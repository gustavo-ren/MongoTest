package spam;

/**
 *
 * @author Gustavo
 */

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration//anotacao que declara que a classe tem um ou mais metodos bean e que eles podem ser processados pelo container
              //Spring para gerar definicoes de beans e requisicoes de servicos em tempo de execucao
@EnableBatchProcessing//Habilita caracteristicas de Spring Batch e prove configuracao base para classes anotadas com 
                      //@Configuration
public class BatchConfiguration {
    
    
    @Autowired//Anotacao que diz ao Spring onde injecoes precisam ocorrer
    public JobBuilderFactory jobBuilderFactory;//Fabrica de JobBuilder que configura JobRepository automaticamente
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;//Fabrica de stepBuilder que configura o JobRepository e o PlatformTransactionManager
                                                 //automaticamente. 
    
    @Autowired
    public DataSource dataSource;//Fabrica para conexoes entre a origem fisica do dado e o objeto DataSource que o representa
    
    @Bean//Marcacao de Bean, analogo ao XML </bean>
    public FlatFileItemReader<Person> reader(){//ItemReader reiniciavel que le linhas atraves do metodo setResource
        FlatFileItemReader<Person> reader=new FlatFileItemReader<>();
        
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>(){{//Setter para o mapeador de linhas
            setLineTokenizer(new DelimitedLineTokenizer(){{//lineMapper com duas fases: a primeira atribui um token para cada
                                                           //linha e depois as mapeia
                setNames(new String []{"name", "lastName"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }
    
    @Bean
    public PersonItemProcessor processor(){//Recupera um objeto PersonItemProcessor, responsavel por transformar os itens do CSV
        return new PersonItemProcessor();
    }
    
    @Bean
    public JdbcBatchItemWriter<Person> writer(){//ItemWriter que usa caracteristicas batch do NamedParameterJdbcTemplate
                                                //para gerar um lote de comandos para todos os itens gerados
        JdbcBatchItemWriter<Person> writer=new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());//
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:name, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener){
        return jobBuilderFactory.get("importUserJob")//Cria um jobBuilder e inicia o repositorio de jobs, parametro o nome do job
                .incrementer(new RunIdIncrementer())//adiciona um incrementer para o job
                .listener(listener)//Registra um listener para a execucao do job(no caso a classe JobCompletionNotificationListener)
                .flow(step1())//cria um job builder que ira executar um passo ou sequencia de passos
                .end()//
                .build();//constroi o job e o executa com o fluxo fornecido
    }
   
    @Bean
    public Step step1(){//Interface do dominio batch que representa a configuracao de um step, e intencionado para ter a configuracao explicita para o desenvolvedor
        return stepBuilderFactory.get("step1")//cria um stepBuilder e inicializa seu repositorio job e o gerenciador de transicao
                .<Person, Person>chunk(10)//Cria um passo que processa os itens em pedacos com o tamanho, no caso 10, especificado
                .reader(reader())//cria um leitor de itens que prove um stream de itens, e automaticamente registrado como AbstractTaskletStepBuilder.stream(ItemStream) ou um listener se implementar uma interface. E nao transacional por padrao.(o parametro foi o FlatFileItemReader)
                .processor(processor())//um processador de itens que processa ou transforma um strem de itens. Recebeu a estancia da classe PersonItemProcessor, que mudava os nomes para maiusculas
                .writer(writer())//um escritor e itens que escreve um chunk de itens
                .build();//Constroi
    }
}

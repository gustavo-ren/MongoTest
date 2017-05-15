package spam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gustavo
 */

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    
    private final static Logger log=LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate1){
        this.jdbcTemplate=jdbcTemplate1;
    }
    
    /**
     *
     * @param jobExecution
     */
    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus()==BatchStatus.COMPLETED){//Verifica quando o trabalho acabar
            log.info("It's Done, It's Over Now");
            
            List<Person> result=jdbcTemplate.query("SELECT first_name, last_name FROM people", (ResultSet rs, int row) -> new Person(rs.getString(1), rs.getString(2)));//expressao lambda que implementa a interface RowMapper, e a seu unico metodo abstrado mapRow
            result.forEach((person) -> {//expressao lambda para o for each
                log.info("Found "+person+" in database");
            });
        }
    }
}

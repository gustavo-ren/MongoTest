package spam;

/**
 *
 * @author Gustavo
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{
    
    private static final Logger log=LoggerFactory.getLogger(PersonItemProcessor.class);
    
    @Override
    public Person process(Person person) throws Exception{
        final String name=person.getName().toUpperCase();
        final String lastName=person.getLastName().toUpperCase();
        
        final Person ofJedi=new Person(name, lastName);
        
        log.info("Converting "+person+" to "+ofJedi);
        
        return ofJedi;
    }
}

package mongotest;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Gustavo
 */
public class UpdateDocument {

    public void updateSelected(String id, String field, String newValue) {
        
        ConectionFactory factory=new ConectionFactory();
        
        try {
            
            Map<String, Object> update = new HashMap<>();
            update.put(field, newValue);
            
            MongoCollection<Document> collection=factory.getCollection();
            collection.findOneAndUpdate(eq("_id", id), new Document("$set", new Document(update)));
            
        }finally{
            factory.finish();
        }
    }

}

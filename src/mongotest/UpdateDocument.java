package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Gustavo
 */
public class UpdateDocument {

    public void updateSelected(String id) {
        
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {
            
            MongoDatabase database = mongoClient.getDatabase("bleach");
            MongoCollection<Document> collection = database.getCollection("gotei");
            
            Map<String, Object> update = new HashMap<>();
            update.put("zanpakutou", "Haguro tonbo");

            collection.findOneAndUpdate(eq("_id", id), new Document("$set", new Document(update)));
            mongoClient.close();
        }
    }

}

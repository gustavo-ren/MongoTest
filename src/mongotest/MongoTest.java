package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Gustavo
 */

public class MongoTest {

    public static void main(String[] args) throws Exception {
        
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {
            MongoDatabase database=mongoClient.getDatabase("bleach");
            MongoCollection collection=database.getCollection("gotei");
            
            Map <String, Object> json=new HashMap<>();
            
            Document captain= new Document(json);
            collection.insertOne(captain);
        }
        
    }

}

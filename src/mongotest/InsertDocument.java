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
public class InsertDocument {
    
    private String id;
    private String name;
    private String zanpakutou;
    
    public InsertDocument(String id, String name, String zanpakutou){
        this.id=id;
        this.name=name;
        this.zanpakutou=zanpakutou;
    }
    
    public void insert(){
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {
            MongoDatabase database=mongoClient.getDatabase("bleach");
            MongoCollection collection=database.getCollection("gotei");
            
            Map <String, Object> json=new HashMap<>();
            json.put("_id", this.id);
            json.put("name", this.name);
            json.put("zanpakutou", this.zanpakutou);
            Document captain= new Document(json);
            collection.insertOne(captain);
            
        }
    }
}

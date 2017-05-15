package mongotest;

import com.mongodb.client.MongoCollection;
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
        
        ConectionFactory factory=new ConectionFactory();
        try {
            
            MongoCollection<Document> collection=factory.getCollection();
            Map <String, Object> json=new HashMap<>();
            json.put("_id", this.id);
            json.put("name", this.name);
            json.put("zanpakutou", this.zanpakutou);
            Document captain= new Document(json);
            collection.insertOne(captain);
            
        }finally{
            factory.finish();
        }
    }
}

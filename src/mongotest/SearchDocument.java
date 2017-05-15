package mongotest;

/**
 *
 * @author Gustavo
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

public class SearchDocument {

    private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    private MongoDatabase database = mongoClient.getDatabase("bleach");
    private MongoCollection<Document> collection = database.getCollection("gotei");

    public SearchDocument(){}
    
    public void selectAllRecordsFromACollection(String id) {
        Document myDoc;
        myDoc=this.collection.find(eq("_id", id)).first();
        System.out.println(myDoc.toJson());
        mongoClient.close();
    }
}

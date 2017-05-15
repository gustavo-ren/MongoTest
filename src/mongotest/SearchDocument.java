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

    

    public SearchDocument() {
    }

    public void select(String id) {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {
            MongoDatabase database = mongoClient.getDatabase("bleach");
            MongoCollection<Document> collection = database.getCollection("gotei");
            Document myDoc;
            myDoc = collection.find(eq("_id", id)).first();
            System.out.println(myDoc.toJson());
        }
    }

    public Document selectAndReturn(String id) {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {
            MongoDatabase database = mongoClient.getDatabase("bleach");
            MongoCollection<Document> collection = database.getCollection("gotei");
            Document myDoc = collection.find(eq("_id", id)).first();
            mongoClient.close();
            return myDoc;
        }
    }

}

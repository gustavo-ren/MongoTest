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
        
        ConectionFactory factory=new ConectionFactory();        
        try {
            
            MongoCollection<Document> collection=factory.getCollection();
            Document myDoc;
            myDoc = collection.find(eq("_id", id)).first();
            System.out.println(myDoc.toJson());
        }finally{
            factory.finish();
        }
    }

    public Document selectAndReturn(String id) {
        
        ConectionFactory factory=new ConectionFactory(); 
        try {
            MongoCollection<Document> collection=factory.getCollection();
            Document myDoc = collection.find(eq("_id", id)).first();
            return myDoc;
        }finally{
            factory.finish();
        }
    }

}

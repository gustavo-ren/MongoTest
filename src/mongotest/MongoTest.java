package mongotest;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.bson.BSON;
import org.bson.Document;

/**
 *
 * @author Gustavo
 */

public class MongoTest {

    public static void main(String[] args) throws Exception {
        
        Scanner in=new Scanner(System.in);
        
        String id=in.nextLine();
        String name=in.nextLine();
        String zanpakutou=in.nextLine();
        
        InsertDocument insertDocument=new InsertDocument(id, name, zanpakutou);
        
        insertDocument.insert();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Gustavo
 */
public class ConectionFactory {

    private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    private MongoDatabase database = mongoClient.getDatabase("bleach");
    private MongoCollection<Document> collection = database.getCollection("gotei");

    public ConectionFactory() {

    }

    public MongoCollection getCollection() {
        return this.collection;
    }
    
    private void closeClient(){
        this.mongoClient.close();
    }    
    public void finish(){
        closeClient();
    }
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongotest;

import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gustavo
 */
public class SearchDocumentTest {
    
    private InsertDocument insertDocument=new InsertDocument("as", "Aizen Sosuke", "Kyouka Suigetsu");
    Map <String, Object> map=new HashMap<>();
    Document doc;
    
    public SearchDocumentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
        //insertDocument.insert();
        map.put("_id", "as");
        map.put("name", "Aizen Sosuke");
        map.put("zanpakutou", "Kyouka Suigetsu");
        doc=new Document(map);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of selectAllRecordsFromACollection method, of class SearchDocument.
     */
    @Test
    public void testSelectAndReturn() {
        System.out.println("selectAllRecordsFromACollection");
        String id = "as";
        SearchDocument instance = new SearchDocument();
        Document doc1=instance.selectAndReturn(id);
        System.out.println(doc.toString()+" "+ this.doc.toString());
        assertEquals(doc1.toString(), this.doc.toString());
    }
    
}

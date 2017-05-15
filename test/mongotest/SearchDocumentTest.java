/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongotest;

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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of selectAllRecordsFromACollection method, of class SearchDocument.
     */
    @Test
    public void testSelectAllRecordsFromACollection() {
        System.out.println("selectAllRecordsFromACollection");
        String id = "";
        SearchDocument instance = new SearchDocument();
        instance.selectAllRecordsFromACollection(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

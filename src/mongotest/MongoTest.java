package mongotest;

/**
 *
 * @author Gustavo
 */
public class MongoTest {

    public static void main(String[] args) throws Exception {

//        Scanner in=new Scanner(System.in);
//        
//        String id=in.nextLine();
//        String name=in.nextLine();
//        String zanpakutou=in.nextLine();
//        
//        InsertDocument insertDocument=new InsertDocument(id, name, zanpakutou);
//        
//        insertDocument.insert();
        
        SearchDocument searchDocument=new SearchDocument();
        searchDocument.selectAllRecordsFromACollection("zk");
    }

}

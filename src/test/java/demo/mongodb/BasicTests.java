package demo.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.testng.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Martin Frys
 * Date: 1/21/13
 * Time: 5:22 PM
 */
public class BasicTests {
    public static final String DB_NAME = "mydb";
    private MongoClient mc;
    private DB db;

    @BeforeClass
    void setUp() throws UnknownHostException {
        mc = new MongoClient();
        db = mc.getDB(DB_NAME);
        DBCollection col = db.getCollection("collection");
        BasicDBObject document = new BasicDBObject();
        document.put("id", 1001);
        document.put("msg", "hello world mongoDB in Java");
        col.insert(document);
    }

    @Test
    public void testDBExists() throws Exception {
        for (String s : mc.getDatabaseNames()) {
            System.out.println(s);
            if(DB_NAME.equals(s)) {
                return;
            }
        }
        fail("Database " + DB_NAME + " not found.");
    }

    @AfterClass
    void cleanUp() {
        mc.dropDatabase(DB_NAME);
    }
}

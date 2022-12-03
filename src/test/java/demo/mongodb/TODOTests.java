package demo.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: Martin Frys
 * Date: 1/21/13
 * Time: 5:46 PM
 */
public class TODOTests {
    public static final String DB_NAME = "todo";
    private MongoClient mc;
    private DB db;
    private DBCollection items;

    @BeforeClass
    void setUp() throws UnknownHostException {
        mc = new MongoClient();
        db = mc.getDB(DB_NAME);
        items = db.getCollection("items");

        BasicDBObject doc1 = new BasicDBObject();
        doc1.put("task", "Write Code");
        doc1.put("priority", "high");
        items.insert(doc1);

        BasicDBObject doc2 = new BasicDBObject();
        doc2.put("task", "Read the book");
        doc2.put("priority", "highest");
        items.insert(doc2);
    }

    @Test
    public void testCreate() throws Exception {
        BasicDBObject doc = new BasicDBObject();
        doc.put("task", "Test the code");
        doc.put("priority", "low");
        items.insert(doc);

        assertThat("Wrong number of records.", items.find().length(), is(3));

        BasicDBObject query = new BasicDBObject();
        query.put("priority", "high");
        assertThat("Wrong number of results.", items.find(query).length(), is(1));
    }

    @AfterClass
    void cleanUp() {
        mc.dropDatabase(DB_NAME);
    }
}

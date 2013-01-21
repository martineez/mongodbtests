import com.mongodb.*;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Martin Frys
 * Date: 1/21/13
 * Time: 4:50 PM
 */
public class App {
    public static void main(String[] args) throws java.net.UnknownHostException {

        try {
            MongoClient mongoClient = new MongoClient();

            // get database from MongoDB,
            // if database doesn't exists, mongoDB will create it automatically
            DB db = mongoClient.getDB("mydb");

            // Get collection from MongoDB, database named "mydb"
            // if collection doesn't exists, mongoDB will create it automatically
            DBCollection collection = db.getCollection("collection");

            DBCollection sc = db.getCollection("system.indexes");
            System.out.println(sc.getCount());

            for (String s : mongoClient.getDatabaseNames()) {
                System.out.println(s);
            }

            Set<String> colls = db.getCollectionNames();

            for (String s : colls) {
                System.out.println(s);
            }

            // create a document to store key and value
            BasicDBObject document = new BasicDBObject();
            document.put("id", 1001);
            document.put("msg", "hello world mongoDB in Java");

            // save it into collection named "collection"
            collection.insert(document);

            // search query
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("id", 1001);

            // query it
            DBCursor cursor = collection.find(searchQuery);

            // loop over the cursor and display the retrieved result
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            System.out.println("Done");

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}

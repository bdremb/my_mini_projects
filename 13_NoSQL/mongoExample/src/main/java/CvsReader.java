import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class CvsReader {
    private static final String csvFile = "src/main/resources/mongo.csv";

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("MyDocumentCollection");
        collection.drop();

        String result;
        try (BufferedReader read = new BufferedReader(new FileReader(csvFile));) {

            while ((result = read.readLine()) != null) {

                String[] user = result.substring(0, result.length() - 1).split(",\"");
                String[] nameAge = user[0].split(",");
                String[] courses = user[1].split(",");

                Document document = new Document()
                        .append("name", nameAge[0])
                        .append("age", Integer.parseInt(nameAge[1]))
                        .append("courses", Arrays.asList(courses));
                collection.insertOne(document);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(">> Общее количество студентов в базе: " + collection.count());

        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println(">> Количество студентов старше 40 лет: " + collection.countDocuments(query) + " студентов");

        Document student = collection.find().sort(BsonDocument.parse("{age: 1}")).first();
        assert student != null;
        System.out.println(">> Имя самого молодого студента: " +
                student.get("name") + ", возраст: " +
                student.get("age") + " лет");

        Document oldStudent = collection.find().sort(BsonDocument.parse("{age: -1}")).first();
        System.out.println(">> Список курсов самого старого студента: " +
                oldStudent.get("courses") + ", ему " +
                oldStudent.get("age") + " года. " + oldStudent.get("name"));
    }
}

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;

public class Test {
	
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("192.168.1.105");
		MongoDatabase db = mongoClient.getDatabase("test");
		
		Builder b = MongoClientOptions.builder();
		System.out.println(b.build());
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
//		db.getCollection("tt").drop();
//		db.getCollection("tt").insertOne(new Document("scores",asList(new Document("attempt",1).append("score", 10),new Document("attempt",2).append("score", 8))));
		
		//db.getCollection("tt").updateOne(Filters.and(Filters.gt("available", 0),Filters.eq("_id", 123456789)), Updates.combine(Updates.inc("available", -1),Updates.push("checkout", new Document("by","ZJK").append("date", new Date()))));
//		db.getCollection("restaurants").insertOne(
//						new Document("address", new Document()
//													.append("street", "2 Avenue")
//													.append("zipcode", "10075")
//													.append("building", "1480")
//													.append("coord",asList(-73.9557413, 40.7720266)))
//									.append("borough", "Manhattan")
//									.append("cuisine", "Italian")
//									.append("grades",asList(new Document()
//																.append("date",format.parse("2014-10-01T00:00:00Z"))
//																.append("grade", "A")
//																.append("score", 11),
//															new Document()
//																.append("date",format.parse("2014-01-16T00:00:00Z"))
//																.append("grade", "B")
//																.append("score", 17)))
//									.append("name", "Vella")
//									.append("restaurant_id", "41704620"));
		
		
//		pushEach("quizzes",
//                Arrays.asList(new Document("week", 5).append("score", 8),
//                              new Document("week", 6).append("score", 7),
//                              new Document("week", 7).append("score", 6)),
//                new PushOptions().sortDocument(Sorts.descending("score")).slice(-3))
		UpdateResult r = db.getCollection("tt").updateOne(
				eq("_id", new ObjectId("5716024998be361380d256b4")),
				pushEach("scores",asList(new Document("attempt", 7).append("score", 7)),
						new PushOptions().sortDocument(Sorts.descending("score")).slice(-2)));
		System.out.println(r);
		
		db.getCollection("tt").updateOne(
				new Document("_id",new ObjectId("5716024998be361380d256b4")), 
				new Document("$push",new Document("scores",
						new Document("$each",asList(new Document("attempt",3).append("score", 7),new Document("attempt",4).append("score", 4)))
						.append("$sort", new Document("score",1))
						.append("$slice", -10)))
		);
		
		
		//
//		FindIterable<Document> iterable = db.getCollection("tt").find();
//		iterable.forEach(new Block<Document>() {
//			public void apply(final Document document) {
//				System.out.println(document);
//			}
//		});
//		
//		System.out.println("sss");
		
	}
	
}

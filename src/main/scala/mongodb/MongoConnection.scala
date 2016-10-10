package mongodb

import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.collection.immutable.Document

/**
  * Created by Administrator on 2016/10/10.
  */
class MongoConnection {

  println("init")

  def getAllCollects(dbName: String): Unit = {
   val client: MongoClient = MongoClient("mongodb://192.168.1.100:27017")
    val db = client.getDatabase(dbName);
//    val collect = db.getCollection("student")
//    collect.find().subscribe((doc: Document) => println(doc.toJson()))
    val collNames = db.listCollectionNames()
    collNames.foreach(println(_))
//    collNames.subscribe((str:String)=>{
//      println(str)
//    })
  }
}

object Test {
  def main(args: Array[String]) {
    val mongoClient = new MongoConnection
    mongoClient.getAllCollects("test")
    Thread.sleep(3000)
  }
}

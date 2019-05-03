import org.mongodb.scala._
import Helpers._

object ConnetMongoDB extends App {
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  // Use a Connection String
  //val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  // or provide custom MongoClientSettings
  //val settings: MongoClientSettings = MongoClientSettings.builder().applyToClusterSettings(b => b.hosts(List(new ServerAddress("localhost")).asJava)).build()
  //val mongoClient: MongoClient = MongoClient(settings)

  val database: MongoDatabase = mongoClient.getDatabase("mydb")
  val collection: MongoCollection[Document] = database.getCollection("mycol");
  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
  collection.drop().results()
  collection.insertOne(doc).results()
  val documents = (1 to 100) map { i: Int => Document("i" -> i) }
  val insertObservable = collection.insertMany(documents)
  val insertAndCount = for {
    insertResult <- insertObservable
    countResult <- collection.countDocuments()
  } yield countResult

  println(s"total # of documents after inserting 100 small ones (should be 101):  ${insertAndCount.headResult()}")

}
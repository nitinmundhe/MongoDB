import scala.text.Document
object GettingStarted {

  def main(args: Array[String]): Unit = {

    /* Create the SparkSession.
     * If config arguments are passed from the command line using --conf,
     * parse args for the values to set.
     */
    import org.apache.spark.sql.SparkSession

    val spark = SparkSession.builder()
      .master("local")
      .appName("MongoSparkConnectorIntro")
      .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/test.myCollection")
      .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.myCollection")
      .getOrCreate()

    import org.bson.Document
    val documents = spark.sparkContext.parallelize((1 to 10).map(i => Document.parse(s"{test: $i}")))
    MongoSpark.save(documents) // Uses the SparkConf for configuration

  }
}
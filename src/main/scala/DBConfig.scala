object DBConfig {
  val host:String= "localhost"
  val port:String = "27017"
  val testDatabase = "test"
  val testCollection = "scratch"

  val userName:Option[String] = None
  val passWord:Option[String] = None

  def printConfig():Unit={
    println(s"Connected to MongoDB at host $host and port $port with database $testDatabase and collection $testDatabase")
    (userName,passWord) match {
      case (Some(u),Some(pw))=> println(s"with username is $u and password $pw")
      case _ => println("Not using authentication.")
    }
  }
}
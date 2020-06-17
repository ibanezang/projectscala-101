package lectures.part03fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(123)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod) // wrong because it can be Option(null)
  val result = Option(unsafeMethod()) // this will auto take care null
  println(result)

  // chained methods
  def backupMethod(): String = "valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  // design unsafe API
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Valid result")
  val betterChainedResult = betterUnsafeMethod().orElse(betterBackupMethod())
  println(betterChainedResult)

  //functions in Option
  println(myFirstOption.isDefined)
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // not safe

  // map flatMap filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x ⇒ x < 100))
  println(myFirstOption.flatMap(x ⇒ Option(x * 10)))

  // exercise
  val config: Map[String, String] = Map("host" → "127.0.0.1", "port" → "8080")

  class Connection {
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Option(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h ⇒ port.flatMap(p ⇒ Connection(h, p)))
  val connectionStatus = connection.map(con ⇒ con.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  config
    .get("host")
    .flatMap(h ⇒ config.get("port").flatMap(p ⇒ Connection(h, p)))
    .map(connection ⇒ connection.connect)
    .foreach(println)

  // for-comprehension
  val connectionStatusWithFor = for {
    host ← config.get("host")
    port ← config.get("port")
    connection ← Connection(host, port)
  } yield connection.connect
  connectionStatusWithFor.foreach(println)
}

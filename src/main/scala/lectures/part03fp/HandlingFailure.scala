package lectures.part03fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create a success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super failure!"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU!!")

  // Try object via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod: String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod))
  println(fallbackTry)

  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("a valid string")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  println(aSuccess.map(_ * 24))
  println(aSuccess.flatMap(x ⇒ Success(x * 302)))
  println(aSuccess.filter(_ > 20))

  // for-comprehension
  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>....</html>"
      else throw new Exception("Failed establishing connection")
    }
    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new Exception("Port is not available")
    }
    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHtml = possibleConnection.flatMap(_.getSafe("http://google.com"))
  possibleHtml match {
    case Success(value) ⇒ renderHTML(value)
    case Failure(ex) ⇒ println(ex)
  }

  // short hand
  HttpService
    .getSafeConnection(hostname, port)
    .flatMap(_.getSafe("https://google.com"))
    .fold(println, renderHTML)

  // for-comprehension
  (for {
    connection ← HttpService.getSafeConnection(hostname, port)
    html ← connection.getSafe("https://google.com")
  } yield html).fold(println, renderHTML)
}

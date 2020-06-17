package lectures.part02oop

import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {
    def unary_~ = println(s"$name who likes $favoriteMovie is $age years old")

    def likes(movie: String): Boolean = movie == favoriteMovie // infix notation

    def hangOutWith(person: Person): String =
      s"This $name is hanging out with ${person.name}" // infix notation

    def +(nickName: String): Person =
      new Person(s"$name ($nickName)", favoriteMovie, age) // infix notation

    def unary_! : String = s"This is not $name!" // prefix notation

    def unary_+ : Person =
      new Person(name, favoriteMovie, age + 1) // prefix notation

    def isAlive: String = s"$name is alive!" // postfix notation

    def learns(something: String): String =
      s"$name learns $something." // infix notation

    def learnsScala: String = this learns "Scala"

    def apply(): String =
      s"Hi my name is $name and I like '$favoriteMovie'" // apply

    def apply(watchTime: Int): String =
      s"$name watched $favoriteMovie $watchTime times"
  }

  // => infix notation = operator notation. Only works for method with single parameter
  val mary = new Person("Mary", "Inception", 45)
  println(mary likes "Inception")

  val tom = new Person("Tom", "Dragon Ball", 30)
  println(mary.hangOutWith(tom))
  println(mary hangOutWith tom)

  // prefix notation => only works with -, !, ~, +. Example: unary_!
  println(!mary)

  // postfix notation => method without any parameter
  println(mary.isAlive)
  println(mary isAlive)

  // apply => need to define apply method()
  println(mary.apply())
  println(mary())

  // exercise
  val maryTheRockStar = mary + "The rock star"
  ~maryTheRockStar
  println(maryTheRockStar.name)
  println(maryTheRockStar isAlive)

  val maryNextYear = +mary + "next year"
  ~maryNextYear

  println(mary learns "cooking")
  println(mary learnsScala)
  println(mary(3))
  println(mary age)
}

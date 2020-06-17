package lectures.part02oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("wkwkwkwkwkwk")
  }

  funnyAnimal.eat
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name. How can I help?")
  }
  val jim = new Person("Jim") {
    override def sayHi: Unit =
      println(s"Hi, my name is Jim anon. How can I help?")
  }

  jim.sayHi
}

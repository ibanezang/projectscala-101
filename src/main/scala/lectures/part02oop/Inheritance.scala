package lectures.part02oop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    def eat = println("munch munch")

  }
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(val name: String, val age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  var papa = new Adult("Anang", 10, "ABC")
  println(papa.age)

  class Dog(dogType: String) extends Animal {
    override val creatureType: String = dogType
    override def eat: Unit = {
      super.eat
      println("bark bark munch")
    }
  }

  val dog = new Dog("Herder")
  dog.eat
  println(dog.creatureType)

  // type substitution
  val animal: Animal = new Dog("Puddle")
  println(animal.creatureType)
  animal.eat

  // preventing override

}

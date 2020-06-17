package lectures.part02oop

object Generics extends App {

  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
  }

  class MyMap[Key, Value]

  val listInteger = new MyList[Int]
  val listString = new MyList[String]

  object MyList {
    def empty[A]: MyList[A] = new MyList[A]
  }
  val emptyIntList = MyList.empty[Int]
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  // Covariant
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // invariant
  class InvariantList[A]
  val animalInvariantList: InvariantList[Animal] = new InvariantList[Animal]

  // contravariant
  class Trainer[-A]
  val ContravariantList: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  val newCage = new Cage(new Dog)
}

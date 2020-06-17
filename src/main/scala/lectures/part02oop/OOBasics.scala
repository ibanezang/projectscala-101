package lectures.part02oop

import com.sun.source.tree.CompilationUnitTree

object OOBasics extends App {
  val person = new Person("Anang", 20)
  println(person)
  println(person.age)
  println(person.x)
  person.greet("Bambang")
  person.greet()

  val writer = new Writer("Anang", "Satria", 1987)
  val writer2 = new Writer("Anang", "Satria", 1987)
  val novel = new Novel("New Novel", 2000, writer)
  println(writer.fullName())
  println(novel.isWrittenBy(writer2))
  println(novel.authorAge())

  val counter = new Counter
  counter.increment.increment(10).print
}

// constructor
class Person(name: String, val age: Int = 10) {
  val x = 2
  println(x + 3)

  def greet(name: String): Unit = println(s"${this.name} said: Hi $name!")
  def greet(): Unit = println(s"My name is $name")
  def this(name: String) = this(name, 10)
  def this() = this("John does", 10)
}

class Writer(val firstName: String, val lastName: String, val year: Int) {
  def fullName(): String = s"$firstName $lastName"
}

class Novel(val name: String, val releaseYear: Int, author: Writer) {
  def authorAge(): Int = releaseYear - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYearRelease: Int): Novel = new Novel(name, newYearRelease, author)
}

class Counter(val count: Int = 0) {
  def increment: Counter = {
    println("incrementing")
    new Counter(count + 1)
  }
  def decrement: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }
  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment.increment(n - 1)
  }
  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement.decrement(n - 1)
  }

  def print = println(count)
}

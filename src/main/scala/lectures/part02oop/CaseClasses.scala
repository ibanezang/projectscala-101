package lectures.part02oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  // 1. parameters are fields
  val jim = new Person("Jim", 10)
  println(s"${jim.name} is ${jim.age} years old")

  // 2. sensible toString
  println(jim.toString)

  // 3. equal and hashcode
  val jim2 = new Person("Jim", 10)
  println(jim == jim2)

  // 4. have copy function
  val jim3 = jim.copy(age = 100)
  println(jim3)

  // 5. have companion object
  val personA = Person
  val mary = Person("Mary", 30)
  println(personA.toString + mary.toString)

  // 6. serializable

  // 7. have extractor pattern => can be used in pattern matching

  case object UnitedKingdom {
    def name: String = "The United Kingdom"
  }
}

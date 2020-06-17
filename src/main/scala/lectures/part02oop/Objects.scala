package lectures.part02oop

object Objects extends App {
  object Person {
    val N_EYES = 2
    def canFly = false
  }

  println(Person.N_EYES)
  println(Person.canFly)

  // scala object = SINGLETON
  val mary = Person
  val John = Person
}

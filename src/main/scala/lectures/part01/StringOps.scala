package lectures.part01

object StringOps extends App {
  val str: String = "Hello, I'm learning scala"
  println(str.charAt(2))
  println(str.substring(3, 10))
  val name = "Anang"
  val age = 20
  val salary = 20000
  println(f"Name: $name%s, Age: $age%3d, Salary: $salary%2.2f")
  val multiLine =
    """{
      | asdfa
      | asdfds
      | asdfasdf
      | fdsafd
      |}""".stripMargin
  println(multiLine)
}

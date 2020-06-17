package lectures.part03fp

object WhatsAFunction extends App {
  // use functions as first class elements
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(320))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("40") + 50)

  val adder: (Int, Int) ⇒ Long = new Function2[Int, Int, Long] {
    override def apply(v1: Int, v2: Int): Long = v1 + v2
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // all scala functions are objects => derived from Function Types

  val concat: (String, String) ⇒ String = (v1: String, v2: String) => s"$v1 $v2"

  println(concat("Hello", "World!"))

  val a: Int ⇒ Int ⇒ Int = new Function1[Int, Int ⇒ Int] {
    override def apply(v1: Int): Int ⇒ Int = ???
  }

  val superAdder: Int ⇒ Int ⇒ Int = x ⇒ y ⇒ x + y

  val adder3 = superAdder(3)

  println(adder3(20))
  println(superAdder(3)(20)) // curried function

  val intString: Function1[Int, (Int, String)] = (x: Int) ⇒ (x, f"$x%2.2f")
  println(intString(100))
}

class MyFunction[A, B] {
  def apply(element: A): B = ???
}

package lectures.part03fp

import lectures.part03fp.WhatsAFunction.adder3

object AnonymousFunctions extends App {

  // anonymous function (Lambda)
  val doubler: Int ⇒ Int = (x: Int) ⇒ x * 2
  println(doubler(100))

  val adder: (Int, Int) ⇒ Int = (a: Int, b: Int) ⇒ a + b
  println(adder(100, 200))

  val return3: () ⇒ Int = () ⇒ 3

  println(return3)
  println(return3())

  val stringToInt = (s: String) ⇒ {
    s.toInt
  }

  println(stringToInt("10"))

  // MOAR syntactic sugar
  val niceIncrementer: Int ⇒ Int = _ + 1 // equivalent to x => x + 1
  println(niceIncrementer(40))
  val niceAdder: (Int, Int) ⇒ Int = _ + _ // equivalent to (a, b) => a + b

  val superAdder: Int ⇒ Int ⇒ Int = x ⇒ y ⇒ x + y
  val adder3 = superAdder(3)
  println(adder3(20))
  println(superAdder(10)(4))
}

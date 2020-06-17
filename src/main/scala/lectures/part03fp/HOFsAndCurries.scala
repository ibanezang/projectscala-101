package lectures.part03fp

object HOFsAndCurries extends App {
  //val superFunction: (Int, (String, (Int ⇒ Boolean)) ⇒ Int) ⇒ (Int ⇒ Int) = ???
  // higher order function (HOF)
  // example: map, filter, flatMap, partition in MyList

  // function that applies a function n times over a value of x
  // nTimes(f, n, x), ex: nTimes(f, 3, x) = f(f(f(x)))
  def nTimes(f: Int ⇒ Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }
  val doubler = (x: Int) ⇒ x * 2
  val incrementer = (x: Int) ⇒ x + 1

  println(nTimes(incrementer, 20, 3))
  println(nTimes(doubler, 3, 4))

  def nTimesBetter(f: Int ⇒ Int, n: Int): Int ⇒ Int = {
    if (n <= 0) (x: Int) ⇒ x
    else (x: Int) ⇒ nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(incrementer, 1000)
  println(plus10(30))

  // curried function
  val superAdder: Int ⇒ Int ⇒ Int = (x: Int) ⇒ (y: Int) ⇒ x + y
  val add3 = superAdder(3)
  println(add3(90))

  //functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: Double ⇒ String = curriedFormatter("%4.2f")
  val preciseFormat: Double ⇒ String = curriedFormatter("%10.8f")

  println(standardFormat(100000 * Math.PI))
  println(preciseFormat(100000 * Math.PI))

  def toCurry(f: (Int, Int) ⇒ Int): Int ⇒ Int ⇒ Int = { x ⇒ y ⇒
    f(x, y)
  }

  def fromCurry(f: Int ⇒ Int ⇒ Int): (Int, Int) ⇒ Int = { (x, y) ⇒
    f(x)(y)
  }

  val superAdder2 = toCurry(_ + _)
  println(superAdder2(4)(14))

  val curriedFunction = toCurry((x: Int, y: Int) ⇒ x + y)
  val adder5 = curriedFunction(5)
  println(adder5(30))

  val original = fromCurry(curriedFunction)
  println(original(5, 30))

  def compose[A, B, C](f: A ⇒ B, g: C ⇒ A): C ⇒ B = { x ⇒
    f(g(x))
  }

  def andThen[A, B, C](f: A ⇒ B, g: B ⇒ C): A ⇒ C = { x ⇒
    g(f(x))
  }

  val adder2: Int ⇒ Int = x ⇒ x + 2
  val times3: Int ⇒ Int = x ⇒ x * 3

  val add2ThenTimes3 = compose(times3, adder2)
  println(add2ThenTimes3(10))

  val times3ThenAdd2 = andThen(times3, adder2)
  println(times3ThenAdd2(10))

}

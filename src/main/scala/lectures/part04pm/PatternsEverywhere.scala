package lectures.part04pm

object PatternsEverywhere extends App {
  // big idea #1
  val error = try { throw new RuntimeException("ERROR!") } catch {
    case e: RuntimeException ⇒ "runtime"
    case npe: NullPointerException ⇒ "npe"
    case _ ⇒ "something else"
  }
  println(error)
  // catches are actually matches

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x ← list if x % 2 == 0
  } yield x * 10
  println(evenOnes)

  // generators are also based on PATTERN MATCHING
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) ← tuples
  } yield first * second
  println(filterTuples)
  // case classes,:: operators,...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // multiple value definitions based on PATTERN MATCHING
  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 ⇒ s"$v is even"
    case 1 ⇒ "the one"
    case _ ⇒ "something else"
  } // partial function literal
  println(mappedList)

  val mappedList2 = list.map { x ⇒
    x match {
      case v if v % 2 == 0 ⇒ s"$v is even"
      case 1 ⇒ "the one"
      case _ ⇒ "something else"
    }
  }
  println(mappedList2)
}

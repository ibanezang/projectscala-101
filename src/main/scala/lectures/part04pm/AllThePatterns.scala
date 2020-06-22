package lectures.part04pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  val x: Any = "Scala"
  // 1. constant
  val constants = x match {
    case 1 ⇒ "a number"
    case "Scala" ⇒ "The scala"
    case true ⇒ "The truth"
    case AllThePatterns ⇒ "The singleton object"
  }

  // 2. match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ ⇒
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something ⇒ s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) ⇒
    case (something, 2) ⇒ s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) ⇒ println(v)
  }
  // pattern matching can be nested

  // 4. case class constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val aListMatch = aList match {
    case Empty ⇒
    case Cons(head, Cons(subhead, subtail)) ⇒
  }

  // 5. list patterns
  val standardList = List(1, 2, 3, 100)
  val standardListMatching = standardList match {
    case List(1, _, _, _) ⇒ // extractor - advanced
    case List(1, _*) ⇒ // list of arbitrary length - advanced
    case 1 :: List(_) ⇒ // infix pattern
    case List(1, 2, 3) :+ 100 ⇒ // infix pattern
  }

  // 6. type specifier
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] ⇒ // explicit type specifier
    case number: Int ⇒
    case _ ⇒
  }

  // 7. name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) ⇒ // name binding => use the name here later
    case Cons(1, rest @ Cons(2, _)) ⇒ // name binding inside nested patterns
  }

  // 8. multi patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) ⇒ // compound pattern (multi-pattern)
    case _ ⇒
  }

  // 9. if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 ⇒
  }

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfString: List[String] ⇒
      "a list of strings" // => will match this because of JVM generic type erasure
    case listOfNumber: List[Int] ⇒ "a list of numbers"
    case _ ⇒ ""
  }

  println(numbersMatch)
}

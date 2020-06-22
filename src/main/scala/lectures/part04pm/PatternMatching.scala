package lectures.part04pm

import scala.util.Random

object PatternMatching extends App {
  // switch in steroid
  val random = new Random(System.nanoTime())
  val x = random.nextInt(10)

  val description = x match {
    case 1 ⇒ "The ONE"
    case 2 ⇒ "Double or nothing"
    case 3 ⇒ "Third time is the charm"
    case _ ⇒ "Something else" // _ wild card
  }
  println(x)
  println(description)

  // 1. decompose values from case class + guard
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 50)
  val greeting = bob match {
    case Person(name, age) if age < 55 ⇒
      s"Hello $name, you are still in productive age now. Keep working hard."
    case Person(name, age) ⇒ s"Hello $name, you are now $age years old"
    case _ ⇒ "I don't know who I am"
  }

  println(greeting)

  /*
   * 1. cases are matched in order
   * 2. what if no matched case? it will throw a MatchError => make sure have wild card case
   * 3. type of pattern matching expression? unified type of all the types in the cases
   * 4. pattern matching works really well with case classes
   * */

  // pattern matching in sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Husky")
  animal match {
    case Dog(breed) ⇒ println(s"Matched a dog of the $breed breed")
  }

  // some wrong use cases of pattern matching
  val isEven = x match {
    case n if n % 2 == 0 ⇒ true
    case _ ⇒ false
  } // WHY!?!?

  isEven match {
    case true ⇒ println("Do something")
    case false ⇒ println("Do something else")
  }

  // simple implementation
  if (x % 2 == 0) println("Do something")
  else println("Do something else")

  /*
  exercise
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  val expression = Prod(
    Prod(Sum(Number(3), Number(5)), Number(10)),
    Sum(Number(40), Prod(Number(30), Sum(Number(20), Number(4))))
  )

  def show(e: Expr): String = e match {
    case Number(n) ⇒ n.toString
    case Sum(e1, e2) ⇒ show(e1) + " + " + show(e2)
    case Prod(e1, e2) ⇒ {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) ⇒ show(expr)
        case Number(_) ⇒ show(expr)
        case _ ⇒ "(" + show(expr) + ")"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show(expression))
}

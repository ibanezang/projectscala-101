package exercises

abstract class Maybe[+T] {
  def map[B](f: T ⇒ B): Maybe[B]
  def filter(p: T ⇒ Boolean): Maybe[T]
  def flatMap[B](f: T ⇒ Maybe[B]): Maybe[B]
}

case object MaybeNot extends Maybe[Nothing] {

  override def filter(p: Nothing ⇒ Boolean): Maybe[Nothing] = MaybeNot

  override def flatMap[B](f: Nothing ⇒ Maybe[B]): Maybe[B] = MaybeNot

  override def map[B](f: Nothing ⇒ B): Maybe[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T] {

  override def filter(p: T ⇒ Boolean): Maybe[T] =
    if (p(value)) this else MaybeNot

  override def flatMap[B](f: T ⇒ Maybe[B]): Maybe[B] = {
    f(value)
  }

  override def map[B](f: T ⇒ B): Maybe[B] = {
    Just(f(value))
  }
}

object MaybeTests extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x ⇒ Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}

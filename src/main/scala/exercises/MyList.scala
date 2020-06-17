package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def ++[B >: A](myList: MyList[B]): MyList[B]
  def printElements: String
  override def toString: String = s"[$printElements]"

  // higher order function because receives function as parameter or returns function as result
  // this functions made MyList support for-comprehension
  def map[B](transformer: A ⇒ B): MyList[B]
  def filter(predicate: A ⇒ Boolean): MyList[A]
  def flatMap[B](transformer: A ⇒ MyList[B]): MyList[B]

  // hofs
  def partition(predicate: A ⇒ Boolean): (MyList[A], MyList[A])
  def foreach(f: A ⇒ Unit): Unit
  def sort(compare: (A, A) ⇒ Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) ⇒ C): MyList[C]
  def fold[B](start: B)(operator: (B, A) ⇒ B): B
}

case object MyList {
  def empty[A]() = Empty
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] =
    new Cons(element, Empty)

  override def printElements: String = ""

  override def map[B](transformer: Nothing ⇒ B): MyList[B] = Empty

  override def filter(predicate: Nothing ⇒ Boolean): MyList[Nothing] = Empty

  override def flatMap[B](transformer: Nothing ⇒ MyList[B]): MyList[B] = Empty

  override def partition(
    predicate: Nothing ⇒ Boolean
  ): (MyList[Nothing], MyList[Nothing]) = (Empty, Empty)

  override def ++[B >: Nothing](myList: MyList[B]): MyList[B] = myList

  override def foreach(f: Nothing ⇒ Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) ⇒ Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B],
                             zip: (Nothing, B) ⇒ C): MyList[C] = {
    if (!list.isEmpty)
      throw new RuntimeException("The lists have different length!")
    else Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) ⇒ B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = {
    if (tail.isEmpty) s"$head"
    else s"$head, ${tail.printElements}"
  }

  override def toString: String = super.toString

  override def map[B](transformer: A ⇒ B): MyList[B] = {
    new Cons[B](transformer(head), tail.map(transformer))
  }

  override def filter(predicate: A ⇒ Boolean): MyList[A] = {
    if (predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: A ⇒ MyList[B]): MyList[B] = {
    transformer(head) ++ t.flatMap(transformer)
  }

  override def partition(predicate: A ⇒ Boolean): (MyList[A], MyList[A]) = {
    if (predicate(head)) {
      val (a, b) = tail.partition(predicate)
      (new Cons(head, Empty) ++ a, b)
    } else {
      val (a, b) = tail.partition(predicate)
      (a, new Cons(head, Empty) ++ b)
    }
  }

  override def ++[B >: A](myList: MyList[B]): MyList[B] =
    new Cons(h, tail ++ myList)

  override def foreach(f: A ⇒ Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) ⇒ Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) < 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = tail.sort(compare)
    insert(head, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) ⇒ C): MyList[C] = {
    if (list.isEmpty)
      throw new RuntimeException("The lists have different length!")
    else new Cons(zip(head, list.head), tail.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) ⇒ B): B =
    tail.fold(operator(start, head))(operator)
}

object Cons {}

object ListTest extends App {
  val listInt = MyList
    .empty[Int] add 1 add 2 add 3 add 10 add 20 add 5 add 10 add 17 add 39
  val doubleList = listInt.map(element ⇒ element * 2)
  val listEven = listInt.filter(element ⇒ element % 2 == 0)

  val listSquared = listInt.flatMap(
    element ⇒ new Cons(element, new Cons(element * element, Empty))
  )

  val (listOdd, listNotOdd) =
    listInt.partition(element => element % 2 != 0)

  val listString = MyList
    .empty[String] add "anang" add "jessica" add "mika" add "mily"
  val (withM, withOutM) =
    listString.partition(element => element.contains("m"))

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  val listAnimal = MyList.empty[Animal] add new Dog add new Cat add new Cat

  println(listInt.toString)
  println(doubleList.toString)
  println((listInt ++ doubleList).toString)
  println(listEven.toString)
  println(listString.toString)
  println(listAnimal.toString)
  println(listOdd.toString)
  println(listNotOdd.toString)
  println(listSquared.toString)
  println(withM.toString)
  println(withOutM.toString)

  listString.foreach(x ⇒ println(x))

  println(listInt.sort((x, y) ⇒ x - y))

  println(listInt.sort((x, y) ⇒ y - x))

  val appendString = MyList.empty add "agnia" add "kirana" add "priamoranda" add "dista"
  println(listString.zipWith[String, String](appendString, (x, y) ⇒ s"$x $y"))

  val addToStringLength = MyList.empty add 10 add 20 add 30 add 40
  println(
    listString.zipWith[Int, Int](addToStringLength, (x, y) ⇒ x.length + y)
  )

  println(addToStringLength.fold(200)((x: Int, y: Int) ⇒ x + y))

  val forResult = for {
    firstName ← listString
    middleName ← appendString
  } yield s"$firstName $middleName"

  println(forResult)
}

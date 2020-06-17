package lectures.part03fp

object MapFlatMapFilterFor extends App {
  val list = Seq(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) ⇒ Seq(x, x + 1)
  println(list.flatMap(toPair))

  // iteration
  val listChar = Seq('a', 'b', 'c', 'd')
  val listColor = Seq("black", "white")
  val combination = list.flatMap(
    n ⇒ listChar.flatMap(c ⇒ listColor.map(color ⇒ s"$n-$c-$color"))
  )

  println(combination)

  // foreach
  list.foreach(println)

  // for-comprehension
  // needs =
  // map(f: A => B): MyList[B],
  // filter(f: A => Boolean): MyList[B],
  // flatMap(f: A => MyList[B]): MyList[B]
  val forCombination = for {
    n ← list if n % 2 == 0
    c ← listChar
    color ← listColor
  } yield s"$n-$c-$color"
  println(forCombination)
  println(forCombination == combination)

  for {
    n ← list
  } yield println(n)

  println(list.map { x ⇒
    x * 2
  })
}

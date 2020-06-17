package lectures.part03fp

import scala.util.Random

object Sequences extends App {

  //Seq
  val aSeq = Seq(4, 1, 3, 2)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5, 6, 7))
  println(aSeq.sorted)

  // Range
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)
  (1 to 10).foreach(x ⇒ print("hello"))

  // List
  val aList = List(1, 2, 3)
  val prepended1 = 42 :: aList
  val prepended2 = 31 +: aList
  val appended1 = aList :+ 39
  println(prepended1)
  println(prepended2)
  println(appended1)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("|-", "-|-", "-|"))

  // arrays
  val numbers = Array(1, 2, 3, 4, 5)
  val threeElements = Array.ofDim[Int](3) // default = 0
  println(threeElements)
  threeElements.foreach(println)
  Array.ofDim[String](5).foreach(println) // default = null

  // mutation
  numbers(2) = 0
  println(numbers.mkString(" : "))

  // arrays and sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1, 2, 3, 4, 5)
  println(vector)

  def getWriteTime(iteration: Int, collection: Seq[Int]): Double = {
    val r = new Random
    val maxCapacity = collection.length
    val times = for {
      it ← 1 to iteration
    } yield {
      val startTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - startTime
    }
    times.sum * 1.0 / iteration
  }

  val maxIteration = 10000
  val maxCapacity = 1000000
  val numberList = (1 to maxCapacity).toList
  val numberVector = (1 to maxCapacity).toVector

  // keep reference to tail
  println(getWriteTime(maxIteration, numberList))
  // depth of the tree is small
  //needs to replace 32-elements chunk
  println(getWriteTime(maxIteration, numberVector))

}

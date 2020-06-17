package lectures.part02oop

object Exceptions extends App {
  val x: String = null
  //println(x.length) null pointer exception

  // 1. throwing exception
  //val npe: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // Exception when there is a problem with the program, example: NPE
  // Error when there is a problem with the system, example: JVM, stackoverflow

  // 2. how to catch exception
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No Int for you!!")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    case e: RuntimeException ⇒ 43
    case _ ⇒ println("Other exception!")
  } finally {
    // optional and doesn't influence the expression return type
    // use finally for side effects
    println("finally")
  }

  println(potentialFail)

  class MyException extends Exception
  val exception = new MyException
  //throw exception

  // 1. Crash your application with an OutOfMemory error
  // val array = Array.ofDim(Int.MaxValue)
  // 2. Crash you application with stackoverflow error
//  def recursiveStackOverflow(int: Int): Int = {
//    recursiveStackOverflow(int * 3) + recursiveStackOverflow(10)
//  }
//
//  println(recursiveStackOverflow(10))

  // 3. Pocket application
  //  - add(x, y)
  //  - substract(x, y)
  //  - multiply(x, y)
  //  - divide(x, y)

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Double = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.add(Int.MaxValue, 10))
  //println(PocketCalculator.subtract(Int.MinValue, 10))
  println(PocketCalculator.divide(10, 0))
}

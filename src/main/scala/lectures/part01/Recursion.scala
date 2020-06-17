package lectures.part01

object Recursion extends App {
  def factorial(n: Int): BigInt = {
    if (n <= 1) 1
    else {
      println(
        s"Computing factorial of $n, first I need to compute factorial of ${n - 1}"
      )
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")
      result
    }
  }

  //println(factorial(10))
  //println(factorial(100))

  def tailFactorial(n: Int, accumulator: BigInt = 1): BigInt = {
    if (n <= 1) accumulator
    else tailFactorial(n - 1, n * accumulator)
  }

  //println(tailFactorial(1000))

  def concatString(n: Int, str: String, accumulator: String = ""): String = {
    if (n <= 1) accumulator
    else concatString(n - 1, str, accumulator + str)
  }

  println(concatString(10, "write this "))

  def fibonacci(n: Int, prev1: BigInt = 0, prev2: BigInt = 1): BigInt = {
    if (n <= 2) prev1 + prev2
    else fibonacci(n - 1, prev2, prev1 + prev2)
  }

  println(fibonacci(200))
}

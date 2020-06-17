package lectures.part01

object DefaultArgs extends App {
  def tailFactorial(n: Int, accumulator: BigInt = 1): BigInt = {
    if (n <= 1) accumulator
    else tailFactorial(n - 1, n * accumulator)
  }
}

package lectures.part01

object CallByValueAndByName extends App {
  def calledByValue(x: Long): Unit = {
    println("call by value: " + x)
    println("call by value: " + x)
  }

  def calledByName(x: ⇒ Long): Unit = {
    println("call by name: " + x)
    println("call by name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}

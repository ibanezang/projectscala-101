package lectures.part02oop

import java.sql

import playground.{PrinceCharming, Cinderella ⇒ Princess}
import java.util.Date
import java.sql.{Date ⇒ SqlDate}

object PackagingAndImports extends App {
  val writer = new Writer("Anang", "Satria", 2018)

  //import package
  val cinderella = new Princess // playground.Cinderella = fully qualified name
  val princeCharming = new PrinceCharming

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello()
  println(SPEED_OF_LIGHT)

  val date = new Date
  // use aliasing
  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}

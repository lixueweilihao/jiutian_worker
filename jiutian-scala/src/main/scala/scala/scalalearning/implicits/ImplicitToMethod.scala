package scala.scalalearning.implicits

/**
  * Created on 2019-07-04
  *
  * @author :hao.li
  */

class ImplicitToMethod

object ImplicitToMethod {

  implicit def stringToInt(s: String): Int = Integer.parseInt(s)

  implicit def typeConversion(input: Int): String = input.toString

  implicit def typeConversion(input: Boolean): String = if (input) "true" else "false"

  def plus(x: Int, y: Int): Int = x + y

  def display(input: String): Unit = println(input)

  def apply: ImplicitToMethod = new ImplicitToMethod()

  def main(args:Array[String]): Unit ={
    ImplicitToMethod.display("1212")
    ImplicitToMethod.display(12)
    ImplicitToMethod.display(true)

    //编译时调用了隐式转换
    val result = ImplicitToMethod.plus("3", "2")
    println(result)

  }
}


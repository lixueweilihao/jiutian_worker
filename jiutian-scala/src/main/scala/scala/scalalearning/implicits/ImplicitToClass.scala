package scala.scalalearning.implicits

/**
  * Created on 2019-07-04
  *
  * @author :hao.li
  */
object ImplicitToClass {
  implicit class MyName(x: Int) {
    val y = x
    println("Test implicit class")
  }

  def say(x: MyName): Unit = {
    println(x.y)
  }
def main(args:Array[String]): Unit ={
  say(5)
}

}

package scala.scalalearning.implicits

/**
  * Created on 2019-07-04
  *
  * @author :hao.li
  */
object ImplicitToValue {
  def funImplicit1(implicit age: Int): Unit = {
    println("funImplicit1: age: " + age)
  }

  def funImplicit2(implicit age: Int, name: String): Unit = {
    println("funImplicit2: age: " + age + ", name:" + name)
  }

  def funImplicit3(age: Int)(implicit name: String): Unit = {
    println("funImplicit3: age: " + age + ", name:" + name)
  }
  def main(args:Array[String]): Unit ={
    implicit val impAge = 30
    implicit val implName = "Jack"
    funImplicit1
    funImplicit2
    funImplicit3(31)
  }

}

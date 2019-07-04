package scala.scalalearning
package scala.implicits

object ImplicitClassTest {
  import utils.StringUtils.StringImprovements

  def main(args: Array[String]): Unit = {
    println("HAL".increment)
  }
}

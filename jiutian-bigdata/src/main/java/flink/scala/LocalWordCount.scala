package flink.scala


object LocalWordCount {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.readTextFile("/Users/lixuewei/workspace/private/play_demo_work/flink/src/test.txt")
    val counts = text.flatMap {
      _.toLowerCase.split("\\W+") filter {
        _.nonEmpty
      }
    }
      .map {
        (_, 1)
      }
      .keyBy(0)
      .sum(1)
    counts.print()
    env.execute("Scala WordCount Example")

    val e = Seq("I love", "coding scala")
    getWords(e).foreach(println(_))
  }

  def getWords(lines: Seq[String]): Seq[String] = {
    lines flatMap (_ split " ")
  }

}



import scala.util.matching.Regex

object test {
  def main(args: Array[String]) {
    def replaceErrorCodeNum(src: String): String = {
      val reg = new Regex("""main\.scala:([0-9]*): error:.*""")
      reg.unapplySeq(src).getOrElse(src) match {
        case l :: Nil => "main.scala:" + (l.toString.toInt - 8).toString + src.substring("main.scala:".length() + l.toString.length())
        case _ => src
      }
    }
    println(replaceErrorCodeNum("dfdsadf fdsafd"))
    println(replaceErrorCodeNum("main.scala:13: error: ';' expected but '.' found."))

  }

}




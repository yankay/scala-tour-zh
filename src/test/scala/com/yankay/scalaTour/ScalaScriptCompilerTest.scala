package com.yankay.scalaTour

import java.util.Date
import java.text.SimpleDateFormat
import scala.util.matching.Regex

object ScalaScriptCompilerTest {
  // this only handles functions with a single argument.
  def main(args: Array[String]) {
  }

  object Email {
    def apply(user: String, domain: String) = user + "@" + domain

    def unapply(str: String) = new Regex("""(.*)@(.*)""").unapplySeq(str).get match {
      case user :: domain :: Nil => Some(user, domain)
      case _ => None
    }
  }

  "user@domain.com" match {
    case Email(user, domain) => println(user + "@" + domain)
  }
}




package com.yankay.scalaTour

import java.util.Date
import java.text.SimpleDateFormat

object ScalaScriptCompilerTest {
  // this only handles functions with a single argument.
  def main(args: Array[String]) {
  }

  implicit def strToDate(str: String) = new SimpleDateFormat("yyyy-MM-dd").parse(str)
  implicit def strToLong(str: String) = str.toLong

  println("2013-01-01 unix time: " + "2013-01-01".getTime() / 1000l)
  println("2013-01-01 unix time: " + ("1" + "1"))

}




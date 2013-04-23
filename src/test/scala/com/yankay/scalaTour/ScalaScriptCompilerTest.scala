package com.yankay.scalaTour

import java.lang.Boolean

object ScalaScriptCompilerTest {

  val file = List("warn 2013 msg", "warn 2012 msg", "error 2013 msg", "warn 2013 msg")

  def wordcount(str: String): Int = str.split(" ").count("msg" == _)

  val counts =
    for (line <- file)
      yield wordcount(line)

  println("counts :" + counts)

  val num = counts.reduceLeft(_ + _)

  println("wordcount:" + num)

}




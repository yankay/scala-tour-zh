package com.yankay.scalaTour

import java.lang.Boolean

object ScalaScriptCompilerTest {

  // cat file | grep 'warn' | grep '2013' | wc

  val file = List("warn 2013 msg", "warn 2012 msg", "error 2013 msg", "warn 2013 msg")

  def wordcount(str: String): Int = str.split(" ").count("msg" == _)

  def foldLeft(list: List[Int])(init: Int)(f: (Int, Int) => Int): Int = {
    list match {
      case List() => init
      case head :: tail => foldLeft(tail)(f(init, head))(f)
    }
  }

  val num = foldLeft(file.map(wordcount))(0)(_ + _)

  println("wordcount:" + num)

}




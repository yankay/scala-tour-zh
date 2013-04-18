package com.yankay.scalaTour

import java.io.ByteArrayOutputStream
import scala.tools.nsc.ScriptRunner
import scala.tools.nsc.GenericRunnerCommand
import java.io.IOException
import java.io.Reader

object ScalaScriptCompilerTest {

  trait ForEachAble[A] {
    def iterator: java.util.Iterator[A]
    def foreach(f: A => Unit) = {
      val iter = iterator
      while (iter.hasNext)
        f(iter.next)
    }
  }

  trait JsonAble {
    override def toString() =
      scala.util.parsing.json.JSONFormat.defaultFormatter(this)
  }

  val list = new java.util.ArrayList[Int]() with ForEachAble[Int]
  list.add(1); list.add(2)

  list.foreach(x => println(x))
  println("Json: " + list.toString())
}




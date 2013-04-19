package com.yankay.scalaTour

import java.io.ByteArrayOutputStream
import scala.tools.nsc.ScriptRunner
import scala.tools.nsc.GenericRunnerCommand
import java.io.IOException
import java.io.Reader

object ScalaScriptCompilerTest {

  def fibonacci(in: Any): Int = in match {
    case 0 => 0
    case n => 1
    case n: Int if (n > 1) => fibonacci(n - 1) + fibonacci(n - 2)
    case n: String => fibonacci(n.toInt)
    case _ => 0
  }

  println(fibonacci(3))
  println(fibonacci(-3))
  println(fibonacci("3"))

}




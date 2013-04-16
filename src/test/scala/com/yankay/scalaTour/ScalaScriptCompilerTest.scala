package com.yankay.scalaTour

import java.io.ByteArrayOutputStream
import scala.tools.nsc.ScriptRunner
import scala.tools.nsc.GenericRunnerCommand
import java.io.IOException
import java.io.Reader

object ScalaScriptCompilerTest {

  val logEnable = false

  def log(msg: => String) =
    if (logEnable) println(msg)

  val MSG = "programing is running"

  log(MSG + 1 / 0)
}




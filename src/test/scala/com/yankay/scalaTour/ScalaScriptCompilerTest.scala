package com.yankay.scalaTour

import java.io.ByteArrayOutputStream
import scala.tools.nsc.ScriptRunner
import scala.tools.nsc.GenericRunnerCommand
import java.io.IOException
import java.io.Reader

object ScalaScriptCompilerTest {
  def withClose[A <: { def close(): Unit }, B](closeAble: A)(op: A => B) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    val msg = 123
    def close() = println("close Connection")
  }

  val conn: Connection = new Connection()
  val msg = withClose(conn) { conn =>
    {
      println("do something with Connection")
      conn.msg
    }
  }
  
  println(msg)
}




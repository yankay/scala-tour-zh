package com.yankay.scalaTour

import java.io.ByteArrayOutputStream
import scala.tools.nsc.ScriptRunner
import scala.tools.nsc.GenericRunnerCommand
import java.io.IOException
import java.io.Reader

object ScalaScriptCompilerTest {

  /**
   * loan pattern around working with writing lines in a file using a bufferedOutput Writer
   * This will do all the cruft and setup for you so your function will receive a BufferedWriter object
   * and you can just call write on it to output your data, at the end everything will be flushed and closed
   * automatically for you. See unit test for explict use
   */

  import java.io.PrintWriter
  import java.io.OutputStream

  import scala.reflect.io.File
  import java.util.Scanner
  def withScanner(f: File)(op: Scanner => Unit) {
    val scanner = new Scanner(f.bufferedReader)
    try {
      op(scanner)
    } finally {
      scanner.close()
    }
  }

  withScanner(File("/proc/self/stat"))(
    scanner => println("pid is " + scanner.next()))

  val tmpDir = System.getProperty("java.io.tmpdir")
  val tmpFilename = tmpDir + "/scalatowntest.log"
  println("writing to file: " + tmpFilename)

}


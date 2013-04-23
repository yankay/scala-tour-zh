package com.yankay.scalaTour

import java.lang.Boolean

object ScalaScriptCompilerTest {

  class ScalaCurrentVersion(val url: String) {
    lazy val source: List[String] = {
      println("fetching from url...")
      scala.io.Source.fromURL(url).getLines().toList
    }
    lazy val majorVersion: Option[String] = source.find(_.contains("version.major"))
    lazy val minorVersion: Option[String] = source.find(_.contains("version.minor"))
  }
  val version = new ScalaCurrentVersion("https://raw.github.com/scala/scala/master/build.number")
  println("get scala version from " + version.url)
  version.majorVersion.foreach(println _)
  version.minorVersion.foreach(println _)

}




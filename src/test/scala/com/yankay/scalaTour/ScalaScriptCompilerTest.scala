package com.yankay.scalaTour

import java.lang.Boolean
import scala.actors.TIMEOUT

object ScalaScriptCompilerTest {
  import scala.actors.Actor._
  val versionUrl = "https://raw.github.com/scala/scala/master/starr.number"
  val fromURL = actor {
    loop {
      react {
        case (url: String, relayer: scala.actors.Actor) =>
          relayer ! scala.io.Source.fromURL(url).getLines().mkString("\n")
      }
    }
  }
  fromURL ! (versionUrl, actor {
    reactWithin(1000) {
      case msg: String => println(msg)
      case TIMEOUT => println("timeout")
    }
  })

}




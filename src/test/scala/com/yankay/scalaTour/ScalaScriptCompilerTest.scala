package com.yankay.scalaTour
import java.lang.Boolean

object ScalaScriptCompilerTest {
  def main(args: Array[String]) {
  }
  import scala.actors.Actor._
  import scala.actors.Actor

  case class Add(v: Int)
  case class Get(i: Int)
  case class Sort()

  val concurrentList = actor {
    val innerList = scala.collection.mutable.LinkedList[Int]()
    loop {
      react {
        case Add(v) => innerList :: v :: Nil
        case Get(i) => reply(innerList(i))
        case Sort => innerList.sortWith(_ < _)
      }
    }
  }
  concurrentList ! Add(3)
  concurrentList ! Add(1)
  concurrentList ! Add(2)
  concurrentList ! Sort()
  val v = concurrentList !? Get(1)
  println("get " + v)
}




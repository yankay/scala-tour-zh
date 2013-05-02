package com.yankay.scalaTour

object PerpareScript {

  def perpare() {
    import scala.io.Codec
    import java.nio.charset.CodingErrorAction

    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
  }

}
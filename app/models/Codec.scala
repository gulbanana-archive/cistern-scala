package models

import java.nio.charset._
import java.nio.CharBuffer
import java.nio.ByteBuffer
import play.api.Logger

abstract class Codec(name: String) extends Charset(name, new Array[String](0)) {
  protected def characters: Array[Char]
  
  def contains(subset: Charset): Boolean = false   
  
  def newEncoder: CharsetEncoder = new CharsetEncoder(Ascii85Codec, 1.0F, 1.0F) {
    def encodeLoop(in: CharBuffer, out: ByteBuffer): CoderResult = {
      CoderResult.malformedForLength(1)
    } 
  }
  
  def newDecoder: CharsetDecoder = new CharsetDecoder(Ascii85Codec, 1.0F, 1.0F) {
    def decodeLoop(in: ByteBuffer, out: CharBuffer): CoderResult = {      
      val result = in.array().take(out.remaining).map { byte =>
        if (byte < 0 || byte >= characters.length)
          return CoderResult.unmappableForLength(1)
        else
          characters(byte)
      }
      
      out.append(result)
      CoderResult.UNDERFLOW
    } 
  }
}
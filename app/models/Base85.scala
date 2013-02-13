package models
import java.util.UUID
import java.nio.charset._
import java.nio.ByteBuffer
import java.nio.CharBuffer
import scala.collection.JavaConversions._
import scala.collection.mutable

object Ascii85Codec extends Codec("ascii85") {
  override val characters = Array(
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
    '!', '#', '$', '%', '&', '(', ')', '*', '+', '-', ';', '<', '=', '>', '?', '@', '^', '_', '`', '{', '|', '}', '~'
  )
}

object Base85 {
  def encode(number: Int): String = {
    val buffer = new Array[Byte](5)  
    
    var x: Long = number & 0xFFFFFFFFL
    for (i <- 0 to 4) {
      buffer(i) = (x % 85).toByte
      x = x / 85
    }
    
    new String(buffer, AlphanumericCodec)
  }
  
  def encode(number: Long): String = {
    val buffer = new Array[Byte](10)  
    
    var x: Long = number & 0xFFFFFFFFL
    for (i <- 0 to 9) {
      buffer(i) = (x % 85).toByte
      x = x / 85
    }
    
    new String(buffer, AlphanumericCodec)
  }
  
  def encode(id: UUID): String = {
    val buffer = new mutable.ArrayBuffer[Byte](10)  
    var i: Int = 0
    
    var x: Long = id.getMostSignificantBits & 0xFFFFFFFFL
    while (i < 11) {
      val byte = (x % 85).toByte
      if (byte != 0) buffer.append(byte)
      x = x / 85
      i = i + 1
    }
    
    x = id.getLeastSignificantBits & 0xFFFFFFFFL
    while (i < 20) {
      val byte = (x % 85).toByte
      if (byte != 0) buffer.append(byte)
      x = x / 85
      i = i + 1
    }
    
    new String(buffer.toArray.map(Ascii85Codec.characters(_)))
  }
}
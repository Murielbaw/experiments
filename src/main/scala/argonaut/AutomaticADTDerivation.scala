/*
package argonaut

import java.util.UUID

import argonaut.derive.{JsonSumCodec, JsonSumCodecFor}

object AutomaticADTDerivation extends App {
  implicit def typeFieldJsonSumCodecFor[S]: JsonSumCodecFor[S] =
    JsonSumCodecFor(JsonSumCodec.typeField)

  sealed trait MyAdt
  case object AdtValue1 extends MyAdt
  case object AdtValue2 extends MyAdt
  case object AdtValue3 extends MyAdt
  case object AdtValue4 extends MyAdt
  case object AdtValue5 extends MyAdt

  val encode = EncodeJson.of[MyAdt]
  val decode = DecodeJson.of[MyAdt]

  val encoded = encode.encode(AdtValue1)

  case class MyCaseClass(name: String, value: MyAdt)


  val jsonPieces: List[String] = (1 to 5).map(i => s"""{ "name": "${UUID.randomUUID().toString}", "value": "AdtValue$i" """).toList

  println(jsonPieces)

  println(encoded)
  println(decode.decodeJson(encoded))
}
*/

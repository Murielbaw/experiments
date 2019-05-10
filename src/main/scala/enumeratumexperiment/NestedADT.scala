package enumeratumexperiment

import enumeratum._
import scala.collection.immutable

sealed trait NestedADT extends EnumEntry

object NestedADT extends Enum[NestedADT] {
  case object Test extends NestedADT

  sealed trait AnotherADT extends NestedADT
  object AnotherADT extends Enum[AnotherADT] {
    case object Test2 extends AnotherADT
    case object Test3 extends AnotherADT

    override def values: immutable.IndexedSeq[AnotherADT] = findValues
  }

  sealed trait AnotherAnotherADT extends NestedADT
  object AnotherAnotherADT extends Enum[AnotherAnotherADT] {
    case object Test4 extends AnotherAnotherADT
    case object Test5 extends AnotherAnotherADT

    override def values: immutable.IndexedSeq[AnotherAnotherADT] = findValues
  }

  override def values: immutable.IndexedSeq[NestedADT] = findValues ++ AnotherADT.values ++ AnotherAnotherADT.values
}
package enumeratumexperiment

import enumeratum._

sealed abstract class SomeTrait(override val entryName: String) extends EnumEntry

object SomeTrait extends Enum[SomeTrait] with PlayJsonEnum[SomeTrait] {
  val values = findValues

  case object SomeInstanceObject extends SomeTrait("Some Instance Object")
  case object SomeOtherInstanceObject extends SomeTrait("Some Other Instance Object")
  case object SomeThirdInstanceObject extends SomeTrait("Some Third Instance Object")
  case object SomeFinalInstanceObject extends SomeTrait("Some final instance Object")
}

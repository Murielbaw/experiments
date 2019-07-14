package enumeratumexperiment

import enumeratumexperiment.SomeTrait.{SomeFinalInstanceObject, SomeInstanceObject, SomeOtherInstanceObject}
import play.api.libs.json.{JsString, Reads, Writes}

object Main extends App {

  println(s"Testing ${implicitly[Writes[SomeTrait]].writes(SomeInstanceObject)}")
  println(s"Testing ${implicitly[Writes[SomeTrait]].writes(SomeFinalInstanceObject)}")
  println(s"Testing ${implicitly[Writes[SomeTrait]].writes(SomeOtherInstanceObject)}")
  println(s"Testing ${implicitly[Reads[SomeTrait]].reads(JsString("Some final instance Object"))}")
}

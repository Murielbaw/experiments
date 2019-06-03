package classy.optics

import cats.MonadError
import cats.effect.IO
import com.olegpy.meow.hierarchy._

import scala.concurrent.ExecutionContext
import scala.language.higherKinds

object SomeApp extends App {
  implicit val ec = ExecutionContext.global

  def someFunction[F[_]]()(implicit ME: MonadError[F, MyError]): F[Int] = {
    ME.raiseError(Err1)
  }


  val r = someFunction[IO]()
    .unsafeToFuture()

  r.recover({case e => println(e); 3}).map(println)


  Thread.sleep(1000000)
}

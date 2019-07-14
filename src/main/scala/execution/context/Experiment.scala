package execution.context


import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Experiment extends App {
  implicit val ec = ExecutionContext.global

  def someF(input: String): Future[String] = Future {
    Thread.sleep(1000)
    println(Thread.currentThread().getId)
    println(input)
    "Succeeded"
  }

  def mappedToUnit(): Future[Unit] = {
    someF("first").map(_ => Unit)
  }

  def f(): Future[String] = for {
    _ <- mappedToUnit()
    a <- someF("second")
    b <- someF("third")
    _ <- someF("fourth").map(_ => Unit)
  } yield  a + b

  val result = Await.result(f(), Duration.Inf)
  println(result)
}

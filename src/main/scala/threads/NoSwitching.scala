package threads

import cats.effect.{ContextShift, ExitCode, IO, IOApp}
import cats.implicits._

import java.time.Instant
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object NoSwitching extends IOApp {
  val service = Executors.newFixedThreadPool(1)
  val ec = ExecutionContext.fromExecutor(service)

  override protected implicit def contextShift: ContextShift[IO] = IO.contextShift(ec)
  def execute(step: Int): IO[Unit] = for {
  _ <- IO(println(s"[$step ${Thread.currentThread().getName}] Before... ${Instant.now}"))
  _ <- IO(Thread.sleep(3000))
  _ <- IO(println(s"[$step ${Thread.currentThread().getName}] After... ${Instant.now}"))
  } yield ()

  override def run(args: List[String]): IO[ExitCode] = {
        for {
        _ <- IO.shift(ec)
        _ <- (0 to 10).toList.parTraverse(execute)
        _ <- IO(service.shutdown())
        } yield ExitCode.Success
      }
}

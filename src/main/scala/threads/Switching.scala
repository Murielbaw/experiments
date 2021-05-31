package threads

import cats.effect.{Blocker, ContextShift, ExitCode, IO, IOApp, Resource}
import cats.implicits._

import java.time.Instant
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object Switching extends IOApp {
  val service = Executors.newFixedThreadPool(1)
  val ec = ExecutionContext.fromExecutor(service)

  override protected implicit def contextShift: ContextShift[IO] = IO.contextShift(ec)

  def executeBlocker(step: Int)(blockingEC: Blocker): IO[Unit] = for {
    _ <- IO(println(s"[$step ${Thread.currentThread().getName}] Before... ${Instant.now}"))
    _ <- blockingEC.blockOn(IO{
      Thread.sleep(3000)
    println(s"Finishing blocking... ${Thread.currentThread().getName}")
    })
    _ <- IO(println(s"[$step ${Thread.currentThread().getName}] After... ${Instant.now}"))
  } yield ()

  def executeLowLevelShifting(step: Int)(blockingEC: ExecutionContext): IO[Unit] = for {
    _ <- IO(println(s"[$step] [${Thread.currentThread().getName}] Before... ${Instant.now}"))
    // use the blocking execution context following this
    // frees up the main thread pool for other work
    // we know that sometime in the future it will finish
    _ <- IO.shift(blockingEC)
    _ <- IO{
      Thread.sleep(3000)
      println(s"[$step] [${Thread.currentThread().getName}] Finishing blocking...")
    }
    // need to make sure we return to the main execution context following this
    // otherwise, we observe that it will stick to the blocking EC
    // to experiment, comment out and observe the Thread names
    _ <- IO.shift
    _ <- IO(println(s"[$step] [${Thread.currentThread().getName}] After... ${Instant.now}"))
  } yield ()

  override def run(args: List[String]): IO[ExitCode] = {
    (for {
      blockerEc <- Resource.make(IO(Executors.newSingleThreadExecutor()))(ec => IO(ec.shutdown())).map(ExecutionContext.fromExecutor)
    blocker = Blocker.liftExecutionContext(blockerEc)
    } yield (ec, blocker)).use {
      case (mainEc, blockingEC) =>
          for {
            _ <- IO.shift(mainEc)
            _ <- (0 to 10).toList.parTraverse(s => executeLowLevelShifting(s)(blockingEC.blockingContext))
            _ <- IO(println(s"[${Thread.currentThread().getName}] Finished program execution at ${Instant.now()}"))
            _ <- IO(service.shutdown())
          } yield ExitCode.Success
      }
  }
}

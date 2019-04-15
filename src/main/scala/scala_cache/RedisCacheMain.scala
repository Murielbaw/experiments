package scala_cache
import java.util.UUID

import cats.Traverse
import cats.effect.IO
import scalacache._
import scalacache.redis._
import scalacache.serialization.binary._
import cats.syntax.all._
import cats.instances.all._
import scala.concurrent.duration._
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object RedisCacheMain extends App  {
  case class User(username: String, email: String)

  implicit val redisCache: Cache[User] = RedisCache("host1", 6379)

  def addToCache(user: User): IO[Unit] = IO.delay {
    Thread.sleep(Random.nextInt(4) * 1000)
    println(s"Adding to cache from ${Thread.currentThread()}")
  }

  def removeFromCache(user: User): IO[Unit] = IO.delay {
    Thread.sleep(Random.nextInt(4) * 1000)
    println(s"Removing from cache from ${Thread.currentThread()}")
  }

  def updateCache(old: User, updated: User): IO[Unit] = IO.delay{
    Thread.sleep(Random.nextInt(4) * 1000)
    println(s"Updating cache from ${Thread.currentThread()}")
  }

  def getFromCache(user: User): IO[Any] = IO.delay {
    Thread.sleep(Random.nextInt(4) * 1000)
    println(s"Retrieving from cache ${Thread.currentThread()}: ???")
  }

  def getRandomUser(): IO[User] = IO.apply(User(UUID.randomUUID().toString, UUID.randomUUID().toString))


  def cachingTest(): IO[List[Unit]] = Traverse[List].traverse((0 to 100).toList){_ =>
    getRandomUser() >>= {
      user =>
        (addToCache(user), removeFromCache(user), updateCache(user, user), getFromCache(user)).mapN {
          (_, _, _, _) => ()
        }
    }
  }

  cachingTest().unsafeToFuture().onComplete(println)
}

package streams

import collection.immutable.Stream
import scala.annotation.tailrec

object Examples extends App {
  @tailrec def getFor(n: Int, acc: Int): Int = n match {
    case 0 => acc
    case 1 => acc
    case e => getFor(e - 1, acc * n)
  }

  def factorialUpTo(n: Int): Stream[Int] = {
    n match {
      case 0 => Stream(1)
      case 1 => Stream(1)
      case e => factorialUpTo(e - 1).append(Stream(getFor(e, 1)))
    }
  }

  case class FactorialPair(position: Int, value: Double)

  def infiniteFactorial: Stream[FactorialPair] = {
    FactorialPair(0, 1) #:: FactorialPair(1, 1) #:: infiniteFactorial.tail.map(fp => FactorialPair(fp.position + 1, fp.value * (fp.position + 1)))
  }

  println(infiniteFactorial.take(1000).toList)
}

package classy.optics

sealed trait MyError extends Throwable

case object Err1 extends MyError
case object Err2 extends MyError
case object Err3 extends MyError
case object Err4 extends MyError
case object Err5 extends MyError

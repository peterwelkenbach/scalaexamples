package book.chapter3

import scalaz._
import Scalaz._


sealed trait Result[+A]
final case class Success[A]( value: A) extends Result[A]
final case class Warning[A]( value: A, message: String) extends Result[A]
final case class Error[A]( message: String) extends Result[ Nothing ]

object ResultOpp {
  implicit object resultFunctor extends Functor[Result] {
    def map[A,B] (result: Result[A])(func: A => B ): Result[B] =
      result match {
        case Success(value)      => Success( func(value) )
        case Warning(value, msg) => Warning( func(value), msg)
        case Error( msg )      => Error (msg )
      }
  }

  def success[A](value: A): Result[A] = Success(value)
  def warning[A](value: A, msg: String): Result[A] = Warning(value, msg)
  def error[A](msg: String): Result[A] = Error(msg)
}


object FunctorExample1 extends App {

  import ResultOpp._

  val res1 = success(100) map ( _ * 2)
  println( res1 )

  val res2 = warning(200, " may be too high") map ( _ + 1)
  println( res2  )

  val res3 = error[Int](" Oh my GOD") map ( _ + 1)
  println( res3  )


}

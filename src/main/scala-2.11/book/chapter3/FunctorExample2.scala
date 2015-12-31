package book.chapter3

import scalaz._
import Scalaz._


sealed trait Yoho[+A]
final case class In[A]( value: A ) extends Yoho[A]
final case class Out[A]( value: A, str: String ) extends Yoho[A]


object FunctorOpp {
  implicit object FooFunctor extends Functor[Yoho] {
    def map[A, B](foo: Yoho[A])(func: A => B): Yoho[B] = {
      foo match {
        case In( v ) => Out( func(v), "Hello")
      }
    }
  }

  def doit[A](value: A): Yoho[A] = In(value)
}

object FunctorExample2 extends App {
  import FunctorOpp._

  val in = doit(4)

  println( in map ( _ * 2 ) )

}

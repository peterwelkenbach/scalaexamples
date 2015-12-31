
trait Combinable[T] {
  def combine(a: T, b: T): T
}

object Combinable {
  def apply[T](implicit c: Combinable[T]) = c


  trait CombinableOps[T] {
    def self: T
    def combinable: Combinable[T]
    def combine(other: T) =
         combinable.combine(self, other)
  }

  object Oops {
    implicit def toCombinableOps[T](t: T)(implicit c: Combinable[T]) =
      new CombinableOps[T] {
        override def self: T = t

        override def combinable: Combinable[T] = c
      }
  }

  implicit object StringCombinable extends Combinable[String] {
    override def combine(a: String, b: String): String = a + b
  }
}


Combinable[String].combine("Hello", " World")

val combinable = Combinable[String]

  combinable.combine("Huhu",
    combinable.combine(" Ihr", " Alle"))



import Combinable.Oops._

"Foo" combine " Bar" combine " Boo"
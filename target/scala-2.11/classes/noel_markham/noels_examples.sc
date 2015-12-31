import scalaz._
import Scalaz._

//------------------------------------
// Functor
//------------------------------------
List(10, 20, 30).map( _ / 10 )

"hello".some.map( _.length )

val f = Option(200).map( _ === 400 )
f.foreach( println )



def addInt[F[_]]
     (i: Int, toAdd: F[Int] )
     (implicit f: Functor[F]): F[Int] = {
  f.map( toAdd )( _ + i)
}


addInt(6, 4.some )

addInt( 2, List(10,20,30))

val r = addInt( 100, Option(40))
r.foreach( println )


//-------------------------------------
// Monoid
//-------------------------------------

1 |+| 2 |+| 3

"Hello".some |+| None |+| " World".some


List( 10, 20, 30 ).foldMap( i => i )
// 60

List( "a", "bb", "ccc" ).foldMap( _.length )
// 6

trait Semigroup[F] {
  def append(f1: F, f2: F): F

  def |+|(f1: F, f2: F): F = append(f1, f2 )
}

trait Monoid[F] extends Semigroup[F] {
  def zero: F
}


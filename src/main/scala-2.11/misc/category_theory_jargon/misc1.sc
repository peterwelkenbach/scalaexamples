import scalaz._
import Scalaz._


def add( a: Int, b: Int) = a + b

add(2, 3)

val foo = ( add _ ).tupled

foo( (3,4) )

// add( (3,4) )

val bar = Function.untupled( foo )

bar(3,4)

// bar( (3,4) )

val curried_a = ( add _ ).curried

val addTwoTo = curried_a(2)
val x = addTwoTo( 5 )

val uncurried_a = Function.uncurried( curried_a )
val y = uncurried_a(7,8)



def addTwo(z: Int) =  some(z + 2)

addTwo(8)

some(2) |+| some(4)

Functor[Option].map(some(5))( addTwo )



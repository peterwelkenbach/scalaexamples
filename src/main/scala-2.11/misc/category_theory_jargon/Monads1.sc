import scalaz._
import Scalaz._

List(1,2,3).map( x => x ) == List(1,2,3)
List(1,2,3).map( x => x + 1 ).map( x => x * 2) == List(1,2,3).map( x => (x+1)*2)


type IntToSomething[R] = Function1[Int, R]
val addOne: IntToSomething[Int] = (i => i + 1)
val addOneAndToString: IntToSomething[String] = addOne andThen( i => i.toString )


val list2 = List(1,2,3)
val addOne2: Int => Int = _ + 1
val timesTwo2: Int => Int = _ * 2
val functions: List[Int => Int] = List( addOne2, timesTwo2 )
val result2: List[Int] = list2 <*> functions
// list(2,3,4,2,4,6)


val maybeInt: Option[Int] = Some(99)
val maybeFunction: Option[Int => String] = Some( _.toString )
val result3: Option[String] = maybeInt <*> maybeFunction
// Some("99")


val list: List[Int] = List(10,20,30)
val function: Int => List[Int] = (i => List( i+1, i+2))
val result4: List[Int] = list.flatMap( function )
// List( 11,12,21,22,31,32 )

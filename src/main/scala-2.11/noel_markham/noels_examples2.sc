import scalaz._
import Scalaz._

val m1 = Map( 1 -> List("a", "b"),
              2 -> List("aa", "bb"))

val m2 = Map( 1 -> List("z"),
              3 -> List("yyy", "zzz"))

m1 |+| m2



// Map(1 -> List(a, b, z),
//     3 -> List(yyy, zzz),
//     2 -> List(aa, bb))


val m3 = Map( "a" -> 1, "b" -> 1 )

val m4 = Map( "a" -> 1, "c" -> 1 )


m3 |+| m4

// Map(a -> 2, c -> 1, b -> 1)



List( "a", "b", "b", "b", "c", "c")
     .foldMap( c => Map(c -> 1))

// Map(b -> 3, a -> 1, c -> 2)



val o1: Option[List[Int]] = Some(List(1,2,3))
val o2 = None
val o3: Option[List[Int]] = Some(List(4,5,6))

o1 |+| o2 |+| o3

// Some(List(1, 2, 3, 4, 5, 6))


val o10 = some(List(1,2,3))
val o20 = None
val o30 = some(List(7,8,9))

o10 |+| o20 |+| o30

// Some(List(1, 2, 3, 7, 8, 9))


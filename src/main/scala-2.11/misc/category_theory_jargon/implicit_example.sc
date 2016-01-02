import java.util.Calendar
//------------------------------------------
// anonymous classes
//------------------------------------------
def createAgent() = {
  new {
    val firstName = "James"
    val lastName = "Bond"
  }
}
val jb = createAgent()
println( jb.firstName + " " + jb.lastName )

//---------------------------------------
// implicites and fluent interfaces
//---------------------------------------
class DayUtil( val number: Int ){
   def days = this
   def ago = {
     val today = Calendar.getInstance()
     today.add( Calendar.DAY_OF_MONTH, -number)
     today.getTime
   }
   def fromNow = {
     val today = Calendar.getInstance()
     today.add( Calendar.DAY_OF_MONTH, +number)
     today.getTime
   }
}

implicit def intToDayUtil(number: Int): DayUtil =
   new DayUtil( number )

val vorgestern = 2.days.ago
println( vorgestern )


val uebermorgen = 2.days.fromNow
println( uebermorgen )


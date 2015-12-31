import com.amazonaws.services.dynamodbv2.model.AttributeValue
import collection.convert.decorateAll._

trait DynamoFormat[T] {
  def write(t: T): AttributeValue
  def read( av: AttributeValue): T
}

object DynamoFormat {
  def apply[T](implicit f: DynamoFormat[T]) = f

  implicit object StringDynamoFormat extends DynamoFormat[String] {
    override def write(s: String) =
      new AttributeValue().withS(s)

    override def read(av: AttributeValue):String =
      av.getS
  }

  implicit def listFormat[T](implicit f: DynamoFormat[T]) = {
    new DynamoFormat[List[T]] {
      override def write(l: List[T]) =
        new AttributeValue().withL( l.map(f.write).asJava )

      override def read(av: AttributeValue) =
         av.getL.asScala.map( f.read ).toList
    }
  }

  implicit def mapFormat[V](implicit f: DynamoFormat[V]) = {
    new DynamoFormat[Map[String, V]] {
      override def write(m: Map[String, V]) =
        new AttributeValue().withM( m.mapValues(f.write).asJava )

      override def read(av: AttributeValue) =
        av.getM.asScala.mapValues( f.read ).toMap
    }
  }
}

def roundTrip[T] (t: T)(implicit f: DynamoFormat[T]) =
  f.read( f.write(t)  )


roundTrip("Hello world")
roundTrip( List("Hello", " ", "World" )  )
roundTrip( List( List("Deep", "Hello", " ", "World") )  )

roundTrip( Map[String, String]( "foo" -> "bar") )

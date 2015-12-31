import com.amazonaws.services.dynamodbv2.model.AttributeValue

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
}

def roundTrip[T] (t: T)(implicit f: DynamoFormat[T]) =
   f.read( f.write(t)  )


roundTrip("Hello world")

package slick

import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

object SlickExample extends App {
  case class RandomExample(id: Long,
                           field1: String,
                           field2: Int)

  class RandomExampleTable(tag: Tag) extends Table[RandomExample](tag, "random-example") {
    def field1: Rep[String] = column[String]("field1")
    def field2: Rep[Int]    = column[Int]("field2")
    def id: Rep[Long]       = column[Long]("id", O.PrimaryKey, O.AutoInc)

    override def * : ProvenShape[RandomExample] = (id, field1, field2).mapTo[RandomExample]
  }
}

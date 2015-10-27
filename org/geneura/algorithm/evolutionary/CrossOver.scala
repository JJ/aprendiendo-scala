package org.geneura.algorithm.evolutionary.CrossOver


object CrossOver {

  def apply(  oneEO : IndexedSeq[Boolean], anotherEO : IndexedSeq[Boolean]  ): (IndexedSeq[Boolean],IndexedSeq[Boolean]) = {
    var point1 = (oneEO.length * scala.util.Random.nextDouble).toInt
    var point2 = (oneEO.length * scala.util.Random.nextDouble).toInt
    if (point1 > point2) {
      val temp = point1
      point1 = point2
      point2 = temp
    }
    ( oneEO.take( point1 ) ++ anotherEO.slice(point1,point2) ++ oneEO.drop( point2), anotherEO.take( point1 ) ++ oneEO.slice(point1,point2) ++ anotherEO.drop( point2 ) ) 
  }
  
  def apply(  oneEO : String, anotherEO : String  ): (String,String) = {
    var point1 = (oneEO.length * scala.util.Random.nextDouble).toInt
    var point2 = (oneEO.length * scala.util.Random.nextDouble).toInt
    if (point1 > point2) {
      val temp = point1
      point1 = point2
      point2 = temp
    }
    ( oneEO.take( point1 ) ++ anotherEO.slice(point1,point2) ++ oneEO.drop( point2), anotherEO.take( point1 ) ++ oneEO.slice(point1,point2) ++ anotherEO.drop( point2 ) ) 
  }

}

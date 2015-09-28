package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List { // `List` companion object. Contains functions for creating and working with lists.

  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def appendElement[A](el: A, a2: List[A]): List[A] =
    a2 match {
      case Nil => List(el)
      case Cons(h,t) => Cons(el, a2)
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def product3(ns: List[Double]) = sys.error(" Short-circuiting can't be implemented unless you change foldRight implementation " )

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs ) => xs
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => Nil
    case Cons( x, xs ) => Cons( h, xs)
  }

  def drop[A](l: List[A], n: Int): List[A]  = l match {
    case Nil => Nil
    case Cons( x, xs ) => 
      if ( n == 1 ) xs
      else drop( xs, n-1 )    
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =  l match {
    case Nil => Nil
    case Cons( x, xs ) => 
      if ( f(x) ) dropWhile(xs,f)
      else Cons(x,xs)
  }


  def miLength[A](l: List[A]): Int = l match {
    case Nil => 0
    case Cons( x, xs ) => 1 + length(xs)
  }

  def length[A](l: List[A]): Int = foldRight(l, 0)( (x: A, y: Int ) => y + 1 )

  def firstN[A](l: List[A], n: Int): List[A]  =  l match {
    case Nil => Nil
    case Cons(x, xs) => 
      if (n > length(l)) l
      else if ( n == 1 ) List(x)
      else appendElement(x,firstN(xs, n-1))
  }
  
  def init[A](l: List[A]): List[A]  = firstN( l, length(l)-1)

  def last[A](l : List[A]): A = l match {
    case Nil => sys.error( "No last item" )
    case Cons(x, xs ) =>
      if ( xs == Nil ) x
      else last( xs )
  }

  def reverse[A]( l: List[A] ): List[A] = l match {
    case Nil => Nil
    case Cons( x, xs ) => append( reverse(xs), List(x ))
  }

  def foldLeft[A,B](l: List[A], z: B)(f: (A, B) => B): B = foldRight( reverse( l), z)( f )

  def sumLeft( l: List[Int] ): Int = foldLeft(l,0)(_ + _ )

  def productLeft( l: List[Double] ): Double = foldLeft(l,1.0)(_ * _ )

  def lengthL[A](l: List[A]): Int = foldLeft(l, 0)( (x: A, y: Int ) => y + 1 )

  def reverseFold[A]( l: List[A] ): List[A] = foldLeft( l, List[A]() )( (x: A, xs: List[A]) => appendElement( x, xs ))

  def appendF[A](a1: List[A], a2: List[A]): List[A] = foldLeft( a1, a2 )( (x: A, xs: List[A]) => appendElement( x, xs ))

  def map[A,B](l: List[A])(f: A => B): List[B] = sys.error("todo")
}

//package rusha.x
//
//import org.jetbrains.annotations.TestOnly
//import kotlin.math.sqrt
//
//class IntNumber (
//    val m: Int,
//    val n: Int,
//)
//  fun max (m: Int,n: Int)=
//      if(m > n) m else n
//
//  fun quadraticRootNumber(
//    a: Double,b: Double,c:Double): Int{
//      TODO()
//  }
////каскадное применение оператора
//// if(..) ..
//// else if(..)..
////..
////else ..
//  fun quadraticRootNumber(
//      a: Double, b: Double, c: Double): Int{
//      val d = discriminant(a, b, c)
//      return if( d > 0.0) 2
//      else if (d==0.0) 1
//      else 0
//  }
////альтернативный способ
////описание множксвенного выбора
////оператор табличного выбора
////when{
////cound1 -> res1
////cound2 -> res2
//// ...
////else -> res}
//   fun quadraticRootNumber(
//            a: Double,b: Double,c: Double: Int{
//            val d = discriminant( a, b, c)
//            return when{
//                d > 0.0 -> 2
//                d == 0.0 -> 1
//                else -> 0
//            }
//        }
//        )
//// when  с субьектом
//// when(subj) {
//// subj Cond1 -> res1
//// subj Cond2 -> res2
//// subj Cond3 -> res3
//// ..
//// else -> resElse}
//   fun gradeNotation( grade : Int) : String =
//    when ( grade){
//        5 -> "отлично"
//        4 -> "хорошо"
//        3 -> "удовлетворительно"
//        2 -> "неудовлетворительно"
//        else -> "несуществующая оценка$grade"
//    }
//     println (5 < 2) //true
//     println (5 > 2) //false
//     println (3 >= 3) //true
//     println (3 <= 2) //false
//     println ("Hello" == "Hello") //true
//     println ( 5.0! = 4.0) //true
//     println (3 in 0..5) //true
//     println (5 !in 0..5) //false
//
//
//
//     val sqr:
//     fun pointInsideCircle (
//         x: Double, y: Double,
//         x0: Double,y0: Double, r: Double) =
//         sqr(x- x0)+ sqr(y - y0) <= sqr(r)
//     val x = 0.5
//     val y = 0.5
//)
//        //пересечение: логическое И
//    if ( pointInsideCircle( x,y, 0.0, 0.0, 1.0)
//    && pointInsideCircle (x, y, 1.0, 1.0, 1.0)
//{ TODO()}
//)
////логическое И: &&
//// false && false == false
//// true && false == false
//// false && true == false
//// true && true == true
//
//
////обьединение: логическое ИЛИ: ||
//false || false == false
//true || true == true
//        val x = 0.5
//        val y = 0.5
//if (pointInsideCircle( x, y, 0.0, 0.0, 1.0))
//        || pointInsideCircle ( x, y, 1.0, 1.0, 1.0) {
//    TODO()
//}
//val x = 0.5
//val y = 0.5
//if (!pointInsideCircle(x, y, 0.0, 0.0, 1.0))
//   fun minBiRoot (a: Double, b: Double, c:Double) : Double{
//       if (a == 0.0){
//           if (b == 0.0) return Double.NaN
//           val bc = -c/b
//           if( bc< 0.0) return Double.NaN
//           return - sqrt(bc)
//           val d = discriminant (a, b, c)
//           if (d < 0.0) return Double.NaN
//           val y1 = (-b + sqrt(d)) / (2* a)
//           val y2 = (-b - sqrt(d)) / (2* a)
//           val =
//       }
//   }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

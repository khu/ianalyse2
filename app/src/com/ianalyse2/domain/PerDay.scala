package com.ianalyse2.domain

class PerDay(val builds: List[Builds]) {
  def asJson = {
//    var allPassed = List[String]()
//    var allFailed = List[String]()
//    for (val build <- builds) {
//      if (build.passed) {
//        allPassed = allPassed ::: List(build.toPerBuild)
//      } else {
//        allFailed = allFailed ::: List(build.toPerBuild)
//      }
//    }
//       {
//            name: 'Failed',
//            color: '#B90016',
//            type: 'column',
//            data: [[Date.UTC(2010, 3,11), 3],[Date.UTC(2010, 3,13), 7],[Date.UTC(2010, 3,12), 9]]
//        },{
//            name: 'Passed',
//            color: '#52A622',
//            type: 'column',
//            data: [[Date.UTC(2010, 3,11), 12],[Date.UTC(2010, 3,13), 12],[Date.UTC(2010, 3,12), 13]]
//        }
//        ,{
//            name: 'Pass rate',
//            color: '#4572A7',
//            type: 'line',
//            yAxis: 1,
//            data: [[Date.UTC(2010, 3, 16), 12], [Date.UTC(2010, 3,19), 12], [Date.UTC(2010, 3,20), 19]]
//
//        }
//    String.format("""
//{
//    "passed"   : %s,
//    "failed" : %s
//}
//""", allPassed.mkString("[", ",", "]"), allFailed.mkString("[", ",", "]"))
    ""
  }
}
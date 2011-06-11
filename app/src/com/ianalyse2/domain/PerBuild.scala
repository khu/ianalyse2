package com.ianalyse2.domain

import org.joda.time.DateTimeZone

class PerBuild(val allbuilds: List[Build]) {
  def asJson = {
    var allPassed = List[String]()
    var allFailed = List[String]()
    for (val build <- allbuilds) {
      if (build.passed) {
        allPassed = allPassed ::: List(build.toPerBuild(DateTimeZone.getDefault))
      } else{
        allFailed = allFailed ::: List(build.toPerBuild(DateTimeZone.getDefault))
      }
    }
    String.format("""
{
    "passed"   : %s,
    "failed" : %s
}
""",allPassed.mkString("[", ",", "]"), allFailed.mkString("[", ",", "]"))
  }
}
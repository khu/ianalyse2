package com.ianalyse2.domain

class PerBuild(val allbuilds: List[Builds]) {
  def asJson = {
    var allPassed = List[String]()
    var allFailed = List[String]()
    for (val builds <- allbuilds) {
      if (build.passed) {
        allPassed = allPassed ::: List(build.toPerBuild)
      } else{
        allFailed = allFailed ::: List(build.toPerBuild)
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
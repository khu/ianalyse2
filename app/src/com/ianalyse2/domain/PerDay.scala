package com.ianalyse2.domain

import org.joda.time.DateTime
import collection.mutable.HashMap

class PerDay(val byDays:HashMap[DateTime, Builds]) {
  def asJson = {
    var passed = List[String]()
    var failed = List[String]()
    var rate = List[String]()

    for(val day <- byDays) {
      val datetime = day._1.getMillis.toString
      val failedCount = day._2.failedCount
      val passCount = day._2.passCount
      val passRate = day._2.passRate
      failed = failed ::: List(toJavaScriptStr(datetime, failedCount.toString))
      passed = passed ::: List(toJavaScriptStr(datetime, passCount.toString))
      rate = rate ::: List(toJavaScriptStr(datetime, passRate.toString))
    }
        String.format("""
    {
        "passed"   : %s,
        "failed" : %s,
        "rate": %s
    }
    """, passed.mkString("[", ",", "]"), failed.mkString("[", ",", "]"),
        rate.mkString("[", ",", "]"))
  }

  def toJavaScriptStr(dateTime:String, number:String) = {
    String.format("[%s,%s]", dateTime, number)
  }
}
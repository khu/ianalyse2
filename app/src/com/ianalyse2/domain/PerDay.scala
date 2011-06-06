package com.ianalyse2.domain

import org.joda.time.DateTime
import collection.mutable.HashMap
import collection.Seq

class PerDay(val byDays:HashMap[DateTime, Builds]) {
  def asJson = {
    var passed = List[String]()
    var failed = List[String]()
    var rate = List[String]()
    val days: Seq[DateTime] = getLatestNDays(byDays, 10)


    for(val day <- days) {
      val datetime = day.getMillis.toString
      val failedCount = byDays(day).failedCount
      val passCount = byDays(day).passCount
      val passRate = byDays(day).passRate
      failed = failed ::: List(toJavaScriptStr(datetime, failedCount.toString))
      passed = passed ::: List(toJavaScriptStr(datetime, passCount.toString))
      rate = rate ::: List(toJavaScriptStr(datetime, passRate.toString))
    }
        String.format("""
{
    "passed"   : %s,
    "failed" : %s,
    "rate" : %s
}
""", passed.mkString("[", ",", "]"), failed.mkString("[", ",", "]"),
        rate.mkString("[", ",", "]"))
  }

  def getLatestNDays(byDays:HashMap[DateTime, Builds], number:Int) = {
    var ordered: Seq[DateTime] = byDays.keys.toSeq.sortBy(x => x.getMillis)
    ordered = ordered.take(number)
    ordered
  }

  def toJavaScriptStr(dateTime:String, number:String) = {
    String.format("[%s,%s]", dateTime, number)
  }
}
package com.ianalyse2.domain

import org.joda.time.{DateTimeZone, DateTime}

class Build(val name: String,
            val number: String,
            val startTime: DateTime,
            val duration: Int,
            val passed: Boolean,
            val commitors: List[String],
            val failedTests: List[String]) {

  def toJavaScriptDate = {
    toDateTime.getMillis
  }

  def toJavaScriptTime = {
    startTime.hourOfDay.get
  }

  def toPerBuild = {
    String.format("[%s,%d,%s]",
      toJavaScriptDate.toString,
      new Integer(toJavaScriptTime),
      number
    )
  }

  def toDateTime = {
    new DateTime(startTime.getYear, startTime.getMonthOfYear, startTime.getDayOfMonth,
      0, 0, 0, 0, DateTimeZone.UTC)
  }


}

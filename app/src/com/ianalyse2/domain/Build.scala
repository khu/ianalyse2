package com.ianalyse2.domain

import org.joda.time.{DateTimeZone, DateTime}

class Build(val name: String,
            val number: String,
            val startTime: DateTime,
            val duration: Int,
            val passed: Boolean,
            val commitors: List[String],
            val failedTests: List[String]) {

  def toJavaScriptDate(timezone: DateTimeZone) = {
    toDateTime(timezone).getMillis
  }

  def toJavaScriptTime(timezone: DateTimeZone) = {
    startTime.toDateTime(timezone).hourOfDay.get
  }

  def toPerBuild(timezone: DateTimeZone) = {
    String.format("[%s,%d,%s]",
      toJavaScriptDate(timezone).toString,
      new Integer(toJavaScriptTime(timezone)),
      number
    )
  }

  def toDateTime(timezone: DateTimeZone) = {
    new DateTime(startTime.getYear, startTime.getMonthOfYear, startTime.getDayOfMonth,
      0, 0, 0, 0, DateTimeZone.UTC).toDateTime(timezone)
  }
}

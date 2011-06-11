package com.ianalyse2.domain

import com.ianalyse2.util.LogHelper
import collection.mutable.HashMap
import collection.Seq
import org.joda.time.{DateTimeZone, DateTime}


class Builds extends LogHelper {
  var builds: List[Build] = List[Build]()

  def failedCount = {
    length - passCount
  }

  def commitResults = {
    val commitResults: CommitResults = new CommitResults()
    for (build <- builds) {
      commitResults.add(build.passed, build.commitors)
    }
    commitResults;
  }

  def perBuild = {
    new PerBuild(builds)
  }

  def perDay = {
    new PerDay(orderByDay)
  }

  def orderByDay:HashMap[DateTime, Builds] = {
    var ordered = new HashMap[DateTime, Builds]() {
      override def default(key: DateTime) = new Builds()
    };
    for (build <- builds) {
      val dateTime: DateTime = build.toDateTime(DateTimeZone.getDefault)
      var orderedBuilds: Builds = ordered(dateTime)
      orderedBuilds = orderedBuilds.:::(List(build))
      ordered += (dateTime -> orderedBuilds)
    }
    ordered
  }

  def failedTests = {
    var ordered = new HashMap[String, Int]() {
      override def default(key: String) = 0
    };
    for (build <- builds) {
      if (!build.passed) {
        for (test <- build.failedTests) {
          var failedTimes :Int = ordered(test)
          failedTimes = failedTimes + 1
          ordered += (test -> failedTimes)
        }
      }
    }
    val sorted: List[(String, Int)] = ordered.toList.sortWith((t1, t2) => t1._2 > t2._2)
    val top10: List[(String, Int)] = sorted.take(10)
    top10
  }

  def passRate = {
    if (length > 0) {
      val mc = java.math.MathContext.DECIMAL128
      val result: BigDecimal = BigDecimal(passCount)(mc)./(BigDecimal(length))
      val percentage = result(mc) * BigDecimal(100)
      val a = percentage.setScale(1, scala.math.BigDecimal.RoundingMode.HALF_DOWN)
      a
    } else {
      BigDecimal(0)
    }

  }

  def passCount = {
    builds.filter(_.passed).size
  }

  def length = {
    builds.size
  }

  def avgDuration = {
    var count = 0;
    var totalDuration = 0;
    for (build <- builds) {
      if (build.passed) {
        count += 1
        totalDuration += build.duration
      }
    }
    if (count == 0) {
      0
    } else {
      totalDuration / count
    }

  }


  def add(build: Build) = {
    builds = builds ::: List(build)
  }

  def get(index: Int) = {
    builds(index)
  }

  def :::(builds: List[Build]): Builds = {
    val tempbuilds = this.builds.:::(builds)
    val newBuilds = new Builds
    newBuilds.builds = tempbuilds
    newBuilds
  }

  def :::(build: Build): Builds = {
    val tempbuilds = this.builds.:::(List(build))
    val newBuilds = new Builds
    newBuilds.builds = tempbuilds
    newBuilds
  }

  def :::(parseInBuilds: Builds): Builds = {
    :::(parseInBuilds.builds)
  }
}
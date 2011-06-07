package com.ianalyse2.domain

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import collection.mutable.HashMap
import org.joda.time.{DateTimeZone, DateTime}


class BuildsTest extends Spec with ShouldMatchers {
  describe("analyse the builds") {

    it("should handle the empty builds correctly") {
      val builds = new Builds()
      builds.passCount should be === 0
      builds.failedCount should be === 0
      builds.passRate should be === BigDecimal(0)
    }

    it("should calculate the pass count") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List(), List[String]()))
      builds.passCount should be === 2
    }

    it("should return zero when there is no build in collection") {
      val builds = new Builds()
      builds.passCount should be === 0
    }

    it("should calculate the total count") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List(), List[String]()))
      builds.length should be === 3
    }

    it("should consider the builds as from the same day.") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 7, 1, 1, 1), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 7, 1, 1), 0, false, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 2, 1, 1, 1), 0, false, List(), List[String]()))
      builds.orderByDay.size should be === 1
    }
    
    it("should consider the builds as from the 2 different day.") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 1, 1, 1), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 2, 1, 1, 1, 1), 0, false, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 3, 8, 1, 1), 0, false, List(), List[String]()))
      builds.orderByDay.size should be === 2
      builds.orderByDay(new DateTime(2010, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC)).length should be === 2
      builds.orderByDay(new DateTime(2010, 1, 2, 0, 0, 0, 0, DateTimeZone.UTC)).length should be === 1

    }

  }
}
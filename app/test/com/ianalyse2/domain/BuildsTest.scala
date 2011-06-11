package com.ianalyse2.domain

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import collection.mutable.HashMap
import org.joda.time.{DateTimeZone, DateTime}
import collection.Seq


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

    it("should report there is no failed tests.") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 7, 1, 1, 1), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 7, 1, 1), 0, true, List(), List[String]()))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 2, 1, 1, 1), 0, true, List(), List[String]()))
      builds.failedTests.size should be === 0
    }

    it("should report there is mutiple failed tests.") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 7, 1, 1, 1), 0, false, List(), List("A#1", "B#1")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 7, 1, 1), 0, false, List(), List("A#1")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 2, 1, 1, 1), 0, false, List(), List("B#2")))
      builds.failedTests.size should be === 3
      builds.failedTests(0) should be === ("A#1", 2)
      builds.failedTests(1) should be === ("B#1", 1)
      builds.failedTests(2) should be === ("B#2", 1)
    }

    it("should sort this failed in order") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 7, 1, 1, 1), 0, false, List(), List("A#1", "B#1", "A#2")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 7, 1, 1), 0, false, List(), List("A#1", "A#2")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 2, 1, 1, 1), 0, false, List(), List("A#1", "B#2")))
      builds.failedTests.size should be === 4

      builds.failedTests(0) should be === ("A#1", 3)
      builds.failedTests(1) should be === ("A#2", 2)
    }

    it("should only take the first top 10 failed tests") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 7, 1, 1, 1), 0, false, List(), List("A#1", "A#2", "A#3", "A#4")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 1, 7, 1, 1), 0, false, List(), List("A#5", "A#6", "A#7", "A#8")))
      builds.add(new Build("a", "111", new DateTime(2010, 1, 1, 2, 1, 1, 1), 0, false, List(), List("A#9", "A#10", "A#11", "A#12")))
      builds.failedTests.size should be === 10

      builds.failedTests(9) should be === ("A#9", 1)
    }

  }
}
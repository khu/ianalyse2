package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, Spec}

class CommitorSummaryTest extends Spec with ShouldMatchers with BeforeAndAfterEach {
  override def beforeEach() {
  }

  override def afterEach() {
  }

  describe("analyse the jobs") {
    it("should pass the single the commitor") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(), 0, true, List("james"), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List("james"), List("case1")))
      builds.commitResults.asJson should be === String.format("""
{
    "names"  : %s,
    "passed"   : %s,
    "failed" : %s
}
""", "[\"james\"]", "[1]", "[1]");
    }

    it("should pass the single the jobs") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(), 0, true, List("james"), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, true, List("jane", "james"), List[String]()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List("bob"), List("case1")))
      builds.commitResults.asJson should be === String.format("""
{
    "names"  : %s,
    "passed"   : %s,
    "failed" : %s
}
""", "[\"bob\",\"james\",\"jane\"]", "[0,2,1]", "[1,0,0]");
    }

  }
}
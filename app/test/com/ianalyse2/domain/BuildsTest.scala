package com.ianalyse2.domain

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.joda.time.DateTime


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
      builds.add(new Build("a", "111", new DateTime(), 0, true, List()))
      builds.add(new Build("a", "111", new DateTime(), 0, true, List()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List()))
      builds.passCount should be === 2
    }

    it("should return zero when there is no build in collection") {
      val builds = new Builds()
      builds.passCount should be === 0
    }

    it("should calculate the total count") {
      val builds = new Builds()
      builds.add(new Build("a", "111", new DateTime(), 0, true, List()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List()))
      builds.add(new Build("a", "111", new DateTime(), 0, false, List()))
      builds.length should be === 3
    }
  }
}
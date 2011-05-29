package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfterEach, Spec}
import com.ianalyse2.ProjectFixture


class PerDayTest
  extends Spec with ShouldMatchers with BeforeAndAfterEach with ProjectFixture {
  describe("analyse the jobs") {
    it("should pass the single the jobs") {
      val project: Project = somePassedProject
      project.perDay.asJson should be === String.format("""
{
    "passed"   : %s,
    "failed" : %s,
    "passRate" : %s
}
""", "[[1291766400000,0],[1292803200000,0],[1291852800000,1]]",
      "[[1291766400000,1],[1292803200000,1],[1291852800000,0]]",
     "[[1291766400000,0.0],[1292803200000,0.0],[1291852800000,100.0]]"
      );
    }

  }
}
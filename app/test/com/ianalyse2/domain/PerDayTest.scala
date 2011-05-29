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
""", "[[1291852800000,5,2]]", "[[1292803200000,14,3],[1291766400000,3,1]]",
     "[[1292803200000,14,3],[1291766400000,3,1]]"
      );
    }

  }
}
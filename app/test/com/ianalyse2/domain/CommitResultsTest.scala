package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{Spec, BeforeAndAfterEach}
import org.joda.time.DateTime


class CommitResultsTest  extends Spec with ShouldMatchers with BeforeAndAfterEach {
  describe("analyse the jobs") {
    it("should pass the single the commitor") {
      val commitResults: CommitResults = new CommitResults()
      commitResults.add(true, List("James"))
      commitResults.passedCount("James") should be === 1
      commitResults.failedCount("James") should be === 0
    }

    it("should parse different commitor name separately") {
      val commitResults: CommitResults = new CommitResults()
      commitResults.add(true, List("James", "Bob"))
      commitResults.passedCount("Bob") should be === 1
      commitResults.failedCount("Bob") should be === 0
    }

    it("should parse multiple commitor and result set separately") {
      val commitResults: CommitResults = new CommitResults()
      commitResults.add(true, List("James", "Bob"))
      commitResults.add(false, List("James"))
      commitResults.passedCount("James") should be === 1
      commitResults.failedCount("James") should be === 1
    }

  }
}
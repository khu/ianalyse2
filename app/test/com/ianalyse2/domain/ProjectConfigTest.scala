package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec


class ProjectConfigTest extends Spec with ShouldMatchers {
  describe("analyse the jobs") {
    it("should pass the all the jobs") {
      val config = new ProjectConfig(
        "analytics-server",
        "http://deadlock.netbeans.org/hudson/job/analytics-server/")
      val project = config.instantiate;
      val build = project.get(0);
      build.number.toInt should be >= 1049
    }
  }
}


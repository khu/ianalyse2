package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfterEach, Spec}

class ProjectsConfigTest extends Spec with ShouldMatchers with BeforeAndAfterEach {
  var configs: ProjectsConfig = new ProjectsConfig("http://deadlock.netbeans.org/hudson/api/xml");

  override def beforeEach() {
    Projects.reset
  }

  override def afterEach() {
    Projects.reset
    configs.destory
    configs.stop
  }

  describe("analyse the jobs") {
    it("should parse get the project") {
      configs = new ProjectsConfig("http://deadlock.netbeans.org/hudson/api/xml")
      configs.start
      configs.init
      Thread.sleep(20000)
      Projects.length should be > 0
    }

    it("should pass the all the jobs") {
      configs = new ProjectsConfig("http://deadlock.netbeans.org/hudson/api/xml");
      configs.init
      configs.count should be > 0
    }

    it("should parse the single config correct") {
      configs = new ProjectsConfig("http://deadlock.netbeans.org/hudson/api/xml")
      configs.init
      val config: ProjectConfig = configs.get(0)
      config.name should be === "analytics-server"
      config.url should be === "http://deadlock.netbeans.org/hudson/job/analytics-server/"
    }


  }
}
package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, Spec}

class PassRatesTest extends Spec with ShouldMatchers with BeforeAndAfterEach {

  override def beforeEach() {
    Projects.reset()
  }

  override def afterEach() {
    Projects.reset()
  }

  def somePassedProject: Project = {
    val b1 = new Build("analystic-server", "1", new DateTime(), 12, false, List("jack", "ying"))
    val b2 = new Build("analystic-server", "2", new DateTime(), 12, true, List("jack"))
    val b3 = new Build("analystic-server", "3", new DateTime(), 12, false, List("sun"))

    var builds = new Builds()
    builds = builds.:::(b1)
    builds = builds.:::(b2)
    builds = builds.:::(b3)

    val project: Project = new Project(new ProjectConfig("analystic-server", "someurl"))
    project.update(builds)
    project
  }

  def allPassedProject: Project = {
    val b4 = new Build("lnp", "100", new DateTime(), 112, true, List("jack"))
    val b5 = new Build("lnp", "101", new DateTime(), 132, true, List("jack"))
    val b6 = new Build("lnp", "102", new DateTime(), 112, true, List("sun"))

    var builds2 = new Builds()
    builds2 = builds2.:::(b4)
    builds2 = builds2.:::(b5)
    builds2 = builds2.:::(b6)

    val project2: Project = new Project(new ProjectConfig("lnp", "someurl"))
    project2.update(builds2)
    project2
  }

  def noBuildProject: Project = {
    val project2: Project = new Project(new ProjectConfig("lnp", "someurl"))
    project2.update(new Builds())
    project2
  }

  describe("analyse the jobs") {
    it("should pass the single the jobs") {
      Projects.update(somePassedProject);
      Projects.passRates.asJson should be === String.format("""
{
    "names"  : %s,
    "passed"   : %s,
    "failed" : %s,
    "rate"   : %s
}
""", "[\"analystic-server\"]", "[1]", "[2]", "[33.3]");
    }

    it("should pass the multiple jobs on the same day") {
      Projects.update(somePassedProject);
      Projects.update(allPassedProject);
      Projects.passRates.asJson should be === String.format("""
{
    "names"  : %s,
    "passed"   : %s,
    "failed" : %s,
    "rate"   : %s
}
""", "[\"analystic-server\",\"lnp\"]","[1,3]", "[2,0]", "[33.3,100.0]")
    }
  }
}
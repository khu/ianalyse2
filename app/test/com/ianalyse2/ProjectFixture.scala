package com.ianalyse2

import domain.{ProjectConfig, Builds, Build, Project}
import org.joda.time.DateTime

trait ProjectFixture {

  def somePassedProject: Project = {
    val b1 = new Build("analystic-server", "1", new DateTime(2010, 12, 8, 3, 1, 1, 0), 12, false, List("jack", "ying"), List("case1"))
    val b2 = new Build("analystic-server", "2", new DateTime(2010, 12, 9, 05, 1, 1, 0), 12, true, List("jack"), List[String]())
    val b3 = new Build("analystic-server", "3", new DateTime(2010, 12, 20, 14, 0, 3, 0), 12, false, List("sun"), List("case2"))

    var builds = new Builds()
    builds = builds.:::(b1)
    builds = builds.:::(b2)
    builds = builds.:::(b3)

    val project: Project = new Project(new ProjectConfig("analystic-server", "someurl"))
    project.update(builds)
    project
  }

  def allPassedProject: Project = {
    val b4 = new Build("lnp", "100", new DateTime(), 112, true, List("jack"), List[String]())
    val b5 = new Build("lnp", "101", new DateTime(), 132, true, List("jack"), List[String]())
    val b6 = new Build("lnp", "102", new DateTime(), 112, true, List("sun"), List[String]())

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
}
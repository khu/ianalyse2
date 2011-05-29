package com.ianalyse2.domain

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec


class ProjectsTest extends Spec with ShouldMatchers {
  describe("analyse the builds") {
    it("should parse the project to several builds") {
      val projects = Projects
      val project1: Project = new Project(new ProjectConfig("analystic-server", "someurl"))
      val project2: Project = new Project(new ProjectConfig("name", "someurl"))
      projects.projects.put(project1.name, project1)
      projects.projects.put(project2.name, project2)
      System.out.println(projects.names)
    }
  }
}
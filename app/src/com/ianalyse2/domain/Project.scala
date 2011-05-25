package com.ianalyse2.domain


class Project(val config: ProjectConfig) {
  private var builds: Builds = new Builds()
  //private var builds:Builds;

  def name = {
    config.name
  }

  def commitorSummary = {
    builds.commitResults
  }

  def perBuild = {
    builds.perBuild
  }

  def perDay = {
    builds.perDay
  }

  def length = {
    builds.length
  }

  def get(index: Int) = {
    builds.get(index);
  }

  def passed = {
    if (builds.length > 1) {
      val i: Int = builds.length - 1
      builds.builds(i).passed
    } else {
      false
    }
  }

  def passCount = {
    builds.passCount
  }

  def failedCount = {
    builds.failedCount
  }

  def passRate = {
    builds.passRate
  }

  def avgDuration = {
    builds.avgDuration / 60000
  }

  def update(builds: Builds) = {
    this.builds = this.builds ::: builds
  }

}
package com.ianalyse2.domain

import collection.mutable.LinkedHashMap
import collection.JavaConversions
import java.util.ArrayList
import com.ianalyse2.util.LogHelper

object Projects  extends LogHelper{
  var projects: LinkedHashMap[String, Project]
  = new LinkedHashMap[String, Project]();

  def passRates = {
    new PassRates()
  }
  def passedCount = {
    var i = 0;
    for (project <- projects) {
      if (project._2.passed) {
        i = i+ 1
      }
    }
    i
  }

  def failedCount = {
    length - passedCount
  }
  def reset() {
    projects.clear
  }

  def update(project: Project) {
    projects.put(project.config.name, project)
  }

  def find(name: String) = {
    logger.info("name is " + name)
    projects(name)
  }

  def inOrder = {
    val sorted = projects.toSeq.sortBy(_._1)
    var names: List[Project] = List()
    for (val summary <- sorted) {
      names = names ::: List(summary._2)
    }
    names
  }

  //  def get(index: Int) = {
  //    projects(index)
  //  }
  def count = {
    length
  }

  def names = {
    JavaConversions.asJavaList(projects.keys.toList)
  }

  @Override
  def remove() {
    //
  }

  def length = {
    projects.size
  }
}
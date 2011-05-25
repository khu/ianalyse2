package com.ianalyse2.domain

import collection.mutable.HashMap
import collection.Seq


class CommitResults {
  val commitorsSummary = new HashMap[String, CommitResult](){
    override def default(key:String) = new CommitResult(0, 0)
  };

  def add(passed: Boolean, commitors:List[String]) = {
    for(commitor <- commitors) {
      val result: CommitResult = commitorsSummary(commitor)
      commitorsSummary += (commitor -> result.updateResult(passed))
    }
  }

  def passedCount(name:String) = {
      commitorsSummary(name).passedCount
  }

  def failedCount(name:String) = {
      commitorsSummary(name).failedCount
  }

  def asJson = {
    var passCount: List[Int] = List()
    var failedCount: List[Int] = List()
    var names: List[String] = List()
    val sorted: Seq[(String, CommitResult)] = commitorsSummary.toSeq.sortBy(_._1)
    for (val summary <- sorted) {
      names = names ::: List(summary._1)
      passCount = passCount ::: List(summary._2.passedCount)
      failedCount = failedCount ::: List(summary._2.failedCount)
    }
    String.format("""
{
    "names"  : %s,
    "passed"   : %s,
    "failed" : %s
}
""",
      names.mkString("[\"", "\",\"", "\"]"),
      passCount.mkString("[", ",", "]"),
      failedCount.mkString("[", ",", "]"))
  }
}
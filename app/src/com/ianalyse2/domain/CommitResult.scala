package com.ianalyse2.domain


class CommitResult(val passedCount:Int, val failedCount:Int) {

  def updateResult(result:Boolean) = {
    if (result) {
      new CommitResult(passedCount + 1, failedCount)
    } else {
      new CommitResult(passedCount, failedCount + 1)
    }
  }
}
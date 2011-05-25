package com.ianalyse2.actors

import actors.DaemonActor


trait DaemonActorWrapper extends DaemonActor {

  def deamonactor(body: => Unit): DaemonActorWrapper = {
    val a = new DaemonActorWrapper {
      def act() = body
    }
    a.start()
    a
  }
}


package org.sbot.task

import akka.actor.{Props, ActorRef}
/**
 * @author : const_
 */
object TaskInstantiator {

  def instantiate(taskClass: Class[Task], taskExecutor: TaskExecutor, properties: AnyRef*): ActorRef = {
    taskExecutor.system.actorOf(Props(taskClass, properties), taskClass.getSimpleName)
  }
}

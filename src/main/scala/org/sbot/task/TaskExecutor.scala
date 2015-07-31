package org.sbot.task


import akka.actor.{Props, ActorSystem, ActorRef}
import akka.kernel.Bootable
import akka.util.Timeout

import scala.concurrent.{Future, ExecutionContext, Awaitable, Await}
import scala.concurrent.duration._
import akka.pattern.ask

/**
 * @author : const_
 */

case object Start

case object Stop

case object Pause

case object AskIfBlocking

abstract class TaskExecutor(tasks: IndexedSeq[ActorRef]) extends Bootable {

  val system = ActorSystem(getClass.getSimpleName)

  val blockingMap = createBlockingMap()

  implicit val Timeout = new Timeout(10 seconds)

  val AskIfBlockingTimeout = 60 seconds
  val TaskTimeout = 60 seconds

  private def createBlockingMap(): Map[ActorRef, Boolean] = {
    tasks.map { task =>
      val result = Await.result(task ? AskIfBlocking, AskIfBlockingTimeout).asInstanceOf[Boolean]
      task -> result
    }.toMap
  }

  def startup() = {
    while(!system.isTerminated) {
      loop()
    }
  }

  def shutdown() = {

  }

  def loop(): Int = {
    tasks.foreach { task =>
      blockingMap.get(task).map { blocking =>
        if(blocking) {
          Await.result(task ? Start, TaskTimeout)
        } else {
          task ! Start
          None
        }
      } match {
        case Some(sleep: Int) =>
          Thread.sleep(sleep)
        case _           => println("no block")
      }
    }
    10
  }
}

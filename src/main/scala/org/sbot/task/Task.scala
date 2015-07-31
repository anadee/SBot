package org.sbot.task

import akka.actor.Actor

/**
 * @author : const_
 */

object Task {
  val DID_NOT_VALIDATE = -1
}

abstract class Task(taskExecutor: TaskExecutor) extends Actor {

  def validate: Boolean

  def execute: Int

//  def receive(): Unit = {
//    if(validate) {
//      sender() ! execute
//      println("d")
//    } else {
//      sender() ! Task.DID_NOT_VALIDATE
//      println("d")
//    }
//  }
}


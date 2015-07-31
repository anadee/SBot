package org.sbot.util

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaj.http.Http

/**
 * @author : const_
 */
object Internet {

  def createRequest(url: String) =
    Http(url)
  
  def downloadString(url: String) =
    createRequest(url).asString.body


  def downloadBytes(url: String) =
    Future {
      createRequest(url).asBytes.body
    }
}

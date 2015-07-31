package org.sbot.runetek.api

import com.google.inject.Inject

/**
 * @author : const_
 */
trait ContextProvider {

  @Inject
  val context: Context = _

}

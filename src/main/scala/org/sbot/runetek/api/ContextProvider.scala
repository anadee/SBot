package org.sbot.runetek.api

import com.google.inject.Inject

/**
 * @author : const_
 */
trait ContextProvider {

  @Inject
  var context: Context = _

}

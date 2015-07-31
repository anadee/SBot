package org.sbot.loader

import java.net.{URL, URLClassLoader}

/**
 * @author : const_
 */
case class ClientClassLoader(urls: Array[URL]) extends URLClassLoader(urls) {

}

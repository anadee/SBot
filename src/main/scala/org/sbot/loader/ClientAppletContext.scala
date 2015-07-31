package org.sbot.loader

import java.applet.{AppletContext, AudioClip, Applet}
import java.awt.{Desktop, Image}
import java.io.InputStream
import java.net.URL
import java.util.Collections
import javax.imageio.ImageIO

import scala.collection.mutable


/**
 * @author : const_
 */
class ClientAppletContext extends AppletContext {
  private val streams: java.util.HashMap[String, InputStream] = new java.util.HashMap[String, InputStream]
  private var applet: Applet = null
  private var status: String = null

  def getAudioClip(url: URL): AudioClip = {
    Applet.newAudioClip(url)
  }

  def getImage(url: URL): Image = {
    ImageIO.read(url)
  }

  def getApplet(name: String): Applet = {
    applet
  }

  def getApplets: java.util.Enumeration[Applet] = {
    val applets: java.util.LinkedList[Applet] = new java.util.LinkedList()
    applets.add(applet)
    Collections.enumeration(applets)
  }

  def showDocument(url: URL) {
    if (Desktop.isDesktopSupported) {
        Desktop.getDesktop.browse(url.toURI)
      }
  }

  def showDocument(url: URL, target: String) {
    if (Desktop.isDesktopSupported) {
        Desktop.getDesktop.browse(url.toURI)
      }
  }

  def showStatus(status: String) {
    this.status = status
  }

  def setStream(key: String, stream: InputStream) {
    if (streams.containsKey(key)) {
      streams.remove(key)
    }
    streams.put(key, stream)
  }

  def getStream(key: String): InputStream = {
    streams.get(key)
  }

  def getStreamKeys: java.util.Iterator[String] = {
    streams.keySet.iterator
  }

  def setApplet(applet: Applet) {
    this.applet = applet
  }

  def getStatus: String = {
    status
  }
}

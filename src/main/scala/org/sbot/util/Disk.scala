package org.sbot.util

import java.io.File
import java.nio.file.Files

/**
 * @author : const_
 */
object Disk {

  def read(file: File) = Files.readAllBytes(file.toPath)

  def write(file: File, bytes: Array[Byte]) = Files.write(file.toPath, bytes)
}

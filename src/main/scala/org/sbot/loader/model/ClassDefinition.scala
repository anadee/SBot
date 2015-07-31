package org.sbot.loader.model

/**
 * @author : const_
 */
case class ClassDefinition(getters: Seq[GetterDefinition], methods: Seq[MethodDefinition], identifiedName: String, originalName: String) {

  def get(name: String): Option[GetterDefinition] = {
    getters.find(_.name == name)
  }
}

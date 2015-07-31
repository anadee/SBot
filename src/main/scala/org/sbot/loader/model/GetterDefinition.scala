package org.sbot.loader.model

/**
 * @author : const_
 */
case class GetterDefinition(name: String, signature: String, actualSig: String, fieldClass: String, fieldName: String, multiplier: Int, member: Boolean) {

  def isNonPrimitive: Boolean = {
    !(signature.contains("java") && signature.contains("/")) && !signature.matches("\\[{0,3}(I|Z|F|J|S|B|D|C)(;?)")
  }
}

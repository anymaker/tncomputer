package a2u.tn.utils.computer.formula;

/**
 * Part of formula
 */
public interface FormulaPart {

  /**
   * Return the internal formated representation
   * @param b StringBuilder for result
   * @param indent indent size in whitespace symbols
   */
  void toFormated(StringBuilder b, int indent);

  String toJson();
}

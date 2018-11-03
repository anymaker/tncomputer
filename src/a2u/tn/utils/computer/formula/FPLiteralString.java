package a2u.tn.utils.computer.formula;

/**
 * String literal
 */
public class FPLiteralString implements FormulaPart {
  private String value;

  public FPLiteralString(String string) {
    value = string;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "'"+ value +"'";
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".str[").append(value).append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'string', value: '" + value + "'}";
  }

}

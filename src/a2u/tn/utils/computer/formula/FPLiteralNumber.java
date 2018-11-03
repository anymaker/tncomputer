package a2u.tn.utils.computer.formula;

/**
 * Number literal
 */
public class FPLiteralNumber implements FormulaPart {
  private Number value;

  public FPLiteralNumber(String string) {
    boolean hasDot = string.indexOf('.') >= 0;
    if (hasDot) {
      value = Double.parseDouble(string);
    }
    else {
      value = Long.parseLong(string);
    }
  }

  public Number getValue() {
    return value;
  }


  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".num[").append(value).append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'number', value: " + value + "}";
  }

}

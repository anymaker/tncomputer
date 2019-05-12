package a2u.tn.utils.computer.formula;

/**
 * literal for value with type Object
 */
public class FPLiteral implements FormulaPart {
  private Object value;


  public FPLiteral(Object value) {
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    if (value == null) {
      return "null";
    }
    else if (value instanceof String) {
      return "'"+ value +"'";
    }
    else {
      return String.valueOf(value);
    }
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".obj[").append(toString()).append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'obj', value: " + toString() + "}";
  }
}

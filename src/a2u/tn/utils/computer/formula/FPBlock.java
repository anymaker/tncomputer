package a2u.tn.utils.computer.formula;

import a2u.tn.utils.computer.StringUtil;

/**
 * Part in parentheses, has the highest priority in computing
 */
public class FPBlock implements FormulaPart {
  private FormulaPart internal;

  public FPBlock(FormulaPart internal) {
    this.internal = internal;
  }

  public FormulaPart getOperation() {
    return internal;
  }



  @Override
  public String toString() {
    return "("+ internal.toString() +")";
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".block[");
    b.append("\n");
    StringUtil.repeat(b, " ", indent + 2);

    internal.toFormated(b, indent + 2);

    b.append("\n");
    StringUtil.repeat(b, " ", indent);
    b.append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'block', value: "+ internal.toJson() + "}";
  }

}

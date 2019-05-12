package a2u.tn.utils.computer.formula;

import a2u.tn.utils.computer.StringUtil;

/**
 * Part in parentheses, has the highest priority in computing
 */
public class FPBlock implements FormulaPart {
  private FormulaPart internal;

  public FPBlock() {
    internal = null;
  }
  public FPBlock(FormulaPart internal) {
    this.internal = internal;
  }

  public FormulaPart getOperation() {
    return internal;
  }

  public FPBlock addOperation(FPOperation.Command command, FPOperation operation) {
    if (internal == null) {
      internal = operation;
      return this;
    }
    else {
      if (internal instanceof FPOperation) {
        FPOperation op = (FPOperation) internal;
        internal = FPOperation.make(op, command, operation);
      }
      else {
        FPOperation op = new FPOperation();
        op.arg1 = internal;
        op.command = command;
        op.arg2 = operation;
        internal = op;
      }
      return this;
    }

  }


  @Override
  public String toString() {
    return "("+ (internal == null ? "" : internal.toString()) +")";
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".block[");
    b.append("\n");
    StringUtil.repeat(b, " ", indent + 2);

    if (internal != null) {
      internal.toFormated(b, indent + 2);
    }

    b.append("\n");
    StringUtil.repeat(b, " ", indent);
    b.append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'block', value: "+ (internal == null ? "" : internal.toJson()) + "}";
  }

}

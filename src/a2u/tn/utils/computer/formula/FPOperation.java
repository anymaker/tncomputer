package a2u.tn.utils.computer.formula;


import a2u.tn.utils.computer.StringUtil;

/**
 * Operation
 *
 * Every operation has two argument and command
 */
public class FPOperation implements FormulaPart {

  /**
   * Defined operations
   */
  public enum Command {
    mul(1),
    div(1),

    plus(2),
    minus(2),

    equal(3),
    notequal(3),

    like(3),
    notlike(3),

    great(3),
    greatEqual(3),
    less(3),
    lessEqual(3),

    in(3),
    notin(3),

    and(4),
    or(4),
    xor(4),

    addToDim(5);

    public int prior;
    Command(int prior) {
      this.prior = prior;
    }

    public boolean isPriorThen(Command oper) {
      return prior <= oper.prior;
    }

    public static Command fromString(String string) {
      switch (string) {
        case "*" : return Command.mul;
        case "/" : return Command.div;
        case "+" : return Command.plus;
        case "-" : return Command.minus;

        case "=" :  return Command.equal;
        case "!=" : return Command.notequal;

        case "like" :    return Command.like;
        case "notlike" : return Command.notlike;

        case ">" :  return Command.great;
        case ">=" : return Command.greatEqual;
        case "<" :  return Command.less;
        case "<=" : return Command.lessEqual;

        case "in" :  return Command.in;
        case "notin" :  return Command.notin;
        case "and" : return Command.and;
        case "or" :  return Command.or;
        case "xor" :  return Command.xor;

        case "," : return Command.addToDim;
        default: throw new IllegalArgumentException("invalid operation '"+ string +"'.");
      }
    }

    public static boolean isOperation(String string) {
      switch (string) {
        case "*" :
        case "/" :
        case "+" :
        case "-" :
        case "=" :
        case "!=" :
        case "like" :
        case "notlike" :
        case ">" :
        case ">=" :
        case "<" :
        case "<=" :
        case "in" :
        case "notin" :
        case "and" :
        case "or" :
        case "xor" :
        case "," :
          return true;

        default:
          return false;
      }
    }

    public String toQuery() {
      switch (this) {
        case mul: return "*";
        case div: return "/";
        case plus: return "+";
        case minus: return "-";
        case equal: return "=";
        case notequal: return "!=";
        case great: return ">";
        case greatEqual: return ">=";
        case less: return "<";
        case lessEqual: return "<=";
        default: return name();
      }
    }
  }

  Command command;  //command
  FormulaPart arg1; //first argument
  FormulaPart arg2; //seconf argument
  private FPOperation parent;  //parent operation, where this operation as the second argument (right part)

  FPOperation() {

  }


  public static FPOperation make(FormulaPart arg1, Command command, FormulaPart arg2) {
    if (arg2 instanceof FPOperation) {
      FPOperation root = (FPOperation) arg2;
      if (command.isPriorThen(root.getCommand())) {
        FPOperation top = findtop(command, root);
        FPOperation op1 = new FPOperation();
        op1.arg1    = arg1;
        op1.command = command;
        op1.arg2    = top.getArg1();

        top.arg1 = op1;
        return root;
      }
      else {
        FPOperation op1 = new FPOperation();
        op1.arg1    = arg1;
        op1.command = command;
        op1.arg2    = root;
        return op1;
      }
    }
    else {
      FPOperation op = new FPOperation();
      op.arg1    = arg1;
      op.command = command;
      op.arg2    = arg2;
      return op;
    }
  }
  private static FPOperation findtop(FPOperation.Command command, FPOperation top) {
    if (top.arg1 instanceof FPOperation) {
      FPOperation next = (FPOperation) top.arg1;
      if (command.isPriorThen(next.getCommand())) {
        return findtop(command, next);
      }
      else {
        return top;
      }
    }
    else {
      return top;
    }
  }


  private FPOperation(FPOperation parent) {
    this.parent = parent;
  }

  public FPOperation(Command command, FormulaPart arg1) {
    this.command = command;
    this.arg1 = arg1;
  }
  public FPOperation(Command command, FormulaPart arg1, FormulaPart arg2) {
    this.command = command;
    this.arg1 = arg1;
    this.arg2 = arg2;
  }

  public Command getCommand() {
    return command;
  }
  public FormulaPart getArg1() {
    return arg1;
  }
  public FormulaPart getArg2() {
    return arg2;
  }


  /**
   * Add operation with prioritize
   * @param command left operation
   * @param arg new second argument
   * @return top operation
   * @deprecated нужно использовать make
   */
  @Deprecated
  public FPOperation addOperation_1(Command command, FormulaPart arg) {
    if (arg1 == null) {
      arg1 = arg;
      this.command = command;
      return this;
    }
    else if (command == null) {
      this.arg2 = arg;
      FPOperation topNop = this;
      while (topNop.parent != null) {
        topNop = topNop.parent;
      }
      return topNop;
    }
    else if (this.command.prior < command.prior) {
      //this.arg2 = arg;

      FPOperation nop;
      if (this.parent == null) {
        nop = new FPOperation(null);
        nop.command = command;
        nop.arg1 = this;
        nop.arg2 = arg;
        this.parent = nop;
        return nop;
      }
      else {
        FPOperation parentOP = findEqualParentOrTop(command.prior);
        nop = new FPOperation(parentOP.parent);
        nop.command = command;
        nop.arg1 = parentOP;
        nop.arg2 = arg;
        parentOP.parent = nop;
      }
      return nop;
    }
    else {
      FPOperation nop = new FPOperation(this);
      nop.command = command;
      nop.arg1 = arg;
      nop.parent = this;
      this.arg2 = nop;


      return nop;
    }

  }

  private FPOperation findEqualParentOrTop(int prior) {
    FPOperation op = this;
    while (op.command.prior < prior) {
      if (op.parent == null) {
        break;
      }
      op = op.parent;
    }
    return op;
  }

  @Override
  public String toString() {
    return arg1 +( command != null ? " " + command.toQuery() + " " + arg2 : "");
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    int indent2 = indent + 2;

    b.append(".operation[ ").append(command).append(": ");

    b.append("\n");
    StringUtil.repeat(b, " ", indent2);
    if (arg1 != null) {
      arg1.toFormated(b, indent2);
    }
    else {
      b.append("null");
    }
    b.append(",");

    b.append("\n");
    StringUtil.repeat(b, " ", indent2);
    if (arg2 != null) {
      arg2.toFormated(b, indent2);
    }
    else {
      b.append("null");
    }

    b.append("\n");
    StringUtil.repeat(b, " ", indent);
    b.append("]");
  }

  @Override
  public String toJson() {
    return "{type: 'operation', command: '" + command + "', arg1: " + (arg1==null?"null": arg1.toJson()) + ", arg2: " + (arg2==null?"null": arg2.toJson()) + "}";
  }

}

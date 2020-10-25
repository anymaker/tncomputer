package a2u.tn.utils.computer.formula;

import a2u.tn.utils.computer.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the formula for search
 */
public class Formula {

  /**
   * Root operation from which the calculation begins
   */
  private FormulaPart rootPart;


  /**
   * Constructor for instantiate formula by query
   * @param query search query with EQL expressions
   */
  public Formula(String query) {
    if (query == null || query.isEmpty()) {
      throw new FormulaException("Query can not be null or empty.");
    }

    rootPart = parseArg(query, new AtomicInteger(0));
  }

  /**
   * Constructor for instantiate formula by root element
   * @param rootPart root element
   */
  public Formula(FormulaPart rootPart) {
    this.rootPart = rootPart;
  }


  /**
   * Return Root operation
   * @return Root operation
   */
  public FormulaPart getRootPart() {
    return rootPart;
  }


  private FormulaPart parseArg(String text, AtomicInteger ix) {
    FormulaPart arg;

    Parser.Element part = Parser.extractElement(text, ix);
    if (part == null) {
      return null;
    }
    switch (part.type) {
      case FIELD:
        arg =  makeValue(part, text, ix);
        break;
      case STRING:
        arg = new FPLiteralString(part.value);
        break;
      case NUMBER:
        arg = new FPLiteralNumber(part.value);
        break;
      case BLOCK:
        arg = makeBlock(part.value);
        break;
      case IDENTY:
        arg = makeFunction(part, text, ix);
        break;
      case OPERATOR:
        if (part.value.equals("-") || part.value.equals("+")) {
          AtomicInteger ix2 = new AtomicInteger(ix.intValue());
          Parser.Element part2 = Parser.extractElement(text, ix2);
          if (part2 != null && part2.type == Parser.ElementType.NUMBER) {
            arg = new FPLiteralNumber(part.value + part2.value);
            ix.set(ix2.intValue());
          }
          else {
            throw new FormulaException("Illegal part "+ part +"' at position "+ (ix.get() - part.value.length()) +" in text '"+ text +"'.");
          }
        }
        else {
          throw new FormulaException("Illegal part "+ part +"' at position "+ (ix.get() - part.value.length()) +" in text '"+ text +"'.");
        }
        break;
      default: throw new FormulaException("Illegal part "+ part +"' at position "+ (ix.get() - part.value.length()) +" in text '"+ text +"'.");
    }

    Parser.Element part2 = Parser.extractElement(text, ix);
    if (part2 == null) {
      return arg;
    }

    FPOperation.Command command;

    switch (part2.type) {
      case OPERATOR:
        command = FPOperation.Command.fromString(part2.value);
        break;
      case IDENTY:
        if (FPOperation.Command.isOperation(part2.value)) {
          command = FPOperation.Command.fromString(part2.value);
        }
        else {
          throw new FormulaException("Illegal part "+ part2 +" at position "+ (ix.get() - part2.value.length()) +", expected operator. Source text '"+ text +"'.");
        }
        break;
      default:
        throw new FormulaException("Illegal part "+ part2 +" at position "+ (ix.get() - part2.value.length()) +", expected operator. Source text '"+ text +"'.");
    }

    return makeOperation(arg, command, text, ix);

  }

  private FPOperation makeOperation(FormulaPart arg1, FPOperation.Command command, String text, AtomicInteger ix) {
    FormulaPart arg2 = parseArg(text, ix);
    if (arg2 == null) {
      throw new FormulaException("Operation '"+ arg1 + " " + command +"' is unfinished at position "+ix.get()+", in text '"+ text +"'.");
    }

    FPOperation op = FPOperation.make(arg1, command, arg2);
    return op;
  }

  private FPValue makeValue(Parser.Element part, String text, AtomicInteger ix) {
    FPValue val = new FPValue(part.value);

    char next = Parser.getNextSymbol(text, ix.get());
    if (next == '(') {
      part = Parser.extractElement(text, ix);
      if (part == null) {
        throw new FormulaException("Unexpected end of expression '"+ StringUtil.substr(text, -15) +"'.");
      }

      if (part.value.length() > 0) {
        FormulaPart filter = parseArg(part.value, new AtomicInteger(0));
        val.setFilter(filter);
      }

      next = Parser.getNextSymbol(text, ix.get());
    }

    if (next == '.') {
      part = Parser.extractElement(text, ix);
      FPValue nextValue = makeValue(part, text, ix);
      val.setNext(nextValue);
    }

    return val;
  }

  private FPBlock makeBlock(String text) {
    FormulaPart arg = parseArg(text, new AtomicInteger(0));
    FPBlock blk = new FPBlock(arg);
    return blk;
  }

  private FPFunction makeFunction(Parser.Element part, String text, AtomicInteger ix) {

    String fnName = part.value;
    List<FormulaPart> fnParams = Collections.emptyList();

    char next = Parser.getNextSymbol(text, ix.get());
    if (next == '(') {
      part = Parser.extractElement(text, ix);
      if (part == null) {
        throw new InternalError("Unexpected end of expression '"+ StringUtil.substr(text, -15) +"'.");
      }

      FormulaPart params =  parseArg(part.value, new AtomicInteger(0));
      if (params != null) {
        fnParams = new ArrayList<>();
        convertPartToList(params, fnParams);
        Collections.reverse(fnParams);
      }

    }

    FPFunction fn = new FPFunction(fnName, fnParams);
    return fn;
  }
  private void convertPartToList(FormulaPart part, List<FormulaPart> list) {
    if (part instanceof FPOperation) {
      FPOperation op = (FPOperation) part;
      if (op.getCommand() == FPOperation.Command.addToDim) {
        list.add(op.getArg2());
        convertPartToList(op.getArg1(), list);
      }
      else {
        list.add(op);
      }
    }
    else {
      list.add(part);
    }
  }

  @Override
  public String toString() {
    return rootPart.toString();
  }

  /**
   * Return the internal formated formula representation
   * @return formated formula representation
   */
  public String toFormated() {
    StringBuilder b = new StringBuilder();
    rootPart.toFormated(b, 0);
    return b.toString();
  }

  public String toJson() {
    return rootPart.toJson();
  }

}

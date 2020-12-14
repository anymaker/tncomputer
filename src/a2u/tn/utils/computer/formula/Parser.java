package a2u.tn.utils.computer.formula;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Internal class for parsing search query
 */
class Parser {

  /**
   * Element types of query
   */
  public enum ElementType {
    FIELD,   // Field for extracting value in FPValue
    IDENTY,  // Identyfy function name for FPFunction or field name for FPValue
    BLOCK,   // Part in parentheses, has the highest priority in computing, FPBlock
    STRING,  // String literal
    NUMBER,  // Number literal
    OPERATOR // Operator in FPOperation
  }

  /**
   * Auxiliary structure for returning parsing result
   */
  static class Element {
    ElementType type;
    String value;

    @Override
    public String toString() {
      return type.name() + ": " + value;
    }
  }

  /**
   * Extract query element
   * @param text source text
   * @param ix   start index
   */
  public static Element extractElement(String text, AtomicInteger ix) {
    int len = text.length();
    if (len <= ix.get()) {
      return null;
    }

    Element elt = new Element();

    StringBuilder b = new StringBuilder();

    while (ix.get() < len) {
      char c = text.charAt(ix.get());
      if (c == '.') {
        elt.type = ElementType.FIELD;
        ix.addAndGet(1);
        getIdentify(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (c == '\'') {
        elt.type = ElementType.STRING;
        getString(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (c == '(') {
        elt.type = ElementType.BLOCK;
        getEntry(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (Character.isDigit(c)) {
        elt.type = ElementType.NUMBER;
        getNumber(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (Character.isLetter(c) || c == '@' || c == '#' || c == '$') {
        elt.type = ElementType.IDENTY;
        getIdentify(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (c == '=' || c == '+' || c == '-' || c == '/' || c == '*' || c == ',') {
        elt.type = ElementType.OPERATOR;
        elt.value = String.valueOf(c);
        ix.addAndGet(1);
        return elt;
      }
      if (c == '!' || c == '<' || c == '>' ) {
        elt.type = ElementType.OPERATOR;
        getOperator(b, text, ix);
        elt.value = b.toString();
        return elt;
      }
      if (isWhiteSpace(c)) {
        ix.addAndGet(1);
        continue;
      }

      throw new FormulaException("Invalid character '"+ c +"' at position "+ ix.get() +" in text '"+ text +"'.");

    }

    return null;

  }


  /**
   * Extract block in parentheses, has the highest priority in computing
   * @param b    StringBuilder for result
   * @param text source text
   * @param ix   before - first parentheses index; after - index of symbol after last parentheses
   */
  protected static void getEntry(StringBuilder b, String text, AtomicInteger ix) {
    int len = text.length();
    char c = text.charAt(ix.get());
    if (c == '(') {
      ix.addAndGet(1);
    }
    else {
      throw new ParserError("Invalid character '"+ c +"' at position "+ ix.get() +" in text '"+ text +"'.");
    }
    int entreNum = 1;

    while (ix.get() < len) {
      c = text.charAt(ix.get());

      if (c == '(') {
        entreNum++;
        b.append(c);
        ix.addAndGet(1);
      }

      else if (c == ')') {
        if (entreNum == 1) {
          ix.addAndGet(1);
          return;
        }
        else {
          entreNum--;
          b.append(c);
          ix.addAndGet(1);
        }
      }

      else if (c == '\'') {
        b.append(c);
        getString(b, text, ix);
        b.append(c);
      }

      else {
        b.append(c);
        ix.addAndGet(1);
      }

    }
    throw new FormulaException("Unexpected end of block in text '"+ text +"', no "+ entreNum +" parentheses.");
  }


  /**
   * Extract string without quotes
   * @param b    StringBuilder for result
   * @param text source text
   * @param ix   before - first quote index; after - index of symbol after last quote
   */
  protected static void getString(StringBuilder b, String text, AtomicInteger ix) {
    int index = ix.get();
    char quoteSymbol = text.charAt(index);
    if (quoteSymbol != '\'' && quoteSymbol != '"') {
      throw new FormulaException("Invalid symbol ("+quoteSymbol+") at position "+ ix.get() +" in text '"+ text +"'. Require (') or (\").");
    }

    index = ix.addAndGet(1);
    int len = text.length();
    while (index < len) {
      char c = text.charAt(index);

      if (c == '\\') {
        b.append(c);
        index = ix.addAndGet(1);
        if (index >= len) {
          throw new FormulaException("Unexpected end of string in text '"+ text +"', obtained value '"+ b.toString() +"'.");
        }
        char next = text.charAt(index);
        b.append(next);
      }

      else if (c == quoteSymbol) {
        ix.addAndGet(1);
        return;
      }

      else {
        b.append(c);
      }

      index = ix.addAndGet(1);
    }

    throw new FormulaException("Unexpected end of block in text '"+ text +"', no closing quotation marks.");
  }

  /**
   * Extract identify - function or field name.
   * @param b    StringBuilder for result
   * @param text source text
   * @param ix   before - first symbol index; after - index of symbol after last identify symbol
   */
  protected static void getIdentify(StringBuilder b, String text, AtomicInteger ix) {
    int len = text.length();
    int index = ix.get();

    if (index >= len) {
      return;
    }

    char c = text.charAt(ix.get());
    if (!Character.isLetter(c) && !Character.isDigit(c) && !isWhiteSpace(c)) {
      if (c!='*' && c!='/' && c!='+' && c!='-' && c!='=' && c!='<' && c!='>' && c!=',' && c!='@' && c!='#' && c!='$' && c!='(') {
        throw new ParserError("Invalid character '" + c + "' at position " + ix.get() + " in text '" + text + "'.");
      }
    }

    while (index < len) {
      c = text.charAt(index);
      if (Character.isLetterOrDigit(c) || c == '_' || c == '@' || c == '#' || c == '$') {
        b.append(c);
        index = ix.addAndGet(1);
        continue;
      }
      break;
    }

  }

  /**
   * Extract number
   * @param b    StringBuilder for result
   * @param text source text
   * @param ix   before - index of the first digital symbol; after - index of the symbol after last digital symbol
   */
  protected static void getNumber(StringBuilder b, String text, AtomicInteger ix) {
    char c = text.charAt(ix.get());
    if (!Character.isDigit(c)) {
      throw new ParserError("Invalid character '"+ c +"' at position "+ ix.get() +" in text '"+ text +"'.");
    }

    int len = text.length();
    boolean oneDot = false;
    int index = ix.get();
    while (index < len) {
      c = text.charAt(index);
      if (Character.isDigit(c)) {
        b.append(c);
      }
      else if (c == '.') {
        if (!oneDot) {
          oneDot = true;
          b.append(c);
        }
        else {
          throw new FormulaException("Invalid number at position "+ ix.get() +" in text '"+ text +"'.");
        }
      }
      else {
        break;
      }
      index = ix.addAndGet(1);
    }
  }


  protected static void getOperator(StringBuilder b, String text, AtomicInteger ix) {
    char c = text.charAt(ix.getAndAdd(1));
    b.append(c);

    if (ix.get() >= text.length()) {
      return;
    }

    char nextC = text.charAt(ix.get());
    if (nextC == '=') {
      b.append(nextC);
      ix.addAndGet(1);
    }

  }


  /**
   * Return the next symbol with ignore whitespace
   * @param text  source text
   * @param index search will start from this position
   * @return symbol or zero when not found
   */
  public static char getNextSymbol(String text, int index) {
    int len = text.length();

    if (len <= index) {
      return 0;
    }

    while (index < len) {
      char c = text.charAt(index);
      if (isWhiteSpace(c)) {
        index++;
        continue;
      }
      return c;
    }
    return 0;
  }

  private static boolean isWhiteSpace(char c) {
    switch (c) {
      case 0x0009:
      case 0x000A:
      case 0x000B:
      case 0x000C:
      case 0x000D:
      case 0x0020:
      case 0x0085:
      case 0x00A0:
      case 0x1680:
      case 0x2000:
      case 0x2001:
      case 0x2002:
      case 0x2003:
      case 0x2004:
      case 0x2005:
      case 0x2006:
      case 0x2007:
      case 0x2008:
      case 0x2009:
      case 0x200A:
      case 0x2028:
      case 0x2029:
      case 0x202F:
      case 0x205F:
      case 0x3000:
        return true;
      default: return false;
    }
  }




}

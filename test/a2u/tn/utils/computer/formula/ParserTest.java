package a2u.tn.utils.computer.formula;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class ParserTest {

  @Test
  public void testExtractElement() {
    testExtractElement("5",     0, Parser.ElementType.NUMBER,    "5");
    testExtractElement("5.1",   0, Parser.ElementType.NUMBER,    "5.1");
    testExtractElement("-5",    0, Parser.ElementType.OPERATOR,  "-");
    testExtractElement("'string'", 0, Parser.ElementType.STRING, "string");
    testExtractElement("true",  0, Parser.ElementType.IDENTY,    "true");
    testExtractElement("null",  0, Parser.ElementType.IDENTY,    "null");
    testExtractElement("2,3",   0, Parser.ElementType.NUMBER,    "2");
    testExtractElement("(2,3)", 0, Parser.ElementType.BLOCK,     "2,3");
    testExtractElement("1+5",   0, Parser.ElementType.NUMBER,    "1");
    testExtractElement(".requestList.id", 0, Parser.ElementType.FIELD, "requestList");

    testExtractElement("1*5",  1, Parser.ElementType.OPERATOR, "*");
    testExtractElement("1/5",  1, Parser.ElementType.OPERATOR, "/");
    testExtractElement("1+5",  1, Parser.ElementType.OPERATOR, "+");
    testExtractElement("1-5",  1, Parser.ElementType.OPERATOR, "-");
    testExtractElement("1,5",  1, Parser.ElementType.OPERATOR, ",");
    testExtractElement("1=5",  1, Parser.ElementType.OPERATOR, "=");
    testExtractElement("1>5",  1, Parser.ElementType.OPERATOR, ">");
    testExtractElement("1>=5", 1, Parser.ElementType.OPERATOR, ">=");
    testExtractElement("1<5",  1, Parser.ElementType.OPERATOR, "<");
    testExtractElement("1<=5", 1, Parser.ElementType.OPERATOR, "<=");
    testExtractElement("1 and 5",   2, Parser.ElementType.IDENTY, "and");
    testExtractElement("1 or 5",    2, Parser.ElementType.IDENTY, "or");
    testExtractElement("1 xor 5",   2, Parser.ElementType.IDENTY, "xor");
    testExtractElement("1 in 5",    2, Parser.ElementType.IDENTY, "in");
    testExtractElement("1 notin 5", 2, Parser.ElementType.IDENTY, "notin");

    String str = " 1 in 5";
    AtomicInteger ix = new AtomicInteger(0);
    Parser.Element elt = Parser.extractElement(str, ix);
    assertEquals(elt.type, Parser.ElementType.NUMBER);
    assertEquals(elt.value, "1");
    assertEquals(ix.get(), 2);
    assertEquals(str.substring(ix.get()), " in 5");

  }
  private void testExtractElement(String text, int pos, Parser.ElementType type, String value) {
    AtomicInteger ix = new AtomicInteger(pos);
    Parser.Element elt = Parser.extractElement(text, ix);

    assertEquals(elt.type, type);
    assertEquals(elt.value, value);

    int npos = ix.get() - pos;
    if (elt.type == Parser.ElementType.STRING || elt.type == Parser.ElementType.BLOCK) {
      npos -= 2;
    }
    if (elt.type == Parser.ElementType.FIELD) {
      npos -= 1;
    }
    assertEquals(npos, value.length());
  }


  @Test
  public void testGetEntry() {
    testEntry("(.code = '123')", ".code = '123'");
    testEntry("(.code = ('123'+'str'))", ".code = ('123'+'str')");
    testEntry("(.code = '(123'+'str')",  ".code = '(123'+'str'");
    testEntry("(.code = ')123'+'str')",  ".code = ')123'+'str'");
    testEntry("(.code = '123)'+'str')",  ".code = '123)'+'str'");

    checkEntryError("(.code = '123'(+'str')",   "FormulaException: Unexpected end of block in text '(.code = '123'(+'str')', no 1 parentheses.");
    checkEntryError("(.code = '123'(+('str')",  "FormulaException: Unexpected end of block in text '(.code = '123'(+('str')', no 2 parentheses.");
    checkEntryError("(.code = '123'(+(('str')", "FormulaException: Unexpected end of block in text '(.code = '123'(+(('str')', no 3 parentheses.");
    checkEntryError(".code = '123'(+(('str')",  "ParserError: Invalid character '.' at position 0 in text '.code = '123'(+(('str')'.");

    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getEntry(b, "(.code = '123')+'str')", ix);
    assertEquals(b.toString(), ".code = '123'");
  }
  private void testEntry(String text, String resultString) {
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getEntry(b, text, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(ix.get(), text.length());


    String testedString = text + "=";
    b = new StringBuilder();
    ix = new AtomicInteger(0);
    Parser.getEntry(b, testedString, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(testedString.substring(ix.get()), "=");
    assertEquals(ix.get(), text.length());
  }
  private void checkEntryError(String text, String error) {
    String errMsg;
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    try {
      Parser.getEntry(b, text, ix);
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }


  @Test
  public void testGetString() {
    testString("'123'",   "123");         // '123' -> 123
    testString("\"123\"", "123");         // "123" -> 123

    testString("'1\"23'",  "1\"23");      // '1"23' -> 1"23
    testString("\"1'23\"", "1'23");       // "1'23" -> 1'23

    testString("'1\\'23'",    "1'23");  // '1\'23' -> 1\'23

    checkStringError("'123",   "FormulaException: Unexpected end of block in text ''123', no closing quotation marks.");
    checkStringError("\"123'", "FormulaException: Unexpected end of block in text '\"123'', no closing quotation marks.");
    checkStringError("'123\"", "FormulaException: Unexpected end of block in text ''123\"', no closing quotation marks.");

    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getString(b, "'12''34", ix);
    assertEquals(b.toString(), "12");
  }
  private void testString(String text, String resultString) {
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getString(b, text, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(ix.get(), text.length());


    String testedString = text + "=";
    b = new StringBuilder();
    ix = new AtomicInteger(0);
    Parser.getString(b, testedString, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(testedString.substring(ix.get()), "=");
    assertEquals(ix.get(), text.length());
  }
  private void checkStringError(String text, String error) {
    String errMsg;
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    try {
      Parser.getString(b, text, ix);
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }


  @Test
  public void testGetIdentify() {
    testIdentify("abcd",    "abcd");
    testIdentify("a1bcd",   "a1bcd");
    testIdentify("abcd123", "abcd123");
    testIdentify("a_bcd",   "a_bcd");
    testIdentify("abcd_",   "abcd_");
    testIdentify("1abcd",   "1abcd");

    checkIdentifyError("_abcd", "ParserError: Invalid character '_' at position 0 in text '_abcd'.");
    //checkIdentifyError("1abcd", "ParserError: Invalid character '1' at position 0 in text '1abcd'.");

  }
  private void testIdentify(String text, String resultString) {
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getIdentify(b, text, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(ix.get(), text.length());


    String testedString = text + "=";
    b = new StringBuilder();
    ix = new AtomicInteger(0);
    Parser.getIdentify(b, testedString, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(testedString.substring(ix.get()), "=");
    assertEquals(ix.get(), text.length());
  }
  private void checkIdentifyError(String text, String error) {
    String errMsg;
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    try {
      Parser.getIdentify(b, text, ix);
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }


  @Test
  public void testGetNumber() {
    testNumber("123",  "123");
    testNumber("1.23", "1.23");
    checkNumberError("-123",  "ParserError: Invalid character '-' at position 0 in text '-123'.");
    checkNumberError("+123",  "ParserError: Invalid character '+' at position 0 in text '+123'.");
    checkNumberError("1.2.3", "FormulaException: Invalid number at position 3 in text '1.2.3'.");
    checkNumberError(".123",  "ParserError: Invalid character '.' at position 0 in text '.123'.");
  }
  private void testNumber(String text, String resultString) {
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    Parser.getNumber(b, text, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(ix.get(), text.length());


    String testedString = text + "=";
    b = new StringBuilder();
    ix = new AtomicInteger(0);
    Parser.getNumber(b, testedString, ix);

    assertEquals(b.toString(), resultString);
    assertEquals(testedString.substring(ix.get()), "=");
    assertEquals(ix.get(), text.length());
  }
  private void checkNumberError(String text, String error) {
    String errMsg;
    StringBuilder b = new StringBuilder();
    AtomicInteger ix = new AtomicInteger(0);
    try {
      Parser.getNumber(b, text, ix);
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }



  @Test
  public void testGetNextSymbol() {
    char c;

    c = Parser.getNextSymbol("a", 0);
    assertEquals(c, 'a');

    c = Parser.getNextSymbol(" a", 0);
    assertEquals(c, 'a');

    c = Parser.getNextSymbol(" ", 0);
    assertEquals(c, 0);

    c = Parser.getNextSymbol("", 0);
    assertEquals(c, 0);

  }

}
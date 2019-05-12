package a2u.tn.utils.computer.formula;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormulaTest {

  @Test
  public void testFormula() {
    check("5",        "{type: 'number', value: 5}");
    check("'string'", "{type: 'string', value: 'string'}");
    check("true",     "{type: 'function', name:'true'}");
    check("null",     "{type: 'function', name:'null'}");

    check("1 + 2",    "{type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}}");

    check("1+2+3", "{type: 'operation', command: 'plus', arg1: {type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}}, arg2: {type: 'number', value: 3}}");
    check("1*2+3", "{type: 'operation', command: 'plus', arg1: {type: 'operation', command: 'mul', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}}, arg2: {type: 'number', value: 3}}");
    check("1+2*3", "{type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'operation', command: 'mul', arg1: {type: 'number', value: 2}, arg2: {type: 'number', value: 3}}}");

    check("(1+2)*3", "{type: 'operation', command: 'mul', arg1: {type: 'block', value: {type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}}}, arg2: {type: 'number', value: 3}}");
    check("1*(2+3)", "{type: 'operation', command: 'mul', arg1: {type: 'number', value: 1}, arg2: {type: 'block', value: {type: 'operation', command: 'plus', arg1: {type: 'number', value: 2}, arg2: {type: 'number', value: 3}}}}");

    check("calc(1)",        "{type: 'function', name:'calc', params: [{type: 'number', value: 1}]}");
    check("calc(1, 2)",     "{type: 'function', name:'calc', params: [{type: 'number', value: 1},{type: 'number', value: 2}]}");
    check("calc(1+2, 3)",   "{type: 'function', name:'calc', params: [{type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}},{type: 'number', value: 3}]}");
    check("calc(1+2, 3+4)", "{type: 'function', name:'calc', params: [{type: 'operation', command: 'plus', arg1: {type: 'number', value: 1}, arg2: {type: 'number', value: 2}},{type: 'operation', command: 'plus', arg1: {type: 'number', value: 3}, arg2: {type: 'number', value: 4}}]}");

    check(".",                          "{type= 'value', fieldName: '', filter: null, next: null}");
    check(".fieldA",                    "{type= 'value', fieldName: 'fieldA', filter: null, next: null}");
    check(".fieldA.FieldB",             "{type= 'value', fieldName: 'fieldA', filter: null, next: {type= 'value', fieldName: 'FieldB', filter: null, next: null}}");
    check(".fieldA((filter))",          "{type= 'value', fieldName: 'fieldA', filter: {type: 'block', value: {type: 'function', name:'filter'}}, next: null}");
    check(".fieldA(filter)",            "{type= 'value', fieldName: 'fieldA', filter: {type: 'function', name:'filter'}, next: null}");
    check(".fieldA(.FieldB = 1)",       "{type= 'value', fieldName: 'fieldA', filter: {type: 'operation', command: 'equal', arg1: {type= 'value', fieldName: 'FieldB', filter: null, next: null}, arg2: {type: 'number', value: 1}}, next: null}");
    check(".fieldA(1 = .FieldB)",       "{type= 'value', fieldName: 'fieldA', filter: {type: 'operation', command: 'equal', arg1: {type: 'number', value: 1}, arg2: {type= 'value', fieldName: 'FieldB', filter: null, next: null}}, next: null}");
    check(".fieldA(.FieldB = .FieldC)", "{type= 'value', fieldName: 'fieldA', filter: {type: 'operation', command: 'equal', arg1: {type= 'value', fieldName: 'FieldB', filter: null, next: null}, arg2: {type= 'value', fieldName: 'FieldC', filter: null, next: null}}, next: null}");

    check(".fieldA(.FieldB = .FieldC + 3)", "{type= 'value', fieldName: 'fieldA', filter: {type: 'operation', command: 'equal', arg1: {type= 'value', fieldName: 'FieldB', filter: null, next: null}, arg2: {type: 'operation', command: 'plus', arg1: {type= 'value', fieldName: 'FieldC', filter: null, next: null}, arg2: {type: 'number', value: 3}}}, next: null}");

    checkError(":",    "FormulaException: Invalid character ':' at position 0 in text ':'.");
    checkError("+",    "FormulaException: Illegal part OPERATOR: +' at position 0 in text '+'.");
    checkError("1 2",  "FormulaException: Illegal part NUMBER: 2 at position 2, expected operator. Source text '1 2'.");
    checkError("1+",   "FormulaException: Unexpected end of expression at position 2, in text '1+'.");
    checkError("1+(",  "FormulaException: Unexpected end of block in text '1+(', no 1 parentheses.");
    checkError("1+(2", "FormulaException: Unexpected end of block in text '1+(2', no 1 parentheses.");
    checkError("1)",   "FormulaException: Invalid character ')' at position 1 in text '1)'.");
    checkError("1)+",  "FormulaException: Invalid character ')' at position 1 in text '1)+'.");
    checkError("1)+2", "FormulaException: Invalid character ')' at position 1 in text '1)+2'.");

  }

  private void check(String query, String value) {
    Formula formula = new Formula(query);
    try {
      assertEquals(value, formula.toJson());
    }
    catch (Throwable tr) {
      //System.out.println(formula.toFormated());
      //System.out.println(formula.toJson());
      throw tr;
    }
  }

  private void checkError(String query, String error) {
    String errMsg;
    try {
      Formula formula = new Formula(query);
      System.out.println(formula.toFormated());
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }


  @Test
  public void test01() {
    //
    //.entity = obj.pubList.entity
    String query = ".entity = 8@TnPubList.entity";
    query = ".entity = child('8@TnPubList', 'entity.part')";
    //query = ".$8@TnPubList.entity";
    Formula formula = new Formula(query);
    System.out.println(formula.toString());
  }

}
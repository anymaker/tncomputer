package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OwnFunctionTest {

  private static class Concat extends Function {

    /**
     * Return descriptors for incoming parameters
     *
     * @return descriptors for incoming parameters
     */
    @Override
    protected List<Parameter> initParameters() {
      List<Parameter> parameters = new ArrayList<>();
      parameters.add(new Parameter(String.class, "stringA"));
      parameters.add(new Parameter(String.class, "stringB"));
      return parameters;
    }

    /**
     * Invoke function to execution
     *
     * @param calculator  Calculator for executing or type conversion
     * @param params      Other params
     * @param paramValues Prepared values of parameters
     * @param ctx         Data for calculating
     * @return Result of execution function
     */
    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
      String stringA = (String) paramValues.get("stringA");
      String stringB = (String) paramValues.get("stringB");

      return stringA+stringB;
    }

  }


  @Test
  public void countTest() {
    ObjCalcEngine engine = new ObjCalcEngine();

    Function fn = new Concat();

    engine.addFunction(fn);
    engine.addFunction(fn, "cnct");

    Function fn1 = engine.getFunction(Concat.class.getSimpleName().toLowerCase());
    Function fn2 = engine.getFunction("cnct");
    assertEquals(fn, fn1);
    assertEquals(fn, fn2);

    assertEquals("AB", engine.calc("cnct('A', 'B')"));

  }



}

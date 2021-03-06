package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provide switch-case-default functionality
 * Syntax
 *   decode(expression, caseValue, returnValue [, caseValue, returnValue] [, default])
 *     expression - The value to compare
 *     caseValue - The value that is compared against expression
 *     returnValue - The value returned, if expression is equal to caseValue
 *     default - Optional. If no matches are found, the DECODE function will return default.
 *
 * If expression is a Collection, then then comparacion will running for every values in collection
 * and result will be a collection.
 *
 */
public class Decode extends Function {


  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(Object.class, "expression"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    Object testValue = paramValues.get("expression");

    if (testValue instanceof Collection) {
      Collection caseValue = (Collection)testValue;
      List<Object> resultList = new ArrayList<>();
      for (Object test : caseValue) {
        Object result = doDecode(calculator, test, params, ctx);
        resultList.add(result);
      }
      return resultList;
    }
    else {
      Object result = doDecode(calculator, testValue, params, ctx);
      return result;
    }
  }

  private Object doDecode(Calculator calculator, Object caseValue, List<FormulaPart> params, CalcContext ctx) {
    int ix = 1; // first param contains expression
    while (ix < params.size()) {
      FormulaPart part = params.get(ix++);
      Object test = calculator.calcArgument(part, ctx);
      if (ix >= params.size()) {
        return test;
      }
      FormulaPart retPart = params.get(ix++);
      if (calculator.equalValues(caseValue, test)) {
        Object result = calculator.calcArgument(retPart, ctx);
        return result;
      }
    }
    return caseValue;
  }

}

package a2u.tn.utils.computer.calcobj.functions;

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
  private static List<Parameter> parameters;

  public Decode(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "expression"));
  }

  @Override
  public String getName() {
    return "decode";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    FormulaPart expression = namedParams.get("expression");
    Object testValue = calculator.calcArgument(expression, row, rowIndex, allRows);

    if (testValue instanceof Collection) {
      Collection caseValue = (Collection)testValue;
      List<Object> resultList = new ArrayList<>();
      for (Object test : caseValue) {
        Object result = doDecode(test, otherParams, row, rowIndex, allRows);
        resultList.add(result);
      }
      return resultList;
    }
    else {
      Object result = doDecode(testValue, otherParams, row, rowIndex, allRows);
      return result;
    }
  }

  private Object doDecode(Object caseValue, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    int ix = 0;
    while (ix < params.size()) {
      FormulaPart part = params.get(ix++);
      Object test = calculator.calcArgument(part, row, rowIndex, allRows);
      if (ix >= params.size()) {
        return test;
      }
      FormulaPart retPart = params.get(ix++);
      if (calculator.equalValues(caseValue, test)) {
        Object result = calculator.calcArgument(retPart, row, rowIndex, allRows);
        return result;
      }
    }
    return caseValue;
  }

}

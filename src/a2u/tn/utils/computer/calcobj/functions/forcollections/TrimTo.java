package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Removes all values from the array, starting from the beginning and ending with the given value `lastValue`
 * The optional `leaveLastValue` parameter allows you to leave the specified value `lastValue` in the array. By default, it is `true`
 */
public class TrimTo extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(List.class, "collection"));
    parameters.add(new Parameter(Object.class, "firstValue"));
    parameters.add(new Parameter(boolean.class, "leaveFirstValue", false, "true"));
    return parameters;
  }

  /**
   * Invoke function to execution
   *
   * @param calculator  Calculator for executing or type conversion
   * @param params      Incoming params
   * @param paramValues Prepared values of parameters
   * @param ctx         Data for calculating
   * @return Result of execution function
   */
  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    List<?> list  = (List<?>) paramValues.get("collection");
    Object firstValue = paramValues.get("firstValue");
    boolean leaveFirstValue = (boolean) paramValues.get("leaveFirstValue");

    if (list == null) {
      return null;
    }

    if (list.isEmpty()) {
      return new ArrayList<>();
    }

    int ix = list.indexOf(firstValue);

    if (ix < 0) {
      return new ArrayList<>();
    }

    if (!leaveFirstValue) {
      ix++;
    }

    if (ix >= list.size()) {
      return new ArrayList<>();
    }



    List<?> result = list.subList(ix, list.size());
    return result;
  }

}

package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Context;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FPValue;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Determine the max value in the path across all rows
 * Unlike the similar function "max", this function looks for the largest value in all rows processed by the filter.
 */
public class MaxInRows extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(FPValue.class, "path"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    if (ctx.getAllRows() == null) {
      throw new CalculatingException("Function 'maxInRows' can only be used to filtering rows.");
    }

    FPValue path = (FPValue) paramValues.get("path");
    Object max = null;
    int ix=0;
    for (Object obj : ctx.getAllRows()) {
      Object value = calculator.calcArgument(path, Context.of(obj, ix, ctx.getAllRows()));
      if (max == null) {
        max = value;
      }
      else {
        Type type = calculator.getType(max.getClass());
        if (type.less(max, value)) {
          max = value;
        }
      }
      ix++;
    }
    return max;
  }
}

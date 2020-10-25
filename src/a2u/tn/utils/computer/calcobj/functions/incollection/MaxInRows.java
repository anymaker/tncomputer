package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FPValue;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Determine the max value in the path across all rows
 * Unlike the similar function "max", this function looks for the largest value in all rows processed by the filter.
 */
public class MaxInRows extends Function {

  public MaxInRows(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(FPValue.class, "path"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'maxInRows' can only be used to filtering rows.");
    }

    FormulaPart path = params.get(0);
    Object max = null;
    int ix=0;
    for (Object obj : allRows) {
      Object value = calculator.calcArgument(path, obj, ix, allRows);
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

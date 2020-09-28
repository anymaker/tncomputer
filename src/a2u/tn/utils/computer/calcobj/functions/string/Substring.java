package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Returns a string that is a substring of this string.
 */
public class Substring extends Function {

  public Substring(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "string"));
    parameters.add(new Parameter(Object.class, "start"));
    parameters.add(new Parameter(Object.class, "length"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object string_ = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object start_  = calculator.calcArgument(params.get(1), row, rowIndex, allRows);
    Object length_ = calculator.calcArgument(params.get(2), row, rowIndex, allRows);

    String string = calculator.toType(String.class, string_);
    int    start  = calculator.toType(Integer.class, start_);
    int    length = calculator.toType(Integer.class, length_);

    return StringUtil.substring(string, start, length);
  }

}

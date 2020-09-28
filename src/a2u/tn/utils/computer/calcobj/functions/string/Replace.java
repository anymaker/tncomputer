package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Returns a string resulting from replacing all occurrences of oldString in this string with newString.
 */
public class Replace extends Function {

  public Replace(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "string"));
    parameters.add(new Parameter(Object.class, "oldString"));
    parameters.add(new Parameter(Object.class, "newString"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object string_    = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object oldString_ = calculator.calcArgument(params.get(1), row, rowIndex, allRows);
    Object newString_ = calculator.calcArgument(params.get(2), row, rowIndex, allRows);

    String string    = calculator.toType(String.class, string_);
    String oldString = calculator.toType(String.class, oldString_);
    String newString = calculator.toType(String.class, newString_);

    if (string == null) {
      return null;
    }
    if (oldString == null) {
      return string;
    }

    if (newString == null) {
      newString = "";
    }

    String result = StringUtil.replace(string, oldString, newString);
    return result;
  }

}
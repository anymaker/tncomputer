package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adds characters to the right of the string until the string reaches the specified length.
 */
public class PadRight extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Function.Parameter> parameters = new ArrayList<>();
    parameters.add(new Function.Parameter(Object.class, "string"));
    parameters.add(new Function.Parameter(Object.class, "length"));
    parameters.add(new Function.Parameter(Object.class, "sample"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object string_ = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object length_ = calculator.calcArgument(params.get(1), row, rowIndex, allRows);
    Object sample_ = calculator.calcArgument(params.get(2), row, rowIndex, allRows);

    String string = calculator.toType(String.class, string_);
    int length    = calculator.toType(Integer.class, length_);
    String sample = calculator.toType(String.class, sample_);

    if (string == null) {
      string = "";
    }
    if (length == 0) {
      return string;
    }
    if (sample == null || sample.length() == 0) {
      return string;
    }

    StringBuilder b = new StringBuilder(string);
    while (b.length() < length) {
      b.append(sample);
    }

    return b.toString();
  }

}
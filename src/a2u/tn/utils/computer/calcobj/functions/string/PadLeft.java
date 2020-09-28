package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PadLeft extends Function {

  public PadLeft(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Function.Parameter> initParameters() {
    List<Function.Parameter> parameters = new ArrayList<>();
    parameters.add(new Function.Parameter(Object.class, "string"));
    parameters.add(new Function.Parameter(Object.class, "quantity"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
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
      b.insert(0, sample);
    }

    return b.toString();
  }

}
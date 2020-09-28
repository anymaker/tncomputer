package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Remove all whitespaces characters
 */
public class RemoveWhitespaces extends Function {

  public RemoveWhitespaces(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "string"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object string_ = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    String string = calculator.toType(String.class, string_);

    if (string == null) {
      return true;
    }

    StringBuilder b = new StringBuilder();
    int len = string.length();
    
    for (int ix = 0; ix < len; ix++) {
      char c = string.charAt(ix);
      if (! StringUtil.isWhiteSpace(c)) {
        b.append(c);
      }
    }

    return b.toString();
  }

}
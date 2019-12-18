package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Calc count not null values in path
 * See @link FunctionsTest#countTest() for demo
 */
public class Count extends Function {

  private static List<Parameter> parameters;

  public Count(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "data"));
  }


  @Override
  public String getName() {
    return "count";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = calculator.calcArgument(namedParams.get("data"),  row, rowIndex, allRows);
    if (data == null) {
      return 0;
    }
    if (data instanceof Collection) {
      Collection list = (Collection) data;
      int cnt = 0;
      for (Object obj : list) {
        if (obj != null) {
          cnt++;
        }
      }
      return cnt;
    }
    return 1;
  }

}

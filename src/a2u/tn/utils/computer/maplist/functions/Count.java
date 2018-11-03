package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

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
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = paramValues.get("data");
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

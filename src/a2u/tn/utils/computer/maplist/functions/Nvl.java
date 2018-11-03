package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Return substitute if value is null
 */
public class Nvl extends Function {
  private static List<Parameter> parameters;

  public Nvl(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "value"));
    parameters.add(new Parameter(Object.class, "substitute"));
  }

  @Override
  public String getName() {
    return "nvl";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    Object value = paramValues.get("value");
    Object substitute  = paramValues.get("substitute");

    return value == null ? substitute : value;
  }

}

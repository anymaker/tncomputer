package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Return substitute if value is null
 */
public class Nvl extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "value"));
    parameters.add(new Parameter(Object.class, "substitute"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object value       = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object substitute  = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

    return value == null ? substitute : value;
  }

}

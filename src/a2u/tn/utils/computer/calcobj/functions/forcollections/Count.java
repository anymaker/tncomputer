package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Calc count not null values in path
 * See @link FunctionsTest#countTest() for demo
 */
public class Count extends Function {

  public Count(Calculator calculator) {
    super(calculator);
  }


  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "collection"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
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

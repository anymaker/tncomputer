package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Calc count not null values in path
 * See @link FunctionsTest#countTest() for demo
 */
public class Count extends Function {


  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(List.class, "collection"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    List list = (List) paramValues.get("collection");

    if (list == null) {
      return 0;
    }

    int cnt = 0;
    for (Object obj : list) {
      if (obj != null) {
        cnt++;
      }
    }

    return cnt;
  }

}

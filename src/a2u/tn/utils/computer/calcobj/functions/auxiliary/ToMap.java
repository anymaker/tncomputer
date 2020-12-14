package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.Formula;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToMap extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(List.class, "fields", false, null));
    return parameters;
  }



  /**
   * Invoke function to execution
   *
   * @param calculator
   * @param params     Other params
   * @param row        Data from current row
   * @param rowIndex   Index current row in rows
   * @param allRows    Collection with all rows
   * @return result of execution function
   */
  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {

    Map<Object, Object> map = new LinkedHashMap<>();

    for (FormulaPart param : params) {
      Object objs = calculator.calcArgument(param, row, rowIndex, allRows);
      List values = calculator.toType(List.class, objs);
      if (values != null) {
        if (values.size() != 2) {
          throw new IllegalArgumentException("Invalid params. Example tomap(('key1', .value1),('key2', .value2))");
        }
        map.put(values.get(0), values.get(1));
      }

    }

    return map;
  }
}

package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ToList extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(List.class, "items", false, null));
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

    List list = new ArrayList();

    for (FormulaPart param : params) {
      Object obj = calculator.calcArgument(param, row, rowIndex, allRows);
      list.add(obj);
    }

    return list;
  }
}

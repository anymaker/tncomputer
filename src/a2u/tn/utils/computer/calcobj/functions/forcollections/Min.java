package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Detect minimal value in path
 */
public class Min extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "collection"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Collection<?> iincomigCollection;

    if (params.size() == 1) {
      Object data = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
      if (data instanceof Collection) {
        iincomigCollection = (Collection) data;
      }
      else {
        iincomigCollection = Collections.singletonList(data);
      }
    }
    else {
      List<Object> list = new ArrayList<>();
      for (FormulaPart param : params) {
        Object data = calculator.calcArgument(param,  row, rowIndex, allRows);
        list.add(data);
      }
      iincomigCollection = list;
    }

    Object min = null;
    for (Object obj : iincomigCollection) {
      if (obj == null) {
        continue;
      }
      if (min == null) {
        min = obj;
      }
      Type type = calculator.getType(min.getClass());
      if (type.less(obj, min)) {
        min = obj;
      }
    }
    return min;
  }

}

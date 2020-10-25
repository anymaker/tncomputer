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

  public Min(Calculator calculator) {
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



  public Object run2(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
    if (data == null) {
      return null;
    }
    if (data instanceof Collection) {
      Collection list = (Collection) data;
      Object min = null;
      for (Object obj : list) {
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
    return data;

  }
}

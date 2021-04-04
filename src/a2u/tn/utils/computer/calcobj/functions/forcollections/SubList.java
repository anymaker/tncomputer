package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Returning a subarray from an incoming array
 * Subarray, starts with `indexFrom` and ends with `indexTo`
 * By default `indexFrom` and `indexTo` are included in the subarray
 *
 *
 */
public class SubList extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(List.class, "collection"));
    parameters.add(new Parameter(int.class, "indexFrom"));
    parameters.add(new Parameter(int.class, "indexTo"));
    parameters.add(new Parameter(boolean.class, "includeFirstValue", false, true));
    parameters.add(new Parameter(boolean.class, "includeLastValue", false, true));

    return parameters;
  }


  /**
   * Invoke function to execution
   *
   * @param calculator  Calculator for executing or type conversion
   * @param params      Incoming params
   * @param paramValues Prepared values of parameters
   * @param ctx         Data for calculating
   * @return Result of execution function
   */
  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    List<?> list  = (List<?>) paramValues.get("collection");
    int indexFrom = (int) paramValues.get("indexFrom");
    int indexTo   = (int) paramValues.get("indexTo");
    boolean includeFirstValue = (boolean) paramValues.get("includeFirstValue");
    boolean includeLastValue = (boolean) paramValues.get("includeLastValue");

    if (list == null) {
      return null;
    }

    if (list.isEmpty()) {
      return new ArrayList<>();
    }

    // думаем, что list.subList всегда inclusive

    if (indexFrom < 0) {
      indexFrom = list.size()-1 + indexFrom;
      if (indexFrom < 0) {
        indexFrom = 0;
      }
    }

    if (indexTo < 0) {
      indexTo = list.size()-1 + indexTo;
      if (indexTo < 0) {
        indexTo = 0;
      }
    }

    if (indexFrom > indexTo) {
      int hold = indexFrom;
      indexFrom = indexTo;
      indexTo = hold;
    }


    if (indexFrom >= list.size()) {
      return new ArrayList<>();
    }
    if (indexTo >= list.size()) {
      indexTo = list.size() - 1;
    }

    //fromIndex low endpoint (inclusive) of the subList
    //toIndex high endpoint (exclusive) of the subList
    indexFrom = includeFirstValue ? indexFrom : indexFrom+1;
    indexTo = includeLastValue ? indexTo+1 : indexTo;

    List<?> sublist = list.subList(indexFrom, indexTo);
    return sublist;
  }

}

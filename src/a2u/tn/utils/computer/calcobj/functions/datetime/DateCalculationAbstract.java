package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for date calculation
 */
abstract class DateCalculationAbstract extends Function {


  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(LocalDateTime.class, "date"));
    parameters.add(new Parameter(Long.class, getSecondParamName()));
    return parameters;
  }

  protected abstract String getSecondParamName();

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    //Object dateValue   = paramValues.get("date")calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
    //Object monthsValue = calculator.calcArgument(params.get(1),  row, rowIndex, allRows);

    LocalDateTime date  = (LocalDateTime) paramValues.get("date");//calculator.toType(LocalDateTime.class, dateValue);
    Long item           = (Long) paramValues.get(getSecondParamName());//calculator.toType(Long.class, monthsValue);

    LocalDateTime calculatedDate = calc(date, item);

    Date result = Date.from(calculatedDate.atZone(ZoneId.systemDefault()).toInstant());

    return result;
  }

  protected abstract LocalDateTime calc(LocalDateTime current, long item);

}

package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calcobj.types.TNull;
import a2u.tn.utils.computer.formula.FPBlock;
import a2u.tn.utils.computer.formula.FPFunction;
import a2u.tn.utils.computer.formula.FPLiteral;
import a2u.tn.utils.computer.formula.FPLiteralNumber;
import a2u.tn.utils.computer.formula.FPLiteralString;
import a2u.tn.utils.computer.formula.FPOperation;
import a2u.tn.utils.computer.formula.FPValue;
import a2u.tn.utils.computer.formula.Formula;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Computing by formula
 */
public abstract class Calculator {

  /**
   * All known functions used in queries
   */
  private Map<String, Function> functions;

  /**
   * All known types used in data
   */
  private Map<Class<?>, Type> knownTypes;

  /**
   * Converters to convert values
   */
  private Converter converters;


  public Calculator() {
    converters = new Converter();
    functions = new LinkedHashMap<>();
    knownTypes = new LinkedHashMap<>();
  }

  /**
   * Run computing using the prepared formula
   * @param formula prepared formula
   * @return result of calculations
   */
  public Object calc(Formula formula) {
    try {
      FormulaPart root = formula.getRootPart();
      Object result = calcArgument(root, Context.of(null, 0, null));
      return result;
    }
    catch (CalculatingException cex) {
      StringBuilder b = new StringBuilder();
      Throwable tr = cex;
      while (tr != null) {
        b.append("ERROR: ");
        b.append(tr).append("\n");
        tr = tr.getCause();
      }
      throw new CalculatingException("Error calculate formula:\n" + formula.toString() + "\n" + b.toString(), cex);
    }
  }

  /**
   * Run computing using query as string
   * @param query query as string
   * @return result of calculations
   */
  public Object calc(String query) {
    Formula formula = new Formula(query);
    return calc(formula);
  }


  /**
   * Run computing using the prepared formula
   * @param formula prepared formula
   * @param startObj object for begin computing
   * @return result of calculations
   */
  public Object calc(Formula formula, Object startObj) {
    try {
      FormulaPart root = formula.getRootPart();
      Object result = calcArgument(root, Context.of(startObj, 0, null));
      return result;
    }
    catch (CalculatingException cex) {
      StringBuilder b = new StringBuilder();
      Throwable tr = cex;
      while (tr != null) {
        b.append("ERROR: ");
        b.append(tr).append("\n");
        tr = tr.getCause();
      }
      throw new CalculatingException("Error calculate formula:\n" + formula.toString() + "\nby object " + String.valueOf(startObj) + "\n" + b.toString(), cex);
    }
  }

  /**
   * Run computing using query as string
   * @param query query as string
   * @param startObj object for begin computing
   * @return result of calculations
   */
  public Object calc(String query, Object startObj) {
    Formula formula = new Formula(query);
    return calc(formula, startObj);
  }


  /**
   * Run computing using the prepared formula and convert value to result type
   * @param formula prepared formula
   * @param cls goal-class for result
   * @param <T> type of returned value. This type must be subtype of cls.
   * @return result of calculations
   */
  public <T> T calc(Formula formula, Class<? extends T> cls) {
    Object result = calc(formula);
    T value = toType(cls, result);
    return value;
  }

  /**
   * Run computing using query as string and convert value to result type
   * @param query query as string
   * @param cls goal-class for result
   * @param <T> type of returned value. This type must be subtype of cls.
   * @return result of calculations
   */
  public <T> T calc(String query, Class<? extends T> cls) {
    Formula formula = new Formula(query);
    Object result = calc(formula);
    T value = toType(cls, result);
    return value;
  }


  /**
   * Run computing using the prepared formula and convert value to result type
   * @param formula prepared formula
   * @param startObj object for begin computing
   * @param cls goal-class for result
   * @param <T> type of returned value. This type must be subtype of cls.
   * @return result of calculations
   */
  public <T> T calc(Formula formula, Object startObj, Class<? extends T> cls) {
    Object result = calc(formula, startObj);
    T value = toType(cls, result);
    return value;
  }

  /**
   * Run computing using query as string and convert value to result type
   * @param query query as string
   * @param startObj object for begin computing
   * @param cls goal-class for result
   * @param <T> type of returned value. This type must be subtype of cls.
   * @return result of calculations
   */
  public <T> T calc(String query, Object startObj, Class<? extends T> cls) {
    Formula formula = new Formula(query);
    Object result = calc(formula, startObj);
    T value = toType(cls, result);
    return value;
  }


  /**
   * Compute result for part of formula
   * @param part part of formula
   * @param ctx data for calculating
   * @return result of calculations
   */
  public Object calcArgument(FormulaPart part, CalcContext ctx) {
    if (part instanceof FPOperation) {
      return calcOperation((FPOperation) part, ctx);
    }
    if (part instanceof FPFunction) {
      return calcFunction((FPFunction) part, ctx);
    }
    if (part instanceof FPValue) {
      return calcValue((FPValue) part, ctx.getRowData());
    }
    if (part instanceof FPLiteralNumber) {
      FPLiteralNumber exn = (FPLiteralNumber) part;
      Object v = exn.getValue();
      return v;
    }
    if (part instanceof FPLiteralString) {
      FPLiteralString exs = (FPLiteralString) part;
      Object v = exs.getValue();
      return v;
    }
    if (part instanceof FPLiteral) {
      FPLiteral exs = (FPLiteral) part;
      Object v = exs.getValue();
      return v;
    }
    if (part instanceof FPBlock) {
      FPBlock exb = (FPBlock) part;
      Object v = calcArgument(exb.getOperation(), ctx);
      return v;
    }
    throw new CalculatingException("Illegal operation '" + part + "'.");
  }

  private Object calcOperation(FPOperation operation, CalcContext ctx) {
    Object value1 = calcArgument(operation.getArg1(), ctx);
    if (value1 == null) {
      value1 = TNull.getNull();
    }

    if (operation.getArg2() == null) {
      return value1;
    }

    Object value2 = calcArgument(operation.getArg2(), ctx);

    Type type = getType(value1.getClass());

    switch (operation.getCommand()) {
      case mul:        return type.mul(value1, value2);
      case div:        return type.div(value1, value2);
      case plus:       return type.plus(value1, value2);
      case minus:      return type.minus(value1, value2);
      case equal:      return type.equal(value1, value2);
      case notequal:   return type.notequal(value1, value2);
      case great:      return type.great(value1, value2);
      case greatEqual: return type.greatEqual(value1, value2);
      case less:       return type.less(value1, value2);
      case lessEqual:  return type.lessEqual(value1, value2);

      case and:        return type.and(value1, value2);
      case or:         return type.or(value1, value2);
      case xor:        return type.xor(value1, value2);

      case in:         return calcOperationIn(value1, value2);
      case notin:      return calcOperationNotIn(value1, value2);
      case addToDim:   return calcOperationAddToDim(value1, value2);
      default: throw new CalculatingException("Unknown operation '"+ operation.getCommand() +"'.");
    }


  }
  private Object calcOperationIn(Object value, Object valueList) {
    List<Object> list = toType(List.class, valueList);
    Type type = getType(value.getClass());
    for (Object valInList : list) {
      boolean isPresent = type.equal(value, valInList);
      if (isPresent) {
        return true;
      }
    }
    return false;
  }
  private Object calcOperationNotIn(Object value, Object valueList) {
    List<Object> list = toType(List.class, valueList);
    Type type = getType(value.getClass());
    for (Object valInList : list) {
      boolean isPresent = type.equal(value, valInList);
      if (isPresent) {
        return false;
      }
    }
    return true;
  }
  private Object calcOperationAddToDim(Object value1, Object value2) {
    List<Object> val1 = toType(List.class, value1);
    List<Object> val2 = toType(List.class, value2);
    List<Object> result = new ArrayList<>();
    result.addAll(val1);
    result.addAll(val2);
    return result;
  }

  private Object calcFunction(FPFunction function, CalcContext ctx) {
    Function fn = getFunction(function.getName().toLowerCase());
    if (fn == null) {
      throw new CalculatingException("Function '"+ function.getName() +"' is not defined.");
    }

    Map<String, Object> paramValues = new LinkedHashMap<>(); // prepared values for use as is

    if (!fn.getParameters().isEmpty()) {

      if (function.getParams() == null) {
        String par2 = StringUtil.collectionToString(", ", fn.getParameters(), Function.Parameter::getTypeName);
        throw new CalculatingException("For function '"+ function.getName() +"' parameter is not specified. You need specify (" + par2 + ").");
      }

      for (int ix = 0; ix < fn.getParameters().size(); ix++) {
        Function.Parameter fp = fn.getParameters().get(ix);

        if (function.getParams().size() < ix+1 && fp.isRequired() && fp.getDefaultValue() == null) {
          throw new CalculatingException("Parameter '"+fp.getName()+"' is not specified for function '"+ function.getName() +"'.");
        }

        Object rawPreparedValue;
        if (function.getParams().size() > ix) {
          FormulaPart  param = function.getParams().get(ix);
          if (fp.getType().equals(FPValue.class)) {
            rawPreparedValue = param;
          }
          else {
            rawPreparedValue = calcArgument(param, ctx);
          }
        }
        else {
          rawPreparedValue = fp.getDefaultValue();
        }

        Object preparedValue = toType(fp.getType(), rawPreparedValue);
        paramValues.put(fp.getName(), preparedValue);

      }

    }
    else if (function.getParams() != null && fn.getParameters() == null) {
       throw new CalculatingException("Function '"+ function.getName() +"' no need in parameters.");
    }

    Object result = fn.run(this, function.getParams(), paramValues, ctx);
    return result;
  }
  private Object calcFunction2(FPFunction function, CalcContext ctx) {
    Function fn = getFunction(function.getName().toLowerCase());
    if (fn == null) {
      throw new CalculatingException("Function '"+ function.getName() +"' is not defined.");
    }

    List<FormulaPart> params = new ArrayList<>(); // formulas for getting values
    Map<String, Object> paramValues = new LinkedHashMap<>(); // prepared values for use as is

    if (!fn.getParameters().isEmpty()) {

      if (function.getParams() == null) {
        String par2 = StringUtil.collectionToString(", ", fn.getParameters(), Function.Parameter::getTypeName);
        throw new CalculatingException("For function '"+ function.getName() +"' parameter is not specified. You need specify (" + par2 + ").");
      }

      List<FormulaPart> paramValsList = function.getParams();
      int ix = 0;
      while (ix < fn.getParameters().size()) {
        Function.Parameter fp = fn.getParameters().get(ix);
        if (fp.isRequired()) {
          if (paramValsList.size() > ix) {
            FormulaPart param = paramValsList.get(ix);
            params.add(param);
          }
          else {
            throw new CalculatingException("Parameter '"+fp.getName()+"' is not specified for function '"+ function.getName() +"'.");
          }
        }
        else {
          if (paramValsList.size() > ix) {
            FormulaPart param = paramValsList.get(ix);
            params.add(param);
          }
          else if (fp.getDefaultValue() != null){
            FormulaPart param = new FPLiteral(fp.getDefaultValue());
            params.add(param);
          }
        }

        Object rawPreparedValue = calcArgument(params.get(0), ctx);
        Object preparedValue = toType(fp.getType(), rawPreparedValue);
        paramValues.put(fp.getName(), preparedValue);

        ix++;
      }
      if (paramValsList.size() > ix) {
        params.addAll(paramValsList.subList(ix, paramValsList.size()));
      }
    }
    else if (function.getParams() != null && fn.getParameters() == null) {
      throw new CalculatingException("Function '"+ function.getName() +"' no need in parameters.");
    }
    else {
      //for (FormulaPart param : function.getParams())
    }

    Object result = fn.run(this, params, paramValues, ctx);
    return result;
  }

  /**
   * Getting values from source by path
   * @param fpValue Path to goal value
   * @param arg1    Object that contains the required value
   * @return        One object or list with values or null if nothing is found
   */
  private Object calcValue(FPValue fpValue, Object arg1) {
    Collection<Object> resultList = calcValueProcess(fpValue, arg1);
    if (resultList == null || resultList.isEmpty()) {
      return null;
    }
    else if (resultList.size() == 1) {
      return resultList.iterator().next();
    }
    else {
      return resultList;
    }
  }
  private Collection<Object> calcValueProcess(FPValue fpValue, Object fromObj) {
    FPValue current = fpValue;
    StringBuilder path = new StringBuilder();
    Collection<Object> resultRows = new ArrayList<>();
    resultRows.add(fromObj);
    do {
      path.append(".").append(current.getFieldName());
      Collection<Object> extractedValues;
      try {
        extractedValues = extractValues(current.getFieldName(), resultRows);
      }
      catch (Exception ex) {
        throw new CalculatingException("Error on getting values from '"+String.valueOf(fromObj)+"' by code '"+current.getFieldName()+"' in path "+ path +".", ex);
      }

      Collection<Object> loopValues;
      if (extractedValues == null || extractedValues.isEmpty()) {
        loopValues = Collections.emptyList();
      }
      else if (current.getFilter() != null) {
        loopValues = new ArrayList<>();
        int ix = 0;
        for (Object value : extractedValues) {
          Object isPassObj = calcArgument(current.getFilter(), Context.of(value, ix, extractedValues));
          boolean isPass = toType(Boolean.class, isPassObj);
          if (isPass) {
            loopValues.add(value);
          }
          ix++;
        }
      }
      else {
        loopValues = extractedValues;
      }

      resultRows = loopValues;
      current = current.getNext();
    }
    while (current != null);

    return resultRows;
  }


  /**
   * Extract values from each object 'fromObjList' by code
   * @param byCode code for extracting values
   * @param fromObjList collection with objects for extracting values
   * @return Values
   */
  protected abstract Collection<Object> extractValues(String byCode, Collection<Object> fromObjList);


  /**
   * Add or replace function for used in query
   * @param function new function
   */
  public void addFunction(Function function) {
    functions.put(function.getName().toLowerCase(), function);
  }

  /**
   * Add or replace function for used in query
   * @param function new function
   * @param name the function name
   */
  public void addFunction(Function function, String name) {
    String fnName = StringUtil.trim(name);
    if (fnName == null || fnName.length() == 0) {
      throw new IllegalArgumentException("The function name must not be empty.");
    }
    functions.put(name.toLowerCase(), function);
  }

  /**
   * Getter for known functions
   * @param name name of function
   * @return function
   */
  public Function getFunction(String name) {
    return functions.get(name.toLowerCase());
  }

  /**
   * Add or replace type for used in data
   * @param descriptor type descriptor
   */
  public void addType(Type descriptor) {
    knownTypes.put(descriptor.forClass(), descriptor);
    descriptor.fillConverter(converters);
  }

  /**
   * Getter for known types
   * @param forClass class
   * @return type descriptor
   */
  public Type getType(Class<?> forClass) {
    Type type = null;

    for (Map.Entry<Class<?>, Type> entry : knownTypes.entrySet()) {
      if (entry.getKey().isAssignableFrom(forClass)) {
        type = entry.getValue();
        break;
      }
    }

    //If enum is not overridden
    if (type == null && forClass.isEnum()) {
      type = getType(String.class);
    }

    if (type == null) {
      throw new CalculatingException("Datatype '"+ forClass.getName() +"' has no descriptor.");
    }
    return type;
  }

  /**
   * Convert value to type
   * @param toCls goal-class for result
   * @param value value which be converted
   * @param <T> type of returned value. This type must be subtype of toCls.
   * @return Converted value with goal type
   * @throws NullPointerException if toCls is null
   * @throws UnknownDataTypeException if toCls is not defined in converters
   * @see Converter#addConverter(Class, Class, Converter.ConvertHandler)
   */
  public <T> T toType(Class<? extends T> toCls, Object value) {
    return converters.toType(toCls, value);
  }

  /**
   * Compare values.
   * Values can be of different types.
   * The second value will be converted to the first value type.
   * @param v1 the first value
   * @param v2 the second value
   * @return comparison result
   */
  public boolean equalValues(Object v1, Object v2) {
    Object v1val = v1 == null ? TNull.getNull() : v1;
    Type type = getType(v1val.getClass());
    return type.equal(v1val, v2);
  }


}

package a2u.tn.utils.computer.calculator;

import java.util.LinkedHashMap;
import java.util.Map;


public class Converter {

  public interface AnyClass {}
  public interface NullClass {}

  public interface ConvertHandler<H> {
    H convert(Object value);
  }

  public Map<Class<?>, Map<Class<?>, ConvertHandler<?>>> converters; //Map<toCls, Map<fromCls, ConvertHandler>>

  public Converter() {
    converters = new LinkedHashMap<>();
  }



  public void addConverter(Class<?> toCls, Class<?> fromCls, ConvertHandler<?> handler) {
    Map<Class<?>, ConvertHandler<?>> to = converters.get(toCls);
    if (to == null) {
      to = new LinkedHashMap<>();
      converters.put(toCls, to);
    }
    if (fromCls == null) {
      to.put(NullClass.class, handler);
    }
    else {
      to.put(fromCls, handler);
    }
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
    if (toCls == null) {
      throw new NullPointerException("Parameter 'toCls' is not defined.");
    }

    Map<Class<?>, ConvertHandler<?>> to = find(converters, toCls);
    if (to == null) {
      throw new UnknownDataTypeException("Not found converters to class "+ toCls.getName() +".");
    }

    Class<?> key = value == null ? NullClass.class : value.getClass();
    ConvertHandler<?> handler = find(to, key);
    if (handler == null) {
      if (toCls.isEnum()) {
        return extractValueFromEnum(toCls, value);
      }
      else {
        handler = to.get(AnyClass.class);
      }
    }
    if (handler == null) {
      throw new UnknownDataTypeException("Not found converters to class "+ toCls.getName() +" from "+ (value == null ? "null" : value.getClass()) +".");
    }

    Object result = handler.convert(value);

    @SuppressWarnings("unchecked")
    T resultT = (T) result;
    return resultT;
  }

  private <T> T find(Map<Class<?>, T> map, Class<?> clazz) {
    for (Map.Entry<Class<?>, T> entry : map.entrySet()) {
      if (entry.getKey().isAssignableFrom(clazz)) {
        return entry.getValue();
      }
    }
    return null;
  }

  private <T> T extractValueFromEnum(Class<? extends T> toCls, Object value) {
    String strValue  = this.toType(String.class, value);

    for (T obj : toCls.getEnumConstants()) {
      if (obj.toString().equals(strValue)) {
        return obj;
      }
    }

    throw new CalculatingException("Enum " + toCls.getName() + " is not contain value " + value + ".");
  }

}
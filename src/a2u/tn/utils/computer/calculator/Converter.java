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


  public <T> T toType(Class<?> toCls, Object value) {
    if (toCls == null) {
      throw new NullPointerException("Parameter 'toCls' is not defined.");
    }

    Map<Class<?>, ConvertHandler<?>> to = find(converters, toCls);
    if (to == null) {
      throw new UnknownDataTypeException("Not found converters to class "+ toCls.getClass().getName() +".");
    }

    Class<?> key = value == null ? NullClass.class : value.getClass();
    ConvertHandler<?> handler = find(to, key);
    if (handler == null) {
      handler = to.get(AnyClass.class);
    }
    if (handler == null) {
      throw new UnknownDataTypeException("Not found converters to class "+ toCls.getClass().getName() +" from "+ (value == null ? "null" : value.getClass()) +".");
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

}

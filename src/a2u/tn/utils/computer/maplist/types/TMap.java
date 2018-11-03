package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Map type descriptor
 */
public class TMap extends Type {

  public TMap(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Map.class, null, value -> new LinkedHashMap<String, Objects>());
    converter.addConverter(Map.class, Map.class, value -> value);
  }

  @Override
  public Class<?> forClass() {
    return Map.class;
  }


  public boolean equal(Object v1, Object v2) {
    Map<String, Object> map1 = calculator.toType(Map.class, v1);
    Map<String, Object> map2 = calculator.toType(Map.class, v2);

    if (map1.keySet().size() != map1.keySet().size()) {
      return false;
    }
    for (String key : map1.keySet()) {
      if (!map2.keySet().contains(key)) {
        return false;
      }
      boolean isEquals = calculator.equalValues(map1.get(key), map2.get(key));
      if (!isEquals) {
        return false;
      }
    }
    return true;
  }

  public boolean notequal(Object v1, Object v2) {
    Map<String, Object> map1 = calculator.toType(Map.class, v1);
    Map<String, Object> map2 = calculator.toType(Map.class, v2);

    return !equal(map1, map2);
  }


  public Map<String, Object> xor(Object v1, Object v2) {
    Map<String, Object> map1 = calculator.toType(Map.class, v1);
    Map<String, Object> map2 = calculator.toType(Map.class, v2);

    Map<String, Object> diff = new LinkedHashMap<>();

    for (String key : map1.keySet()) {
      if (!map2.keySet().contains(key)) {
        diff.put(key, map1.get(key));
      }
      Object value1 = map1.get(key);
      Object value2 = map2.get(key);
      boolean isEquals = calculator.equalValues(value1, value2);
      if (!isEquals) {
        diff.put(key, value1);
      }
    }

    return diff;
  }

}

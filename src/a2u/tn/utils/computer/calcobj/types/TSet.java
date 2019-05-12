package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Set type descriptor
 */
public class TSet extends Type {

  public TSet(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Set.class, null, value -> new LinkedHashSet<>());
    converter.addConverter(Set.class, Set.class, value -> value);
    converter.addConverter(Set.class, Map.class, value -> ((Map)value).values());
    converter.addConverter(Set.class, Collection.class, value -> {
      Set<Object> set = new LinkedHashSet<>();
      set.addAll((Collection<?>) value);
      return set;
    });
    converter.addConverter(Set.class, Converter.AnyClass.class, value -> {
      Set<Object> set = new LinkedHashSet<>();
      set.add(value);
      return set;
    });
  }

  @Override
  public Class<?> forClass() {
    return Set.class;
  }


  @Override
  public Set<Object> plus(Object v1, Object v2) {
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);
    Set<Object> resultSet = new LinkedHashSet<>();
    resultSet.addAll(set1);
    resultSet.addAll(set2);
    return resultSet;
  }

  @Override
  public Set<Object> minus(Object v1, Object v2) {
    //delete values from v1, which present in v2
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);
    Set<Object> resultSet = new LinkedHashSet<>();
    for (Object obj1 : set1) {
      boolean isPresent = set2.contains(obj1);
      if (!isPresent) {
        resultSet.add(obj1);
      }
    }
    return resultSet;
  }

  @Override
  public boolean equal(Object v1, Object v2) {
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);

    if (set1.size() != set2.size()) {
      return false;
    }
    for (Object obj1 : set1) {
      boolean isPresent = set2.contains(obj1);
      if (!isPresent) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean notequal(Object v1, Object v2) {
    return !equal(v1, v2);
  }

  @Override
  public Set<Object> and(Object v1, Object v2) {
    //objects from v1, which present in v2
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);

    Set<Object> resultSet = new LinkedHashSet<>();
    for (Object obj1 : set1) {
      for (Object obj2 : set2) {
        boolean isPresent = set2.contains(obj1);
        if (isPresent) {
          resultSet.add(obj1);
          break;
        }
      }
    }
    return resultSet;
  }
  @Override
  public Set<Object> or(Object v1, Object v2) {
    //unique objects from v1 + v2, like distinct
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);

    Set<Object> resultSet = new LinkedHashSet<>();
    resultSet.add(set1);
    resultSet.add(set2);
    return resultSet;
  }

  @Override
  public Set<Object> xor(Object v1, Object v2) {
    //objects from v1, which is not present in v2
    Set<Object> set1 = calculator.toType(Set.class, v1);
    Set<Object> set2 = calculator.toType(Set.class, v2);

    Set<Object> resultSet = new LinkedHashSet<>();
    for (Object obj1 : set1) {
      if (!set2.contains(obj1)) {
        resultSet.add(obj1);
      }
    }
    for (Object obj2 : set2) {
      if (!set1.contains(obj2)) {
        resultSet.add(obj2);
      }
    }

    return resultSet;
  }


}

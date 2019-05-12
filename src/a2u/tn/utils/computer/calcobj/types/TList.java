package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * List type descriptor
 */
public class TList extends Type {

  public TList(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(List.class, null, value -> new ArrayList<>());
    converter.addConverter(List.class, List.class, value -> value);
    converter.addConverter(List.class, Collection.class, value -> {
      List<Object> list = new ArrayList<>();
      list.addAll((Collection<?>) value);
      return list;
    });
    converter.addConverter(List.class, Converter.AnyClass.class, value -> {
      List<Object> list = new ArrayList<>();
      list.add(value);
      return list;
    });
  }

  @Override
  public Class<?> forClass() {
    return List.class;
  }


  @Override
  public List<Object> plus(Object v1, Object v2) {
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);
    List<Object> resultList = new ArrayList<>();
    resultList.addAll(list1);
    resultList.addAll(list2);
    return resultList;
  }

  @Override
  public List<Object> minus(Object v1, Object v2) {
    //delete values from v1, which present in v2
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);
    List<Object> resultList = new ArrayList<>();
    for (Object obj1 : list1) {
      boolean isPresent = false;
      for (Object obj2 : list2) {
        isPresent = calculator.equalValues(obj1, obj2);
        if (isPresent) {
          break;
        }
      }
      if (!isPresent) {
        resultList.add(obj1);
      }
    }
    return resultList;
  }

  @Override
  public boolean equal(Object v1, Object v2) {
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);

    if (list1.size() != list2.size()) {
      return false;
    }
    for (Object obj1 : list1) {
      boolean isPresent = false;
      for (Object obj2 : list2) {
        isPresent = calculator.equalValues(obj1, obj2);
        if (isPresent) {
          break;
        }
      }
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
  public List<Object> and(Object v1, Object v2) {
    //objects from v1, which present in v2
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);

    List<Object> resultList = new ArrayList<>();
    for (Object obj1 : list1) {
      for (Object obj2 : list2) {
        boolean isPresent = calculator.equalValues(obj1, obj2);
        if (isPresent) {
          resultList.add(obj1);
          break;
        }
      }
    }
    return resultList;
  }
  @Override
  public List<Object> or(Object v1, Object v2) {
    //unique objects from v1 + v2, like distinct
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);

    List<Object> resultList = new ArrayList<>();
    addUnique(resultList, list1);
    addUnique(resultList, list2);
    return resultList;
  }

  @Override
  public List<Object> xor(Object v1, Object v2) {
    //objects from v1, which is not present in v2
    List<Object> list1 = calculator.toType(List.class, v1);
    List<Object> list2 = calculator.toType(List.class, v2);

    List<Object> resultList = new ArrayList<>();
    Set<Object> set1 = new LinkedHashSet<>();
    Set<Object> set2 = new LinkedHashSet<>();
    set1.addAll(list1);
    set2.addAll(list2);
    for (Object obj1 : list1) {
      if (!set2.contains(obj1)) {
        resultList.add(obj1);
      }
    }
    for (Object obj2 : list2) {
      if (!set1.contains(obj2)) {
        resultList.add(obj2);
      }
    }

    return resultList;
  }

  private void addUnique(List<Object> resultList, List<Object> valueList) {
    for (Object obj : valueList) {
      boolean isPresent = false;
      for (Object objRes : resultList) {
        isPresent = calculator.equalValues(obj, objRes);
        if (isPresent) {
          break;
        }
      }
      if (!isPresent) {
        resultList.add(obj);
      }
    }
  }

}

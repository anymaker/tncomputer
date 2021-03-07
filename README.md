![workflow](https://github.com/anymaker/tncomputer/actions/workflows/test.yml/badge.svg)
![workflow](https://github.com/anymaker/tncomputer/actions/workflows/publish-release.yml/badge.svg)

![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/com.github.anymaker/tncomputer?server=https%3A%2F%2Foss.sonatype.org)
![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/com.github.anymaker/tncomputer/0)

# tncomputer
Computation with Extendable Query Language

## Goal
1. Creating an extensible mechanism for making calculations with structured data.
2. Creating a mechanism for searching values in structured data with filtering and computations.
3. Creating a fully customizable mechanism for calculating and converting values of different types.

This is like xpath for working with xml or like sql for working with databases. \
The module allows you to manipulate any data from any sources - json, file system, or a java object, and of course from XML and databases.
As a matter of fact, the data can be in absolutely any format.
Data may not exist at all - you yourself can specify a method for extracting data from a source or a method for generating it.


Look at [wiki](https://github.com/anymaker/tncomputer/wiki)
or [Demo.java](https://github.com/anymaker/tncomputer/blob/master/test/a2u/tn/utils/computer/Demo.java)
for see posibilities.


## Get jar
You can use maven dependensy
```xml
    <dependency>
      <groupId>com.github.anymaker</groupId>
      <artifactId>tncomputer</artifactId>
      <version>0.0.17</version>
    </dependency>
```
Or download jar from https://mvnrepository.com/artifact/com.github.anymaker/tncomputer


## Note
Currently this is a experimental project and it has no fixed API.

## Simple usage
Calculating with extract values by code from any objects such as plain object and Map or List instances. \
See details here [Wiki: Simple usage](https://github.com/anymaker/tncomputer/wiki/Simple-usage)

```java
import a2u.tn.utils.computer.calcobj.ObjCalcEngine;

public class Main {
  private static ObjCalcEngine engine = new ObjCalcEngine();

  private int     fieldA = 123;
  private String  fieldB = "BBB";
  private Main    fieldC;

  public static void main(String[] args) {
    Main startObj = new Main();
    startObj.fieldC = startObj;

    System.out.println(engine.calc("'1: ' + .fieldA", startObj));
    System.out.println(engine.calc("'2: ' + .fieldB", startObj));
    System.out.println(engine.calc("'3: ' + .fieldC", startObj));
    System.out.println(engine.calc("'4: ' + .fieldC.fieldA * 10", startObj));
    System.out.println(engine.calc("'5: ' + .fieldA + .fieldC.fieldA", startObj));
    System.out.println(engine.calc("'6: ' + (.fieldA + .fieldC.fieldA)", startObj));
    System.out.println(engine.calc("'7: ' + .fieldB + ' -> ' + .fieldC.fieldA", startObj));
  }

  @Override
  public String toString() {
    return fieldA + ", " + fieldB;
  }
}
```
Output
```
1: 123
2: BBB
3: 123, BBB
4: 1230
5: 123123
6: 246
7: BBB -> 123
```

## With own value extraction method

```java
import a2u.tn.utils.computer.calcobj.ObjCalcEngine;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main2 {

  public static class ID {
    Object value;
    String sourceName;

    public ID(Object value, String sourceName) {
      this.value = value;
      this.sourceName = sourceName;
    }

    public Map<String, Object> loadObj() {
      //todo: Your implementation to load an object from a source
      Map<String, Object> data = new LinkedHashMap<String, Object>();
      data.put("field01", "AAA");
      return data;
    }

    public String toString() {
      return value+"@"+sourceName;
    }
  }

  //We extend this class to override the method that extract values
  private static ObjCalcEngine engine = new ObjCalcEngine() {

    //We override this method to get values in our own way
    /**
     * Extract value from object 'fromObj' by code
     * @param byCode code for extracting values
     * @param fromObj objects for extracting values
     * @return Values
     */
    @Override
    protected Object extractValue(String byCode, Object fromObj) throws Exception {
      if (fromObj instanceof ID) {
        //own way
        Map<String, Object> objData = ((ID)fromObj).loadObj();
        return objData.get(byCode);
      }
      else {
        //standart way
        return super.extractValue(byCode, fromObj);
      }
    }
  };


  public static void main(String[] args) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("value01", new ID(12345, "table01"));
    map.put("value02", "BBB");
    System.out.println(engine.calc(".value01", map));
    System.out.println(engine.calc(".value01.field01", map));
    System.out.println(engine.calc(".value02", map));
    System.out.println(engine.calc(".value01.field01 + .value02", map));

    System.out.println(engine.calc("true"));
    System.out.println(Boolean.TRUE.equals(engine.calc("true")));
    System.out.println(Boolean.FALSE.equals(engine.calc("true")));
  }

}
```
Output
```
12345@table01
AAA
BBB
AAABBB
```

The *ID* class is your own class that contains the data to retrieve the values ​​(*value* and *sourceName*).
This class can retrieve values ​​using the *loadObj()* method.

You can override the *extractValues* method in the *ObjCalcEngine* class.
```java
protected Collection<Object> extractValues(String byCode, Collection<Object> fromObjList)
```
where check *instanceof* to determine when you should call *ID.loadObj()*.
```java
if (obj instanceof ID) {
  //own way
  Map<String, Object> objData = ((ID)obj).loadObj();
  resultList.add(objData.get(byCode));
}
```

**PS:** Do not use this method to get a lot of rows from the database. \
You can use *SqlBuilder* to create sql-query by formula. \
*SqlBuilder* will be available soon.
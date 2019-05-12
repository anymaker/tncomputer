package a2u.tn.utils.computer;

import a2u.tn.utils.computer.formula.Formula;
import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.json.TnJson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Demo {

  private Map<String, Object> map;

  @Before
  public void fillMap() {
    String json =
    "{\n" +
    "  event: {\n" +
    "    id: 10,\n" +
    "    name: 'Event10',\n" +
    "    date: '01.01.2018', \n" +
    "    requestList: [\n" +
    "      {\n" +
    "        id: 'r01',\n" +
    "        date: '01.01.2018',\n" +
    "        rowList: [\n" +
    "          {\n" +
    "            num: 1,\n" +
    "            date: '01.01.2018',\n" +
    "            state: 'new'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 2,\n" +
    "            date: '02.01.2018',\n" +
    "            state: 'registred'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 3,\n" +
    "            date: '03.01.2018',\n" +
    "            state: 'viewed'\n" +
    "          }          \n" +
    "        ]\n" +
    "      },\n" +
    "      {\n" +
    "        id: 'r02',\n" +
    "        date: '27.05.2018',\n" +
    "        rowList: [\n" +
    "          {\n" +
    "            num: 1,\n" +
    "            date: '27.05.2018',\n" +
    "            state: 'new'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 2,\n" +
    "            date: '28.05.2018',\n" +
    "            state: 'registred'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 3,\n" +
    "            date: '29.05.2018',\n" +
    "            state: 'viewed'\n" +
    "          }          \n" +
    "        ]\n" +
    "      },\n" +
    "      {\n" +
    "        id: 'r03',\n" +
    "        date: '12.06.2018',\n" +
    "        rowList: [\n" +
    "          {\n" +
    "            num: 1,\n" +
    "            date: '12.06.2018',\n" +
    "            state: 'new'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 2,\n" +
    "            date: '13.06.2018',\n" +
    "            state: 'registred'\n" +
    "          },\n" +
    "          {\n" +
    "            num: 3,\n" +
    "            date: '14.06.2018',\n" +
    "            state: 'viewed'\n" +
    "          }          \n" +
    "        ]\n" +
    "      }\n" +
    "    ]\n" +
    "  }\n" +
    "}";

    map = TnJson.parse(json);
  }



  @Test
  public void simpleCalculation() {

    //Literal
    check("5",        "{Long:5}");
    check("5.1",      "{Double:5.1}");
    check("-5",       "{Long:-5}");
    check("'string'", "{String:'string'}");
    check("true",     "{Boolean:true}");
    check("false",    "{Boolean:false}");
    check("null",     "{literal:null}");

    //Dimension
    check("2,3",           "{List:[2,3]}");
    check("(2, 3)",        "{List:[2,3]}");
    check("2,3,4",         "{List:[2,3,4]}");
    check("(2,3,4)",       "{List:[2,3,4]}");
    check("1+5=6, 2 or 4", "{List:[true,true]}");
    check("1+5=6, 2 = 4",  "{List:[true,false]}");

    check("1 + 2",        "{Long:3}");
    check("'str' + '23'", "{String:'str23'}");
    check("'str' + 23",   "{String:'str23'}");

    check("2 - 1", "{Long:1}");
    check("2 - 2", "{Long:0}");
    check("2 - 3", "{Long:-1}");

    check("2 * 0",    "{Long:0}");
    check("2 * 1",    "{Long:2}");
    check("2 * 3",    "{Long:6}");
    check("5.1 * 10", "{Double:51.0}");
    check("10 * 5.1", "{Long:50}");
    check("51.0 = 51", "{Boolean:true}");

    check("6 / 1",   "{Long:6}");
    check("6 / 2",   "{Long:3}");
    check("6 / 6",   "{Long:1}");
    check("6.2 / 2", "{Double:3.1}");
    check("6 / 2.1", "{Long:3}");
    check("0 / 2",   "{Long:0}");
    check("6.1 / 0", "{Double:Infinity}");
    check("-6.1 / 0", "{Double:-Infinity}");
    checkError("6 / 0", "java.lang.ArithmeticException:/ by zero");

    check("2 = 2", "{Boolean:true}");
    check("2 = 3", "{Boolean:false}");

    check("2 != 3", "{Boolean:true}");
    check("2 != 2", "{Boolean:false}");

    check("2 > 3",  "{Boolean:false}");
    check("2 > 2",  "{Boolean:false}");
    check("2 > 1",  "{Boolean:true}");

    check("2 >= 3", "{Boolean:false}");
    check("2 >= 2", "{Boolean:true}");
    check("2 >= 1", "{Boolean:true}");

    check("2 < 3",  "{Boolean:true}");
    check("2 < 2",  "{Boolean:false}");
    check("2 < 1",  "{Boolean:false}");

    check("2 <= 3", "{Boolean:true}");
    check("2 <= 2", "{Boolean:true}");
    check("2 <= 1", "{Boolean:false}");

    check("2 in 2", "{Boolean:true}");
    check("2 in (2,3,4)", "{Boolean:true}");
    check("1 in (2,3,4)", "{Boolean:false}");

    check("2 notin (2,3,4)", "{Boolean:false}");
    check("1 notin (2,3,4)", "{Boolean:true}");

    check("2 in (2,3,true)", "{Boolean:true}");
    check("2 in (2,'str')",  "{Boolean:true}");
    checkError("2 in (3,'str')", "java.lang.NumberFormatException:For input string: \"str\"");

    check("1 and 2",     "{Boolean:true}");
    check("1 and 0",     "{Boolean:false}");
    check("1 and 'str'", "{Boolean:true}");
    check("0 and 0",     "{Boolean:false}");

    check("1>2 and 2>3", "{Boolean:false}");
    check("1>2 and 2<3", "{Boolean:false}");
    check("1<2 and 2<3", "{Boolean:true}");


    check("1 or 2",     "{Boolean:true}");
    check("1 or 0",     "{Boolean:true}");
    check("1 or 'str'", "{Boolean:true}");
    check("0 or 0",     "{Boolean:false}");

    check("1>2 or 2>3", "{Boolean:false}");
    check("1>2 or 2<3", "{Boolean:true}");
    check("1<2 or 2<3", "{Boolean:true}");


    check("1 xor 2",     "{Boolean:false}");
    check("1 xor 0",     "{Boolean:true}");
    check("0 xor 0",     "{Boolean:false}");
    check("1 xor 'str'", "{Boolean:false}");

    check("1>2 xor 2>3", "{Boolean:false}");
    check("1>2 xor 2<3", "{Boolean:true}");
    check("1<2 xor 2<3", "{Boolean:false}");


    //Prioritet

    check("1+2*5",   "{Long:11}");
    check("1-2*5",   "{Long:-9}");
    check("1+2*5-6", "{Long:5}");
    check("(1+2)*5", "{Long:15}");

    check("(1+2)*5 + 2*(3+2)",   "{Long:25}");
    check("(1+2)*5 > 2*(3+2)",   "{Boolean:true}");
    check("(1+2)*5 < 2*(3+2)",   "{Boolean:false}");
    check("(1+2)*5 and 2*(3+2)", "{Boolean:true}");
    check("(1+2)*5 and 2*(2-3)", "{Boolean:false}");

    check("5 and 2 in(2,3)", "{Boolean:true}");
    check("0 and 2 in(2,3)", "{Boolean:false}");
    check("5 and 2 in(3,4)", "{Boolean:false}");

    check("5+2, 6+7",        "{List:[7,13]}");
    check("5>2, 6>7",        "{List:[true,false]}");
    check("5<2, 2 in(2,3)",  "{List:[false,true]}");
  }

  @Test
  public void withLists() {
    check("(1,2,3) + (3,4,5)",   "{List:[1,2,3,3,4,5]}");
    check("(1,2,3) - (3,4,5)",   "{List:[1,2]}");
    check("(1,2,3) = (3,4,5)",   "{Boolean:false}");
    check("(1,2,3) = (3,2,1)",   "{Boolean:true}");
    check("(1,2,3) != (3,2,1)",  "{Boolean:false}");

    check("(1,2,3) and (3,2,1)", "{List:[1,2,3]}");
    check("(1,2,3) and (3,2)",   "{List:[2,3]}");
    check("(1,2) or (3,2)",      "{List:[1,2,3]}");
    check("(1,2) xor (3,2)",     "{List:[1,3]}");
  }

  @Test
  public void withLists02() {
    List<String> list1 = new ArrayList<String>() {{
      add("1");
      add("2");
    }};
    List<String> list2 = new ArrayList<String>() {{
      add("2");
      add("3");
    }};
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("list1", list1);
    map.put("list2", list2);
    Formula formula = new Formula(".list1 xor .list2");

    ObjCalcEngine engine = new ObjCalcEngine();
    Object res = engine.calc(formula, map);

    String jsonResult = jsonbuilder.buildJson(res);
    assertEquals("['1','3']", jsonResult);
  }

  @Test
  public void withMap() {
    //check(".event.requestList(.id='r01') xor .event.requestList(.id='r02')", "{List:[1,2,3,1,2,3,1,2,3]}");
  }

  @Test
  public void functions() {
    check(".event.requestList.rowList.num",           "{List:[1,2,3]}");
    check(".event.requestList.rowList.num(distinct)", "{List:[1,2,3]}");
    check(".event.requestList.rowList.num(first)", "{Integer:1}");
    check(".event.requestList.rowList.num(last)",  "{Integer:3}");

    check(".event.requestList.rowList", "{List:[{num:1,date:'01.01.2018',state:'new'},{num:2,date:'02.01.2018',state:'registred'},{num:3,date:'03.01.2018',state:'viewed'},{num:1,date:'27.05.2018',state:'new'},{num:2,date:'28.05.2018',state:'registred'},{num:3,date:'29.05.2018',state:'viewed'},{num:1,date:'12.06.2018',state:'new'},{num:2,date:'13.06.2018',state:'registred'},{num:3,date:'14.06.2018',state:'viewed'}]}");

    check(".event.requestList.rowList(.num = rownum)",             "{literal:null}");
    check(".event.requestList.rowList(rownum=1)",                  "{Map:{num:2,date:'02.01.2018',state:'registred'}}");
    check(".event.requestList.rowList(.num = rownum+1).date",      "{List:['01.01.2018','02.01.2018','03.01.2018']}");
    check(".event.requestList.rowList(.num + 8 = rowscount).date", "{List:['01.01.2018','27.05.2018','12.06.2018']}");
    check(".event.requestList(todate(.date, 'dd.MM.yyyy') > todate('01.01.2018', 'dd.MM.yyyy')).date",    "{List:['27.05.2018','12.06.2018']}");
    check("datetostr( todate(.event.requestList(.id='r01').date, 'dd.MM.yyyy'), 'dd.MM.yyyy hh:mm:ss' )", "{String:'01.01.2018 12:00:00'}");  //!!! 12:00 ???

    check("DateToStr( todate(.event.requestList(.id='r01').date, 'dd.MM.yyyy'), 'dd.MM.yyyy hh:mm:ss' )", "{String:'01.01.2018 12:00:00'}");
    check("DATETOSTR( todate(.event.requestList(.id='r01').date, 'dd.MM.yyyy'), 'dd.MM.yyyy hh:mm:ss' )", "{String:'01.01.2018 12:00:00'}");
  }

  @Test
  public void select() {
    check(".", "{Map:{event:{id:10,name:'Event10',date:'01.01.2018',requestList:[{id:'r01',date:'01.01.2018',rowList:[{num:1,date:'01.01.2018',state:'new'},{num:2,date:'02.01.2018',state:'registred'},{num:3,date:'03.01.2018',state:'viewed'}]},{id:'r02',date:'27.05.2018',rowList:[{num:1,date:'27.05.2018',state:'new'},{num:2,date:'28.05.2018',state:'registred'},{num:3,date:'29.05.2018',state:'viewed'}]},{id:'r03',date:'12.06.2018',rowList:[{num:1,date:'12.06.2018',state:'new'},{num:2,date:'13.06.2018',state:'registred'},{num:3,date:'14.06.2018',state:'viewed'}]}]}}}");
    check(".event.requestList.id",                                 "{List:['r01','r02','r03']}");
    check(".event.requestList(.id='r01').id",                      "{String:'r01'}");
    check(".event.requestList.rowList.num",                        "{List:[1,2,3]}");
    check(".event.requestList.rowList.num(. > 1)",                 "{List:[2,3]}");
    check(".event.requestList.rowList.num(. > 1 and distinct)",    "{List:[2,3]}");
    check(".event.requestList.rowList(.num > 1).num",              "{List:[2,3]}");
    check(".event.requestList.rowList(.num > 1 and distinct).num", "{List:[2,3]}");
    check(".event.requestList.rowList(.num > 1).num(distinct)",    "{List:[2,3]}");
  }


  @Test
  public void testPriority() {
    check("1+2+3+4+5", "{Long:15}");
    check("1+2+3+4*5", "{Long:26}");
    check("1+2+3*4+5", "{Long:20}");
    check("1+2*3+4+5", "{Long:16}");
    check("1*2+3+4+5", "{Long:14}");

    check("1*2*3+4+5", "{Long:15}");
    check("1*2*3*4+5", "{Long:29}");
    check("1*2*3*4*5", "{Long:120}");
    check("1+2*3*4*5", "{Long:121}");
    check("1+2+3*4*5", "{Long:63}");

    check("1*2+3+4*5", "{Long:25}");
    check("1*2*3+4*5", "{Long:26}");
    check("1*2+3*4*5", "{Long:62}");
    check("1+2*3*4+5", "{Long:30}");

    check("1+2*3+4*5+6*7", "{Long:69}");
    check("1,2*3+4*5+6*7", "{List:[1,68]}");
    check("1+2,3+4*5+6*7", "{List:[3,65]}");
    check("1+2*3,4*5+6*7", "{List:[7,62]}");
    check("1+2*3+4,5+6*7", "{List:[11,47]}");
    check("1+2*3+4*5,6*7", "{List:[27,42]}");
    check("1+2*3+4*5+6,7", "{List:[33,7]}");

    check("1+2*3+4*5,6,7", "{List:[27,6,7]}");
    check("1+2*3+4,5,6,7", "{List:[11,5,6,7]}");
    check("1+2*3,4,5,6,7", "{List:[7,4,5,6,7]}");
    check("1+2,3,4,5,6,7", "{List:[3,3,4,5,6,7]}");
    check("1,2,3,4,5,6,7", "{List:[1,2,3,4,5,6,7]}");
    check("1,2,3,4,5,6*7", "{List:[1,2,3,4,5,42]}");
    check("1,2,3,4,5+6*7", "{List:[1,2,3,4,47]}");
    check("1,2,3,4*5+6*7", "{List:[1,2,3,62]}");
    check("1,2,3+4*5+6*7", "{List:[1,2,65]}");
    check("1,2*3+4*5+6*7", "{List:[1,68]}");

    check("1,2*3+4*5+6,7", "{List:[1,32,7]}");
    check("1,2,3+4*5,6,7", "{List:[1,2,23,6,7]}");
    check("1,2,3,4*5,6,7", "{List:[1,2,3,20,6,7]}");
    check("1,2,3+4,5,6,7", "{List:[1,2,7,5,6,7]}");
    check("1+2*3,4,5+6*7", "{List:[7,4,47]}");
    check("1+2,3,4,5,6*7", "{List:[3,3,4,5,42]}");
  }

  TnJson jsonbuilder = TnJson.builder().readable().withoutKeyQuote().singleQuote().keepNull();
  private void check(String query, String value) {
    Object result = null;
    String jsonResult = null;

    try {
      ObjCalcEngine engine = new ObjCalcEngine();
      result = engine.calc(query, map);


      Map<String, Object> mapval = new HashMap<>();
      String key;
      if (result == null) {
        key = "literal";
      }
      else if (result instanceof Collection) {
        key = "List";
      }
      else if (result instanceof Map) {
        key = "Map";
      }
      else {
        key = result.getClass().getSimpleName();
      }
      mapval.put(key, result);
      jsonResult = jsonbuilder.buildJson(mapval);

      assertEquals(value, jsonResult);
    }
    catch (Throwable ex) {
      System.out.println(query);
      System.out.println(result);
      System.out.println(jsonResult);
      throw ex;
    }
  }

  private void checkError(String query, String value) {
    Object result;
    String errMsg;
    try {
      ObjCalcEngine engine = new ObjCalcEngine();
      result = engine.calc(query, map);

      System.out.println(query);
      System.out.println(result);
      throw new AssertionError("No Error:"+ value +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getName() + ":" + ex.getMessage();
    }

    try {
      assertEquals(value, errMsg);
    }
    catch (Throwable ex) {
      System.out.println(query);
      System.out.println(errMsg);
      throw ex;
    }
  }

  @Test
  public void test01() {
    check("max((2,3,2,-1))", "{Long:3}");
  }

}

package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.formula.Formula;
import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.json.TnJson;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {

  private Map<String, Object> map;

  @Before
  public void fill() {
    String json =
      "{\n" +
        " mailList: [\n" +
        "   {\n" +
        "     author: 'Author01',\n" +
        "     body: 'body01',\n" +
        "     attachment: {\n" +
        "       type: 'text',\n" +
        "       size: 100\n" +
        "     }\n" +
        "   },\n" +
        "   {\n" +
        "     author: 'Author02',\n" +
        "     body: 'body02',\n" +
        "     attachment: {\n" +
        "       type: 'pict',\n" +
        "       size: 90\n" +
        "     }\n" +
        "   },\n" +
        "   {\n" +
        "     author: 'Author03',\n" +
        "     body: 'body03',\n" +
        "     attachment: null\n" +
        "   },\n" +
        "   {\n" +
        "     author: 'Author01',\n" +
        "     body: 'body04'\n" +
        "   }\n" +
        " ]\n" +
        "}";
    map = TnJson.parse(json);
  }

  @Test
  public void countTest() {

    check("count(.mailList.attachment)", "{Integer:2}");

    check(".mailList",                                  "{List:[{author:'Author01',body:'body01',attachment:{type:'text',size:100}},{author:'Author02',body:'body02',attachment:{type:'pict',size:90}},{author:'Author03',body:'body03',attachment:null},{author:'Author01',body:'body04'}]}");
    check(".mailList.attachment",                       "{List:[{type:'text',size:100},{type:'pict',size:90},null]}");
    check(".mailList(count(.attachment)>0).attachment", "{List:[{type:'text',size:100},{type:'pict',size:90}]}");
    check(".mailList(count(.attachment)>0).author",      "{List:['Author01','Author02']}");

  }

  @Test
  public void distinctTest() {
    check(".mailList.author(distinct)", "{List:['Author01','Author02','Author03']}");
  }

  @Test
  public void falseTest() {
    check("false", "{Boolean:false}");
  }

  @Test
  public void trueTest() {
    check("true", "{Boolean:true}");
  }

  @Test
  public void nullTest() {
    check("null", "{literal:null}");
  }

  @Test
  public void nvlTest() {
    check("nvl(null, 5)", "{Long:5}");
    check("nvl(33, 5)", "{Long:33}");
    check(".mailList(nvl(.attachment.size, 555) > 100)", "{List:[{author:'Author03',body:'body03',attachment:null},{author:'Author01',body:'body04'}]}");
  }

  @Test
  public void decodeTest() {
    check("decode(.mailList.attachment.type,  'pict', 'photo',  'text', 'message',    null, 'no attach')", "{List:['message','photo','no attach']}");
    check("decode(.mailList.attachment.type,  'pict', 'photo',   null,  'no attach',  'default')", "{List:['default','photo','no attach']}");
  }

  @Test
  public void firstRowTest() {
    check(".mailList.author(first)", "{String:'Author01'}");
  }

  @Test
  public void lastRowTest() {
    check(".mailList.author(last)", "{String:'Author03'}");
  }

  @Test
  public void likeTest() {
    check(".mailList(like(.author, '%or01'))", "{List:[{author:'Author01',body:'body01',attachment:{type:'text',size:100}},{author:'Author01',body:'body04'}]}");
    check("like('Author03', '%or01')", "{Boolean:false}");
    check("like('Author03', '%or03')", "{Boolean:true}");
  }

  @Test
  public void LikeRegexpTest() {
    check(".mailList(likeRegexp(.author, '.+or01'))", "{List:[{author:'Author01',body:'body01',attachment:{type:'text',size:100}},{author:'Author01',body:'body04'}]}");
    check("likeRegexp('Author03', '.+or01')", "{Boolean:false}");
    check("likeRegexp('Author03', '.+or03')", "{Boolean:true}");
  }

    @Test
  public void maxTest() {
    check("max(.mailList.attachment.size)", "{Integer:100}");
    check(".mailList(.attachment.size = maxinrows(.attachment.size))", "{Map:{author:'Author01',body:'body01',attachment:{type:'text',size:100}}}");
    check("max(.mailList.author)", "{String:'Author03'}");
    check("max((1,2,3,4,5))", "{Long:5}");
  }

  @Test
  public void minTest() {
    check("min(.mailList.attachment.size)", "{Integer:90}");
    check("min(.mailList.author)", "{String:'Author01'}");
  }

  @Test
  public void rowNumTest() {
    check(".mailList.author(rowNum=0)", "{String:'Author01'}");
    check(".mailList.author(rowNum=1)", "{String:'Author02'}");
    check(".mailList.author(rowNum=2)", "{String:'Author03'}");
    check(".mailList.author(rowNum=3)", "{literal:null}");
  }

  @Test
  public void rowsCountTest() {
    check(".mailList.author(rowNum=rowsCount)",   "{literal:null}");
    check(".mailList.author(rowNum=rowsCount-1)", "{String:'Author03'}");
  }

  @Test
  public void toDateTest() {
    check("todate('01.01.2018', 'dd.MM.yyyy')",   "{Date:'2018.00.01-00.00.00'}");
  }

  @Test
  public void toStrTest() {
    check("datetostr(todate('01.01.2018', 'dd.MM.yyyy'), 'yyyy.MM.dd')",   "{String:'2018.01.01'}");
  }



  private TnJson jsonSettings = TnJson.builder().readable().withoutKeyQuote().singleQuote().keepNull();

  private void check(String query, String value) {
    Object result = null;
    String jsonResult = null;
    try {

      Formula formula = new Formula(query);
      ObjCalcEngine engine = new ObjCalcEngine();
      result = engine.calc(formula, map);


      Map<String, Object> mapval = new HashMap<>();
      String key;
      if (result == null) {
        key = "literal";
        mapval.put(key, result);
      }
      else if (result instanceof Collection) {
        key = "List";
        mapval.put(key, result);
      }
      else if (result instanceof Map) {
        key = "Map";
        mapval.put(key, result);
      }
      else if (result instanceof Date) {
        key = "Date";
        DateFormat df = new SimpleDateFormat("yyyy.mm.dd-HH.mm.ss");
        String formatedDate = df.format(result);
        mapval.put(key, formatedDate);
      }
      else {
        key = result.getClass().getSimpleName();
        mapval.put(key, result);
      }

      jsonResult = jsonSettings.buildJson(mapval);

      assertEquals(value, jsonResult);
    }
    catch (Exception ex) {
      System.out.println(query);
      System.out.println(result);
      System.out.println(jsonResult);
      throw ex;
    }
  }
  private void checkError(String query, String error) {
    String errMsg;
    try {
      Formula formula = new Formula(query);
      ObjCalcEngine engine = new ObjCalcEngine();
      Object result = engine.calc(formula, map);

      System.out.println(formula.toFormated());
      throw new AssertionError("No Error:"+ error +".");
    }
    catch (Throwable ex) {
      errMsg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    assertEquals(error, errMsg);
  }


}
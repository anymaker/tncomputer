package a2u.tn.utils.computer.calcobj;

import a2u.tn.utils.computer.calcobj.functions.auxiliary.Decode;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.IfNotNull;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.Ifnn;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.Length;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.Nvl;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.ToList;
import a2u.tn.utils.computer.calcobj.functions.auxiliary.ToMap;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddDays;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddHours;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddMinutes;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddMonths;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddSeconds;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddWeeks;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddYears;
import a2u.tn.utils.computer.calcobj.functions.datetime.DateToStr;
import a2u.tn.utils.computer.calcobj.functions.datetime.LastDayInMonth;
import a2u.tn.utils.computer.calcobj.functions.datetime.LastDayInYear;
import a2u.tn.utils.computer.calcobj.functions.datetime.SysDate;
import a2u.tn.utils.computer.calcobj.functions.datetime.SysTime;
import a2u.tn.utils.computer.calcobj.functions.datetime.ToDate;
import a2u.tn.utils.computer.calcobj.functions.forcollections.Count;
import a2u.tn.utils.computer.calcobj.functions.forcollections.Max;
import a2u.tn.utils.computer.calcobj.functions.forcollections.Min;
import a2u.tn.utils.computer.calcobj.functions.incollection.Distinct;
import a2u.tn.utils.computer.calcobj.functions.incollection.First;
import a2u.tn.utils.computer.calcobj.functions.incollection.Last;
import a2u.tn.utils.computer.calcobj.functions.incollection.MaxInRows;
import a2u.tn.utils.computer.calcobj.functions.incollection.RowNum;
import a2u.tn.utils.computer.calcobj.functions.incollection.RowsCount;
import a2u.tn.utils.computer.calcobj.functions.staticvalue.False;
import a2u.tn.utils.computer.calcobj.functions.staticvalue.Nil;
import a2u.tn.utils.computer.calcobj.functions.staticvalue.Null;
import a2u.tn.utils.computer.calcobj.functions.staticvalue.True;
import a2u.tn.utils.computer.calcobj.functions.string.CharAt;
import a2u.tn.utils.computer.calcobj.functions.string.EndWith;
import a2u.tn.utils.computer.calcobj.functions.string.ExtractDigits;
import a2u.tn.utils.computer.calcobj.functions.string.Format;
import a2u.tn.utils.computer.calcobj.functions.string.IndexOf;
import a2u.tn.utils.computer.calcobj.functions.string.IsBlank;
import a2u.tn.utils.computer.calcobj.functions.string.IsEmpty;
import a2u.tn.utils.computer.calcobj.functions.string.LTrim;
import a2u.tn.utils.computer.calcobj.functions.string.Like;
import a2u.tn.utils.computer.calcobj.functions.string.NormalizeSpaces;
import a2u.tn.utils.computer.calcobj.functions.string.PadLeft;
import a2u.tn.utils.computer.calcobj.functions.string.PadRight;
import a2u.tn.utils.computer.calcobj.functions.string.RTrim;
import a2u.tn.utils.computer.calcobj.functions.string.RemoveSpaces;
import a2u.tn.utils.computer.calcobj.functions.string.RemoveWhitespaces;
import a2u.tn.utils.computer.calcobj.functions.string.Replace;
import a2u.tn.utils.computer.calcobj.functions.string.StartsWith;
import a2u.tn.utils.computer.calcobj.functions.string.Substring;
import a2u.tn.utils.computer.calcobj.functions.string.ToString;
import a2u.tn.utils.computer.calcobj.functions.string.Trim;
import a2u.tn.utils.computer.calcobj.functions.string.TrimToNull;
import a2u.tn.utils.computer.calcobj.types.TBool;
import a2u.tn.utils.computer.calcobj.types.TDate;
import a2u.tn.utils.computer.calcobj.types.TDouble;
import a2u.tn.utils.computer.calcobj.types.TFloat;
import a2u.tn.utils.computer.calcobj.types.TInt;
import a2u.tn.utils.computer.calcobj.types.TList;
import a2u.tn.utils.computer.calcobj.types.TLong;
import a2u.tn.utils.computer.calcobj.types.TMap;
import a2u.tn.utils.computer.calcobj.types.TNull;
import a2u.tn.utils.computer.calcobj.types.TSet;
import a2u.tn.utils.computer.calcobj.types.TString;
import a2u.tn.utils.computer.calculator.Calculator;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Calculator with extract values by code from any objects such as plain object and Map or List instances
 */
public class ObjCalcEngine extends Calculator {
  private static ObjCalcEngine instance = new ObjCalcEngine();

  public static ObjCalcEngine getInstance() {
    return instance;
  }


  public ObjCalcEngine() {
    fillFunctions();
    fillTypes();
  }


  /**
   * Run computing using query as string with converting type
   * @param query query as string
   * @param startObj object for begin computing
   * @param type class to which the value will be cast
   * @param <T> value final type
   * @return result of calculations
   */
  @SuppressWarnings("unchecked")
  public <T> T calc(String query, Object startObj, Class<? extends T> type) {
    Object obj = super.calc(query, startObj);

    if (obj == null) {
      return null;
    }

    if (type.isAssignableFrom(obj.getClass())) {
      return (T) obj;
    }

    T result = super.toType(type, obj);
    return result;
  }




  @SuppressWarnings("unchecked")
  @Override
  protected Collection<Object> extractValues(String byCode, Collection<Object> fromObjList) {
    if (fromObjList == null) {
      return null;
    }

    Set<Object> valueListSet = new LinkedHashSet<>();

    for (Object fromObj : fromObjList) {
      try {
        if (fromObj == null) {
          valueListSet.add(null); //one null must be present in the collection
        }
        else if (byCode.length() == 0) {
          if (fromObj instanceof Collection) {
            valueListSet.addAll((Collection<?>) fromObj);
          }
          else {
            valueListSet.add(fromObj);
          }
        }
        else {
          Object val = extractValue(byCode, fromObj);

          if (val instanceof Collection) {
            valueListSet.addAll((Collection<Object>) val);
          }
          else {
            valueListSet.add(val);
          }
        }

      }
      catch (Exception ex) {
        throw new RuntimeException("Error on extract value by code '"+byCode+"' from object '"+String.valueOf(fromObj)+"'.");
      }

    }
    return valueListSet;
  }

  /**
   * Extract value from object 'fromObj' by code
   * @param byCode code for extracting values
   * @param fromObj objects for extracting values
   * @return Values
   */
  protected Object extractValue(String byCode, Object fromObj) throws Exception {
    if (fromObj instanceof Map) {
      Map map = (Map) fromObj;
      return map.get(byCode);
    }
    else if (fromObj instanceof CharSequence || fromObj instanceof Number || fromObj instanceof Boolean) {
      throw new IllegalArgumentException("Object '"+fromObj+"' with class "+fromObj.getClass()+" is incorrect for extract field "+byCode+".");
    }
    else {
      Class<?> cls = fromObj.getClass();
      Field fld;
      try {
        fld = cls.getDeclaredField(byCode);
      }
      catch (NoSuchFieldException e) {
        return null;
      }

      fld.setAccessible(true);
      return fld.get(fromObj);
    }
  }


  private void fillFunctions() {
    addFunction(new Null());
    addFunction(new Nil());
    addFunction(new True());
    addFunction(new False());

    addFunction(new Nvl());
    addFunction(new IfNotNull());
    addFunction(new Ifnn());
    addFunction(new Decode());
    addFunction(new ToMap());
    addFunction(new ToList());

    addFunction(new Distinct());
    addFunction(new First());
    addFunction(new Last());

    addFunction(new RowNum());
    addFunction(new RowsCount());
    addFunction(new MaxInRows());
    addFunction(new Count());

    addFunction(new Max());
    addFunction(new Min());

    addFunction(new ToDate());
    addFunction(new DateToStr());

    addFunction(new CharAt());
    addFunction(new StartsWith());
    addFunction(new EndWith());
    addFunction(new IndexOf());
    addFunction(new Substring());
    addFunction(new Replace());
    addFunction(new PadLeft());
    addFunction(new PadRight());
    addFunction(new IsBlank());
    addFunction(new IsEmpty());
    addFunction(new LTrim());
    addFunction(new RTrim());
    addFunction(new Trim());
    addFunction(new TrimToNull());
    addFunction(new RemoveSpaces());
    addFunction(new RemoveWhitespaces());
    addFunction(new NormalizeSpaces());
    addFunction(new ExtractDigits());
    addFunction(new Like());
    addFunction(new Format());
    addFunction(new ToString());

    addFunction(new Length());

    addFunction(new AddDays());
    addFunction(new AddHours());
    addFunction(new AddMinutes());
    addFunction(new AddMonths());
    addFunction(new AddSeconds());
    addFunction(new AddWeeks());
    addFunction(new AddYears());

    addFunction(new SysDate());
    addFunction(new SysTime());

    addFunction(new LastDayInMonth());
    addFunction(new LastDayInYear());
  }

  private void fillTypes() {
    addType(new TInt(this));
    addType(new TLong(this));
    addType(new TBool(this));
    addType(new TString(this));
    addType(new TDouble(this));
    addType(new TDate(this));
    addType(new TFloat(this));
    addType(new TList(this));
    addType(new TSet(this));
    addType(new TMap(this));
    addType(new TNull(this));
  }

}

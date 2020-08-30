package a2u.tn.utils.computer.calcobj;

import a2u.tn.utils.computer.calcobj.functions.datetime.AddDays;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddHours;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddMinutes;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddMonths;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddSeconds;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddWeeks;
import a2u.tn.utils.computer.calcobj.functions.datetime.AddYears;
import a2u.tn.utils.computer.calcobj.functions.Decode;
import a2u.tn.utils.computer.calcobj.functions.First;
import a2u.tn.utils.computer.calcobj.functions.datetime.LastDayInMonth;
import a2u.tn.utils.computer.calcobj.functions.datetime.LastDayInYear;
import a2u.tn.utils.computer.calcobj.functions.Nil;
import a2u.tn.utils.computer.calcobj.functions.datetime.SysDate;
import a2u.tn.utils.computer.calcobj.functions.datetime.SysTime;
import a2u.tn.utils.computer.calcobj.types.TNull;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calcobj.functions.Count;
import a2u.tn.utils.computer.calcobj.functions.DateToStr;
import a2u.tn.utils.computer.calcobj.functions.Distinct;
import a2u.tn.utils.computer.calcobj.functions.False;
import a2u.tn.utils.computer.calcobj.functions.Last;
import a2u.tn.utils.computer.calcobj.functions.Like;
import a2u.tn.utils.computer.calcobj.functions.Max;
import a2u.tn.utils.computer.calcobj.functions.MaxInRows;
import a2u.tn.utils.computer.calcobj.functions.Min;
import a2u.tn.utils.computer.calcobj.functions.Null;
import a2u.tn.utils.computer.calcobj.functions.Nvl;
import a2u.tn.utils.computer.calcobj.functions.RowNum;
import a2u.tn.utils.computer.calcobj.functions.RowsCount;
import a2u.tn.utils.computer.calcobj.functions.ToDate;
import a2u.tn.utils.computer.calcobj.functions.True;
import a2u.tn.utils.computer.calcobj.types.TBool;
import a2u.tn.utils.computer.calcobj.types.TDate;
import a2u.tn.utils.computer.calcobj.types.TDouble;
import a2u.tn.utils.computer.calcobj.types.TFloat;
import a2u.tn.utils.computer.calcobj.types.TInt;
import a2u.tn.utils.computer.calcobj.types.TList;
import a2u.tn.utils.computer.calcobj.types.TLong;
import a2u.tn.utils.computer.calcobj.types.TMap;
import a2u.tn.utils.computer.calcobj.types.TSet;
import a2u.tn.utils.computer.calcobj.types.TString;

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

    if (byCode.length() == 0) {
      valueListSet.addAll(fromObjList);
      return valueListSet;
    }

    for (Object fromObj : fromObjList) {
      try {
        if (fromObj == null) {
          valueListSet.add(null); //one null must be present in the collection
        }
        else {
          Object val;
          if (fromObj instanceof Map) {
            Map map = (Map) fromObj;
            val = map.get(byCode);
          }
          else if (fromObj instanceof CharSequence || fromObj instanceof Number || fromObj instanceof Boolean) {
            throw new IllegalArgumentException("Object '"+fromObj+"' with class "+fromObj.getClass()+" is incorrect for extract field "+byCode+".");
          }
          else {
            Class<?> cls = fromObj.getClass();
            Field fld = null;
            try {
              fld = cls.getDeclaredField(byCode);
            }
            catch (NoSuchFieldException e) {
              return null;
            }

            fld.setAccessible(true);
            val = fld.get(fromObj);
          }

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


  private void fillFunctions() {
    addFunction(new Null(this));
    addFunction(new Nil(this));
    addFunction(new Nvl(this));
    addFunction(new Decode(this));
    addFunction(new True(this));
    addFunction(new False(this));

    addFunction(new Distinct(this));
    addFunction(new First(this));
    addFunction(new Last(this));

    addFunction(new RowNum(this));
    addFunction(new RowsCount(this));
    addFunction(new MaxInRows(this));
    addFunction(new Count(this));
    addFunction(new Max(this));
    addFunction(new Min(this));

    addFunction(new ToDate(this));
    addFunction(new DateToStr(this));

    addFunction(new Like(this));

    addFunction(new AddDays(this));
    addFunction(new AddHours(this));
    addFunction(new AddMinutes(this));
    addFunction(new AddMonths(this));
    addFunction(new AddSeconds(this));
    addFunction(new AddWeeks(this));
    addFunction(new AddYears(this));

    addFunction(new SysDate(this));
    addFunction(new SysTime(this));

    addFunction(new LastDayInMonth(this));
    addFunction(new LastDayInYear(this));
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

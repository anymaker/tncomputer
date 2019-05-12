package a2u.tn.utils.computer.maplist;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.maplist.functions.Count;
import a2u.tn.utils.computer.maplist.functions.DateToStr;
import a2u.tn.utils.computer.maplist.functions.Distinct;
import a2u.tn.utils.computer.maplist.functions.False;
import a2u.tn.utils.computer.maplist.functions.First;
import a2u.tn.utils.computer.maplist.functions.Last;
import a2u.tn.utils.computer.maplist.functions.Like;
import a2u.tn.utils.computer.maplist.functions.Max;
import a2u.tn.utils.computer.maplist.functions.MaxInRows;
import a2u.tn.utils.computer.maplist.functions.Min;
import a2u.tn.utils.computer.maplist.functions.Null;
import a2u.tn.utils.computer.maplist.functions.Nvl;
import a2u.tn.utils.computer.maplist.functions.RowNum;
import a2u.tn.utils.computer.maplist.functions.RowsCount;
import a2u.tn.utils.computer.maplist.functions.True;
import a2u.tn.utils.computer.maplist.types.TBool;
import a2u.tn.utils.computer.maplist.types.TDate;
import a2u.tn.utils.computer.maplist.types.TDouble;
import a2u.tn.utils.computer.maplist.types.TFloat;
import a2u.tn.utils.computer.maplist.types.TInt;
import a2u.tn.utils.computer.maplist.types.TList;
import a2u.tn.utils.computer.maplist.types.TLong;
import a2u.tn.utils.computer.maplist.types.TMap;
import a2u.tn.utils.computer.maplist.types.TString;
import a2u.tn.utils.computer.maplist.functions.ToDate;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Calculator with extract values by code from source such as Map or List with Map
 * @deprecated you should use ObjCalcEngine insted this. @see a2u.tn.utils.computer.calcobj.ObjCalcEngine
 */
@Deprecated
public class MapListEngine extends Calculator {

  public MapListEngine() {
    fillFunctions();
    fillTypes();
  }

  private void fillFunctions() {
    addFunction(new Null(this));
    addFunction(new Nvl(this));
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
    addType(new TMap(this));
  }


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
      if (fromObj == null) {
        valueListSet.add(null); //one null must be present in the collection
      }
      else if (fromObj instanceof Map) {
        Map map = (Map) fromObj;
        Object mapval = map.get(byCode);
        if (mapval instanceof Collection) {
          valueListSet.addAll((Collection<Object>)mapval);
        }
        else {
          valueListSet.add(mapval);
        }
      }
      else {
        throw new IllegalArgumentException("Illegal value fromObj "+ fromObj.getClass().getName() +": '"+ String.valueOf(fromObj) +"'.");
      }

    }
    return valueListSet;
  }










}
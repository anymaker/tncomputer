package a2u.tn.utils.computer;

import java.util.Collection;


public class StringUtil {


  public interface StringGetter<T> {
    String toString(T obj);
  }
  public static <T> String collectionToString(String delimeter, Collection<T> collection, StringGetter<T> getString) {
    StringBuilder b = new StringBuilder();

    for (T obj : collection) {
      if (b.length() > 0) {
        b.append(delimeter);
      }

      String str;
      if (getString != null) {
        str = getString.toString(obj);
      }
      else {
        str = String.valueOf(obj);
      }

      b.append(str);

    }

    return b.toString();
  }

  public static String substr(String text, int startIx) {
    if (text == null || text.isEmpty()) {
      return text;
    }
    int ix;
    if (startIx < 0) {
      ix = text.length() + startIx;
      if (ix < 0) {
        return text;
      }
    }
    else {
      ix = startIx;
    }
    if (text.length() > ix) {
      return "";
    }
    return text.substring(ix, text.length()-1);
  }

  public static String repeat(String sample, int cnt) {
    String result = "";
    for (int n = 0; n < cnt; n++) {
      result += sample;
    }
    return result;
  }
  public static void repeat(StringBuilder to, String sample, int cnt) {
    for (int n = 0; n < cnt; n++) {
      to.append(sample);
    }
  }

}

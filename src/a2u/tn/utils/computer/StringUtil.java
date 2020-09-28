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

  public static String normalize(String string) {
    if (string == null) {
      return null;
    }

    StringBuilder b = new StringBuilder();
    int len = string.length();

    boolean inSpace = false;

    for (int ix = 0; ix < len; ix++) {
      char c = string.charAt(ix);

      if (c == ' ') {
        if (!inSpace) {
          b.append(c);
          inSpace = true;
        }
      }
      else {
        b.append(c);
        inSpace = false;
      }
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

  /**
   * Returns a string that is a substring of this string.
   * @param inpString Incoming string
   * @param start Start position, from zero. If the number is less than 0, then position will be counted from the end.
   * @param length The number of characters in the substring. If the number is less than 0, then the count will be from right to left.
   * @return Substring
   */
  public static String substring(String inpString, int start, int length) {
    if (inpString == null) {
      return null;
    }

    int stp = start >= 0 ? start : inpString.length() + start;
    int end = stp + length;

    if (stp > end) {
      int c = stp;
      stp = end;
      end = c;
    }

    if (stp < 0) {
      stp = 0;
    }
    else if (stp > inpString.length()) {
      return "";
    }

    if (end < 0) {
      return "";
    }
    if (end > inpString.length()) {
      end = inpString.length();
    }

    String str = inpString.substring(stp, end);
    return str;
  }

  public static String repeat(String sample, int cnt) {
    StringBuilder b = new StringBuilder();
    for (int n = 0; n < cnt; n++) {
      b.append(sample);
    }
    return b.toString();
  }
  public static void repeat(StringBuilder to, String sample, int cnt) {
    for (int n = 0; n < cnt; n++) {
      to.append(sample);
    }
  }

  /**
   * Returns a string resulting from replacing all occurrences of oldString in this string with newString.
   * @param string Incoming string.
   * @param oldString The old string.
   * @param newString The new string.
   * @return resulting string
   */
  public static String replace(String string, String oldString, String newString) {
    if (string == null) {
      return null;
    }
    if (oldString == null || oldString.length() == 0) {
      return string;
    }
    if (newString == null) {
      newString = "";
    }

    int len = string.length();
    int oldStringLen = oldString.length();
    int newStringLen = newString.length();
    StringBuilder b = new StringBuilder();
    for (int ix = 0; ix < len; ix++) {
      boolean isBegin = cmp(string, ix, oldString);
      if (isBegin) {
        if (newStringLen > 0) {
          b.append(newString);
        }
        ix += oldStringLen-1;
      }
      else {
        b.append(string.charAt(ix));
      }
    }

    return b.toString();
  }

  private static boolean cmp(String string, int start, String search) {
    int strIx = start;
    int schIx = 0;
    while (strIx < string.length() && schIx < search.length()) {
      if (string.charAt(strIx) != search.charAt(schIx)) {
        return false;
      }
      strIx++;
      schIx++;
    }
    return true;
  }

  public static String LTrim(String string) {
    if (string == null) {
      return null;
    }

    int ix = 0;
    int len = string.length();

    while ((ix < len) && (isWhiteSpace(string.charAt(ix)))) {
      ix++;
    }

    String res = string.substring(ix, len);
    return res;
  }

  public static String RTrim(String string) {
    if (string == null) {
      return null;
    }

    int ix = 0;
    int len = string.length();

    while ((ix < len) && (isWhiteSpace(string.charAt(len - 1)))) {
      len--;
    }

    String res = string.substring(ix, len);
    return res;
  }

  public static String trim(String string) {
    if (string == null) {
      return null;
    }

    int ix = 0;
    int len = string.length();

    while ((ix < len) && (isWhiteSpace(string.charAt(ix)))) {
      ix++;
    }
    while ((ix < len) && (isWhiteSpace(string.charAt(len - 1)))) {
      len--;
    }

    String res = string.substring(ix, len);
    return res;
  }

  /**
   * Tests if string contain only whitespace characters or null
   * @param string String for test
   * @return true, when string contain only whitespace characters or null
   */
  public static boolean isBlank(String string) {
    if (string == null) {
      return true;
    }

    int len = string.length();
    for (int ix = 0; ix < len; ix++) {
      char c = string.charAt(ix);
      if (! isWhiteSpace(c)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isWhiteSpace(char c) {
    switch (c) {
      case 0x0009:
      case 0x000A:
      case 0x000B:
      case 0x000C:
      case 0x000D:
      case 0x0020:
      case 0x0085:
      case 0x00A0:
      case 0x1680:
      case 0x2000:
      case 0x2001:
      case 0x2002:
      case 0x2003:
      case 0x2004:
      case 0x2005:
      case 0x2006:
      case 0x2007:
      case 0x2008:
      case 0x2009:
      case 0x200A:
      case 0x2028:
      case 0x2029:
      case 0x202F:
      case 0x205F:
      case 0x3000:
        return true;
      default: return false;
    }
  }
}

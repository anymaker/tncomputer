package a2u.tn.utils.computer;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

  @Test
  public void replace() {
    String str;
    str = StringUtil.replace("abcdefbcgh", "bc", "BC");
    assertEquals("aBCdefBCgh", str);

    str = StringUtil.replace("aaaaa", "a", "");
    assertEquals("", str);

    str = StringUtil.replace("aaaaa", "a", "b");
    assertEquals("bbbbb", str);

    str = StringUtil.replace("aaaaa", "c", "b");
    assertEquals("aaaaa", str);

    str = StringUtil.replace("", "c", "b");
    assertEquals("", str);

    str = StringUtil.replace(null, "c", "b");
    assertEquals(null, str);

    str = StringUtil.replace("aaaaa", "", "b");
    assertEquals("aaaaa", str);
  }

  @Test
  public void substring() {
    String str;
    /**/
    str = StringUtil.substring("abcdefgh", 0, 2);
    assertEquals("ab", str);

    str = StringUtil.substring("abcdefgh", 2, 5);
    assertEquals("cdefg", str);

    str = StringUtil.substring("abcdefgh", 2, 50);
    assertEquals("cdefgh", str);

    str = StringUtil.substring("abcdefgh", 20, 50);
    assertEquals("", str);

    str = StringUtil.substring("abcdefgh", -5, 2);
    assertEquals("de", str);

    str = StringUtil.substring("abcdefgh", -5, -2);
    assertEquals("bc", str);

    str = StringUtil.substring("abcdefgh", -50, 2);
    assertEquals("", str);

    str = StringUtil.substring("abcdefgh", -5, 20);
    assertEquals("defgh", str);

    str = StringUtil.substring("abcdefgh", 5, -20);
    assertEquals("abcde", str);
  }

  @Test
  public void lTrim() {
    String str = StringUtil.LTrim("   aaa");
    assertEquals("aaa", str);
  }

}
package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TSetTest {

  TSet tSet;

  @Before
  public void init() {
    tSet = new TSet(ObjCalcEngine.getInstance());
  }

  @Test
  public void plus() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    Set<?> result = tSet.plus(set1, set2);
    assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());

    set1.add(4);
    result = tSet.plus(set1, set2);
    assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());

    result = tSet.plus(null, set2);
    assertEquals("[4, 5, 6]", result.toString());

    result = tSet.plus(set1, null);
    assertEquals("[1, 2, 3, 4]", result.toString());
  }

  @Test
  public void minus() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    Set<?> result;
    result = tSet.minus(set1, set2);
    assertEquals("[1, 2, 3]", result.toString());

    set1.add(4);
    result = tSet.minus(set1, set2);
    assertEquals("[1, 2, 3]", result.toString());

    result = tSet.minus(null, set2);
    assertEquals("[]", result.toString());

    result = tSet.minus(set1, null);
    assertEquals("[1, 2, 3, 4]", result.toString());

  }

  @Test
  public void equal() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    assertTrue(tSet.equal(set1, set1));

    Set<Integer> set3 = new LinkedHashSet<>(set1);
    assertTrue(tSet.equal(set1, set3));

    assertFalse(tSet.equal(set1, set2));
    assertFalse(tSet.equal(null, set2));
    assertFalse(tSet.equal(set1, null));

  }

  @Test
  public void notequal() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    assertFalse(tSet.notequal(set1, set1));

    Set<Integer> set3 = new LinkedHashSet<>(set1);
    assertFalse(tSet.notequal(set1, set3));

    assertTrue(tSet.notequal(set1, set2));
    assertTrue(tSet.notequal(null, set2));
    assertTrue(tSet.notequal(set1, null));
  }

  @Test
  public void and() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    Set<?> result;
    result = tSet.and(set1, set2);
    assertEquals("[]", result.toString());

    set1.add(4);
    result = tSet.and(set1, set2);
    assertEquals("[4]", result.toString());

    set1.add(5);
    result = tSet.and(set1, set2);
    assertEquals("[4, 5]", result.toString());

    result = tSet.and(null, set2);
    assertEquals("[]", result.toString());

    result = tSet.and(set1, null);
    assertEquals("[]", result.toString());
  }

  @Test
  public void or() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    Set<?> result = tSet.or(set1, set2);
    assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());

    set1.add(4);
    result = tSet.or(set1, set2);
    assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());

    result = tSet.or(null, set2);
    assertEquals("[4, 5, 6]", result.toString());

    result = tSet.or(set1, null);
    assertEquals("[1, 2, 3, 4]", result.toString());
  }

  @Test
  public void xor() {
    Set<Integer> set1 = new LinkedHashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
    }};
    Set<Integer> set2 = new LinkedHashSet<Integer>(){{
      add(4);
      add(5);
      add(6);
    }};

    Set<?> result = tSet.xor(set1, set2);
    assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());

    set1.add(4);
    result = tSet.xor(set1, set2);
    assertEquals("[1, 2, 3, 5, 6]", result.toString());

    set1.add(5);
    result = tSet.xor(set1, set2);
    assertEquals("[1, 2, 3, 6]", result.toString());

    result = tSet.xor(null, set2);
    assertEquals("[4, 5, 6]", result.toString());

    result = tSet.xor(set1, null);
    assertEquals("[1, 2, 3, 4, 5]", result.toString());
  }
}
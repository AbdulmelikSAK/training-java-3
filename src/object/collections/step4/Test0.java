package object.collections.step4;

public class Test0 {

  public static void ensure(boolean cond, String msg) {
    if (!cond)
      throw new RuntimeException(msg);
  }

  public static void ensure(boolean cond) {
    if (!cond)
      throw new RuntimeException("Failed assert.");
  }

  static void echoElapsed(long elapsed) {
    if (elapsed > 1000000L) {
      double f = (double) elapsed / 1000000.0;
      System.out.printf("  elapsed: %.2fms\n", f);
    } else if (elapsed > 1000L) {
      double f = (double) elapsed / 1000.0;
      System.out.printf("  elapsed: %.2fus\n", f);
    } else
      System.out.println("  elapsed: " + elapsed + "ns");
  }

  public static void main(String args[]) {
    try {
      long start = System.nanoTime();
      test0();
      long end = System.nanoTime();

      System.out.println("All tests: PASSED");

      echoElapsed(end - start);

    } catch (Throwable th) {
      th.printStackTrace(System.err);
      System.err.println("Tests: FAILED");
    }
  }

  public static void test0() {
    long start = System.nanoTime();

    String keys[] = new String[] { "one", "two", "three", "four" };
    Integer values[] = new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4) };
    Integer uvalues[] = new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4) };

    AssociativeCollection ac;
    ac = new AssociativeCollection();

    // let's put new pairs in, we should always get null
    // since all added pairs are unknown when added.
    for (int i = 0; i < keys.length; i++) {
      Object value = ac.put(keys[i], values[i]);
      ensure(null == value);
    }
    // let's make sure we find our pairs again.
    for (int i = 0; i < keys.length; i++) {
      Object value = ac.get(keys[i]);
      ensure(values[i] == value);
    }

    // let's update our pairs, with new values,
    // making sure that we get back the old values.
    for (int i = 0; i < keys.length; i++) {
      Object value = ac.put(keys[i], uvalues[i]);
      ensure(values[i] == value);
    }

    // let's make sure we still find our pairs, 
    // with the updated values.
    for (int i = 0; i < keys.length; i++) {
      Object value = ac.get(keys[i]);
      ensure(uvalues[i] == value);
    }

    // let's remove pairs and make sure we do not find them
    for (int i = 0; i < keys.length; i++) {
      Object value = ac.remove(keys[i]);
      ensure(uvalues[i] == value);
      value = ac.get(keys[i]);
      ensure(null == value);
    }

    long end = System.nanoTime();
    echoElapsed(end - start);
  }

}

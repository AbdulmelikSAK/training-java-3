package object.collections.step4;

public class Test1 {

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

      AssociativeCollection ac = new AssociativeCollection(true);
      Object key = new String("One");
      Object value = "toto";
      ac.put(key, value);
      Object key2 = new String("One");
      Object value2 = ac.get(key2);
      ensure(value == value2);

      Test0.test0();
      test1();

      System.out.println("All tests: PASSED");

      long end = System.nanoTime();
      echoElapsed(end - start);

    } catch (Throwable th) {
      th.printStackTrace(System.err);
      System.err.println("Tests: FAILED");
    }
  }

  public static void test1() {
    long start = System.nanoTime();
    String keys[] = new String[] { new String("one"), new String("two"), new String("three"), new String("four") };
    Integer values[] = new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4) };

    AssociativeCollection ac;
    ac = new AssociativeCollection(true);

    for (int i = 0; i < keys.length; i++)
      ensure(null == ac.put(keys[i], values[i]));

    ensure(values[0] == ac.get("one"));
    ensure(values[1] == ac.get("two"));
    ensure(values[2] == ac.get("three"));
    ensure(values[3] == ac.get("four"));

    long end = System.nanoTime();
    echoElapsed(end - start);
  }

}

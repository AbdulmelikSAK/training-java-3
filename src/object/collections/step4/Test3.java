package object.collections.step4;

public class Test3 {

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
      Test2.test1();
      Test2.test2();
      test1();
      System.out.println("All tests: PASSED");
    } catch (Throwable th) {
      th.printStackTrace(System.err);
      System.err.println("Tests: FAILED");
    }
  }

  static void test1() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(p1);
    Point p3 = new Point(p1);
    double dx = 2 * Point.epsilon / 3;
    p2.translate(dx, 0);
    p3.translate(2 * dx, 0);

    // reflexive
    ensure(p1.equals(p1));
    ensure(p2.equals(p2));
    ensure(p3.equals(p3));

    // symmetric
    if (p1.equals(p2))
      ensure(p2.equals(p1));
    if (p3.equals(p2))
      ensure(p2.equals(p3));

    // transitive
    if (p1.equals(p2)) {
      if (p2.equals(p3))
        ensure(p1.equals(p3));
    }
  }

}

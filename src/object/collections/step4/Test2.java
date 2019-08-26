package object.collections.step4;

public class Test2 {

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
      test1();
      test2();
      System.out.println("All tests: PASSED");
    } catch (Throwable th) {
      th.printStackTrace(System.err);
      System.err.println("Tests: FAILED");
    }
  }

  static void test1() {
    Point p1 = new Point(2,3);
    Point p2 = new Point(2,3);
    Point p3 = new Point(3,2);
    
    ensure(!p1.equals(p3));
    ensure(!p2.equals(p3));

    // reflexive
    ensure(p1.equals(p1));
    // symmetric
    ensure(p1.equals(p2));
    ensure(p2.equals(p1));
  }

  static void test2() {
    Point p1 = new Point(2,3);
    Point p2 = new Point(3,4);
    Point p3 = new Point(3,2);
    Point p4 = new Point(2,3);
    Line l1 = new Line(p1,p2);
    Line l2 = new Line(p2,p1);
    Line l3 = new Line(p3,p4);
    
    ensure(!l1.equals(l3));
    ensure(!l2.equals(l3));

    // reflexive
    ensure(l1.equals(l1));
    ensure(l2.equals(l2));
    ensure(l3.equals(l3));
    // symmetric
    ensure(l1.equals(l2));
    ensure(l2.equals(l1));
    
  }

}

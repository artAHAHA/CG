package math.vectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2fTest {

    @Test
    void sum() {
        Vector2f v1 = new Vector2f(5, 7);
        Vector2f v2 = new Vector2f(2, 3);

        Vector2f sum = v1.add(v2);
        Assertions.assertEquals(7.0, sum.getX(), 1e-7);
        Assertions.assertEquals(10.0, sum.getY(), 1e-7);
    }
    @Test
    void subtract() {
        Vector2f v1 = new Vector2f(5, 7);
        Vector2f v2 = new Vector2f(2, 3);

        Vector2f difference = v1.subtract(v2);
        Assertions.assertEquals(3.0, difference.getX(), 1e-7);
        Assertions.assertEquals(4.0, difference.getY(), 1e-7);
    }

    @Test
    void multiplyByScalar() {
        Vector2f v = new Vector2f(1, 2);
        v.multiplyingVectorByScalar(3);

        Assertions.assertEquals(3.0, v.getX(), 1e-7);
        Assertions.assertEquals(6.0, v.getY(), 1e-7);
    }

    @Test
    void divideByScalar() {
        Vector2f v = new Vector2f(6, 3);
        v.dividingVectorByScalar(3);

        Assertions.assertEquals(2.0, v.getX(), 1e-7);
        Assertions.assertEquals(1.0, v.getY(), 1e-7);
    }

    @Test
    void divideByZeroThrowsException() {
        Vector2f v = new Vector2f(1, 2);

        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.dividingVectorByScalar(0);
        });
    }

    @Test
    void getLength() {
        Vector2f v = new Vector2f(3, 4);
        double length = v.getLength();

        Assertions.assertEquals(5.0, length, 1e-7);
    }

    @Test
    void normalize() {
        Vector2f v = new Vector2f(3, 4);
        v.normalize();

        Assertions.assertEquals(0.6, v.getX(), 1e-7);
        Assertions.assertEquals(0.8, v.getY(), 1e-7);
    }

    @Test
    void normalizeZeroVectorThrowsException() {
        Vector2f v = new Vector2f(0, 0);

        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.normalize();
        });
    }

    @Test
    void scalarMultiplication() {
        Vector2f v1 = new Vector2f(2, 3);
        Vector2f v2 = new Vector2f(4, 5);

        double result = v1.scalarMultiplication(v2);
        Assertions.assertEquals(23.0, result, 1e-7); // 2*4 + 3*5 = 8 + 15 = 23
    }

    @Test
    void testToString() {
        Vector2f v = new Vector2f(1.5, 2.5);
        Assertions.assertEquals("Vector2f(1.5, 2.5)", v.toString());
    }
}

package ua.kpi.srom.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.kpi.srom.models.LongArithmeticModel;

@DisplayName("LongArithmeticService Tests")
class LongArithmeticServiceTest {
  private LongArithmeticService service;

  @BeforeEach
  void setUp() {
    service = new LongArithmeticService();
  }

  @Test
  @DisplayName("Should add two positive numbers")
  void testAdd() {
    BigInteger a = BigInteger.valueOf(10);
    BigInteger b = BigInteger.valueOf(20);
    assertEquals(BigInteger.valueOf(30), service.add(a, b));
  }

  @Test
  @DisplayName("Should subtract two numbers")
  void testSubtract() {
    BigInteger a = BigInteger.valueOf(50);
    BigInteger b = BigInteger.valueOf(20);
    assertEquals(BigInteger.valueOf(30), service.subtract(a, b));
  }

  @Test
  @DisplayName("Should multiply two numbers")
  void testMultiply() {
    BigInteger a = BigInteger.valueOf(5);
    BigInteger b = BigInteger.valueOf(6);
    assertEquals(BigInteger.valueOf(30), service.multiply(a, b));
  }

  @Test
  @DisplayName("Should square a number")
  void testSquare() {
    BigInteger a = BigInteger.valueOf(7);
    assertEquals(BigInteger.valueOf(49), service.square(a));
  }

  @Test
  @DisplayName("Should divide two numbers")
  void testDivide() {
    BigInteger a = BigInteger.valueOf(100);
    BigInteger b = BigInteger.valueOf(10);
    assertEquals(BigInteger.valueOf(10), service.divide(a, b));
  }

  @Test
  @DisplayName("Should return null when dividing by zero")
  void testDivideByZero() {
    BigInteger a = BigInteger.valueOf(100);
    BigInteger b = BigInteger.ZERO;
    assertNull(service.divide(a, b));
  }

  @Test
  @DisplayName("Should calculate modulo")
  void testModule() {
    BigInteger a = BigInteger.valueOf(17);
    BigInteger b = BigInteger.valueOf(5);
    assertEquals(BigInteger.valueOf(2), service.module(a, b));
  }

  @Test
  @DisplayName("Should return null for modulo with non-positive divisor")
  void testModuleInvalid() {
    BigInteger a = BigInteger.valueOf(17);
    BigInteger b = BigInteger.valueOf(-5);
    assertNull(service.module(a, b));
  }

  @Test
  @DisplayName("Should calculate GCD")
  void testGcd() {
    BigInteger a = BigInteger.valueOf(48);
    BigInteger b = BigInteger.valueOf(18);
    assertEquals(BigInteger.valueOf(6), service.gcd(a, b));
  }

  @Test
  @DisplayName("Should calculate LCM")
  void testLcm() {
    BigInteger a = BigInteger.valueOf(12);
    BigInteger b = BigInteger.valueOf(18);
    assertEquals(BigInteger.valueOf(36), service.lcm(a, b));
  }

  @Test
  @DisplayName("Should perform modular addition")
  void testModuleAdd() {
    BigInteger a = BigInteger.valueOf(10);
    BigInteger b = BigInteger.valueOf(15);
    BigInteger m = BigInteger.valueOf(7);
    assertEquals(BigInteger.valueOf(4), service.moduleAdd(a, b, m));
  }

  @Test
  @DisplayName("Should return null for modular addition with null modulus")
  void testModuleAddNullModulus() {
    BigInteger a = BigInteger.valueOf(10);
    BigInteger b = BigInteger.valueOf(15);
    assertNull(service.moduleAdd(a, b, null));
  }

  @Test
  @DisplayName("Should perform modular subtraction")
  void testModuleSubtract() {
    BigInteger a = BigInteger.valueOf(10);
    BigInteger b = BigInteger.valueOf(3);
    BigInteger m = BigInteger.valueOf(5);
    assertEquals(BigInteger.valueOf(2), service.moduleSubtract(a, b, m));
  }

  @Test
  @DisplayName("Should perform modular multiplication")
  void testModuleMult() {
    BigInteger a = BigInteger.valueOf(7);
    BigInteger b = BigInteger.valueOf(8);
    BigInteger m = BigInteger.valueOf(10);
    assertEquals(BigInteger.valueOf(6), service.moduleMult(a, b, m));
  }

  @Test
  @DisplayName("Should perform modular exponentiation")
  void testModulePow() {
    BigInteger a = BigInteger.valueOf(2);
    BigInteger b = BigInteger.valueOf(10);
    BigInteger m = BigInteger.valueOf(100);
    assertEquals(BigInteger.valueOf(24), service.modulePow(a, b, m));
  }

  @Test
  @DisplayName("Should calculate all operations for a model")
  void testCalculateAll() {
    BigInteger a = BigInteger.valueOf(10);
    BigInteger b = BigInteger.valueOf(3);
    BigInteger m = BigInteger.valueOf(7);

    LongArithmeticModel model =
        LongArithmeticModel.builder().firstNumber(a).secondNumber(b).module(m).build();

    LongArithmeticModel result = service.calculateAll(model);

    assertNotNull(result);
    assertEquals(BigInteger.valueOf(13), result.getSum());
    assertEquals(BigInteger.valueOf(7), result.getSubtraction());
    assertEquals(BigInteger.valueOf(30), result.getMultiplication());
    assertEquals(BigInteger.valueOf(3), result.getDivision());
    assertEquals(BigInteger.valueOf(100), result.getSquare());
    assertEquals(BigInteger.valueOf(1), result.getModuleRes());
    assertEquals(BigInteger.valueOf(1), result.getGcd());
    assertEquals(BigInteger.valueOf(30), result.getLcm());
    assertEquals(BigInteger.valueOf(6), result.getModuleAdd());
    assertEquals(BigInteger.valueOf(0), result.getModuleSubtraction());
    assertEquals(BigInteger.valueOf(2), result.getModuleMult());
    assertEquals(BigInteger.valueOf(6), result.getModulePow());
    assertEquals(BigInteger.valueOf(2), result.getModuleSquare());
  }

  @Test
  @DisplayName("Should handle large numbers")
  void testWithLargeNumbers() {
    BigInteger a = new BigInteger("123456789012345678901234567890");
    BigInteger b = new BigInteger("987654321098765432109876543210");

    BigInteger sum = service.add(a, b);
    assertEquals(new BigInteger("1111111110111111111011111111100"), sum);
  }
}

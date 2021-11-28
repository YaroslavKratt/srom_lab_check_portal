package ua.kpi.ipt.srom.srom_check.services;

import static java.util.Objects.isNull;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;

@Service
@Slf4j
public class LongArithmeticService {

    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    public BigInteger square(BigInteger a) {
        return a.pow(2);
    }

    public BigInteger divide(BigInteger a, BigInteger b) {
        try {
            return a.divide(b);
        } catch (ArithmeticException e) {
            log.warn("a /b: " + e.getLocalizedMessage());
            return null;
        }
    }

    public BigInteger module(BigInteger a, BigInteger b) {
        try {
            return a.mod(b);
        } catch (ArithmeticException e) {
            log.warn("a mod b: " + e.getLocalizedMessage());
            return null;
        }
    }

    public BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public BigInteger lcm(BigInteger a, BigInteger b) {

        try {
            BigInteger mul = a.multiply(b);
            BigInteger gcd = a.gcd(b);

            return mul.divide(gcd);
        } catch (ArithmeticException e) {
            log.warn("lcm: " + e.getLocalizedMessage());
            return BigInteger.ZERO;
        }
    }

    public BigInteger moduleAdd(BigInteger a, BigInteger b, BigInteger m) {
        try {
            if (isNull(m))
                return null;
            return a.add(b).mod(m);
        } catch (ArithmeticException e) {
            log.warn("a + b  mod m: " + e.getLocalizedMessage());
            return null;
        }
    }

    public BigInteger moduleSubtract(BigInteger a, BigInteger b, BigInteger m) {
        try {
            if (isNull(m))
                return null;

            return a.subtract(b).mod(m);
        } catch (ArithmeticException e) {
            log.warn("a - b  mod m: " + e.getLocalizedMessage());
            return null;
        }

    }

    public BigInteger moduleMult(BigInteger a, BigInteger b, BigInteger m) {
        try {
            if (isNull(m))
                return null;

            return a.multiply(b).mod(m);
        } catch (ArithmeticException e) {
            log.warn("a * b  mod m: " + e.getLocalizedMessage());
            return null;
        }
    }

    public BigInteger modulePow(BigInteger a, BigInteger b, BigInteger m) {
        try {
            if (isNull(m))
                return null;

            return a.modPow(b, m);
        } catch (ArithmeticException e) {
            log.warn("a + b  mod m: " + e.getLocalizedMessage());
            return null;
        }
    }

    public LongArithmeticModel calculateAll(LongArithmeticModel model) {
        BigInteger a = model.getFirstNumber();
        BigInteger b = model.getSecondNumber();
        BigInteger m = model.getModule();

        return LongArithmeticModel.builder()
                .firstNumber(a)
                .secondNumber(b)
                .module(m)
                .moduleRes(module(a, b))
                .numberSystem(model.getNumberSystem())
                .sum(add(a, b))
                .multiplication(multiply(a, b))
                .division(divide(a, b))
                .square(square(a))
                .subtraction(subtract(a, b))
                .gcd(gcd(a, b))
                .lcm(lcm(a, b))
                .moduleAdd(moduleAdd(a, b, m))
                .moduleMult(moduleMult(a, b, m))
                .modulePow(modulePow(a, b, m))
                .moduleSquare(modulePow(a, BigInteger.valueOf(2), m))
                .moduleSubtraction(moduleSubtract(a, b, m))
                .build();
    }
}


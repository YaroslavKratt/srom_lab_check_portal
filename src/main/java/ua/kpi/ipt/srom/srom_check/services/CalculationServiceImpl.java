package ua.kpi.ipt.srom.srom_check.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger square(BigInteger a) {
        return a.pow(2);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger modulo(BigInteger a, BigInteger b) {
        return a.mod(b);
    }

    @Override
    public Map<String, BigInteger> calculateAll() {
        Map<String, BigInteger> map;
        return null;
    }

    @Override
    public BigInteger pow(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO) < 0)
            throw new IllegalArgumentException();
        BigInteger z = a;
        BigInteger result = BigInteger.ONE;
        byte[] bytes = b.toByteArray();
        for (int i = bytes.length - 1; i >= 0; i--) {
            byte bits = bytes[i];
            for (int j = 0; j < 8; j++) {
                if ((bits & 1) != 0)
                    result = result.multiply(z);
                if ((bits >>= 1) == 0 && i == 0)
                    return result;
                z = z.multiply(z);
            }
        }
        return result;
    }
}


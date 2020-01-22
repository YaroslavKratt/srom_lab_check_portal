package ua.kpi.ipt.srom.srom_check.models;

import cc.redberry.rings.poly.FiniteField;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static cc.redberry.rings.Rings.GF;

public class FiniteFieldsVariants {
    public static final Map<String, String> VARIANT_TO_POLYNOM = new LinkedHashMap<>();

    static {
        VARIANT_TO_POLYNOM.put("1", "x^163+x^7+x^6+x^3+1");
        VARIANT_TO_POLYNOM.put("2", "x^173+x^10+x^2+x+1");
        VARIANT_TO_POLYNOM.put("3", "x^179+x^4+x^2+x+1");
        VARIANT_TO_POLYNOM.put("4", "x^191+x^9+1");
        VARIANT_TO_POLYNOM.put("5", "x^233+x^9+x^4+x+1");
        VARIANT_TO_POLYNOM.put("6", "x^239+x^15+x^2+x+1");
        VARIANT_TO_POLYNOM.put("7", "x^251+x^14+x^4+x+1");
        VARIANT_TO_POLYNOM.put("8", "x^281+x^9+x^4+x+1");
        VARIANT_TO_POLYNOM.put("9", "x^283+x^26+x^9+x+1");
        VARIANT_TO_POLYNOM.put("10", "x^293+x^11+x^6+x+1");
        VARIANT_TO_POLYNOM.put("11", "x^359+x^18+x^4+x^2+1");
        VARIANT_TO_POLYNOM.put("12", "x^409+x^15+x^6+x+1");
        VARIANT_TO_POLYNOM.put("13", "x^419+x^21+x^14+x+1");
        VARIANT_TO_POLYNOM.put("14", "x^431+x^5+x^3+x+1");
        VARIANT_TO_POLYNOM.put("15", "x^443+x^28+x^3+x+1");
        VARIANT_TO_POLYNOM.put("16", "x^491+x^17+x^6+x^2+1");
        VARIANT_TO_POLYNOM.put("17", "x^509+x^23+x^3+x^2+1");
        VARIANT_TO_POLYNOM.put("18", "x^571+x^10+x^5+x^2+1");
    }


    public  static FiniteField<UnivariatePolynomialZp64> getFieldBy(String variant) {
        return GF(UnivariatePolynomialZp64.parse(VARIANT_TO_POLYNOM.get(variant), 2));
    }



}

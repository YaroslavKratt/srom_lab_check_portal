package ua.kpi.ipt.srom.srom_check.models;

import cc.redberry.rings.poly.FiniteField;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZ64;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static cc.redberry.rings.Rings.GF;

public class FiniteFieldsVariants {
    public static final Map<String, FiniteField> VARIANTS_FIELDS = new HashMap<>();

    static {
        VARIANTS_FIELDS.put("1", GF(UnivariatePolynomialZp64.parse("x^163+x^7+x^6+x^3+1", 2)));
        VARIANTS_FIELDS.put("2", GF(UnivariatePolynomialZp64.parse("x^173+x^10+x^2+x+1", 2)));
        VARIANTS_FIELDS.put("3", GF(UnivariatePolynomialZp64.parse("x^179+x^4+x^2+x+1", 2)));
        VARIANTS_FIELDS.put("4", GF(UnivariatePolynomialZp64.parse("x^191+x^9+1", 2)));
        VARIANTS_FIELDS.put("5", GF(UnivariatePolynomialZp64.parse("x^233+x^9+x^4+x+1", 2)));
        VARIANTS_FIELDS.put("6", GF(UnivariatePolynomialZp64.parse("x^239+x^15+x^2+x+1", 2)));
        VARIANTS_FIELDS.put("7", GF(UnivariatePolynomialZp64.parse("x^251+x^14+x^4+x+1", 2)));
        VARIANTS_FIELDS.put("8", GF(UnivariatePolynomialZp64.parse("x^281+x^9+x^4+x+1", 2)));
        VARIANTS_FIELDS.put("9", GF(UnivariatePolynomialZp64.parse("x^283+x^26+x^9+x+1", 2)));
        VARIANTS_FIELDS.put("10", GF(UnivariatePolynomialZp64.parse("x^293+x^11+x^6+x+1", 2)));
        VARIANTS_FIELDS.put("11", GF(UnivariatePolynomialZp64.parse("x^359+x^18+x^4+x^2+1", 2)));
        VARIANTS_FIELDS.put("12", GF(UnivariatePolynomialZp64.parse("x^409+x^15+x^6+x+1", 2)));
        VARIANTS_FIELDS.put("13", GF(UnivariatePolynomialZp64.parse("x^419+x^21+x^14+x+1", 2)));
        VARIANTS_FIELDS.put("14", GF(UnivariatePolynomialZp64.parse("x^431+x^5+x^3+x+1", 2)));
        VARIANTS_FIELDS.put("15", GF(UnivariatePolynomialZp64.parse("x^443+x^28+x^3+x+1", 2)));
        VARIANTS_FIELDS.put("16", GF(UnivariatePolynomialZp64.parse("x^491+x^17+x^6+x^2+1", 2)));
        VARIANTS_FIELDS.put("17", GF(UnivariatePolynomialZp64.parse("x^509+x^23+x^3+x^2+1", 2)));
        VARIANTS_FIELDS.put("18", GF(UnivariatePolynomialZp64.parse("x^571+x^10+x^5+x^2+1", 2)));
    }
}

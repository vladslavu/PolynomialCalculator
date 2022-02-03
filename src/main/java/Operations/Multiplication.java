package Operations;

import DataType.Monomial;
import DataType.Polynomial;

import java.awt.*;
import java.util.ArrayList;

public class Multiplication extends Operation{


    public Multiplication (String poly1, String poly2) throws Exception {
        this.getPoly1().setActualPoly(poly1);
        this.getPoly2().setActualPoly(poly2);
        performMultiplication();
    }

    private void performMultiplication() {
        ArrayList<Monomial> poly1 = this.getPoly1().getArray();
        ArrayList <Monomial> poly2 = this.getPoly2().getArray();
        Monomial zero = new Monomial();
        int maxDeg = this.getPoly1().getPolynomialDegree() + this.getPoly2().getPolynomialDegree();
        ArrayList <Monomial> res = new ArrayList<>(maxDeg + 1);

        for (int i = 0; i <= maxDeg; i++) {
            res.add(zero);
        }

        for (Monomial current1 : poly1) {
            for (Monomial current2 : poly2) {
                if (res.get(maxDeg - current1.getDegree() - current2.getDegree()) == zero)
                    res.set(maxDeg - current1.getDegree() - current2.getDegree(), current1.multiply(current2));
                else
                    res.get(maxDeg - (current1.getDegree() + current2.getDegree())).setCoef(res.get(maxDeg - current1.getDegree() - current2.getDegree()).getCoef().doubleValue() + current1.getCoef().doubleValue() * current2.getCoef().doubleValue());
            }
        }

        this.getResult().setActualPoly(res);
        this.getResult().escapeZeros();
    }
}

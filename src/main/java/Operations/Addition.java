package Operations;

import DataType.Monomial;
import DataType.Polynomial;

import java.util.ArrayList;

public class Addition extends Operation{

    public Addition (String poly1, String poly2) throws Exception {
            this.getPoly1().setActualPoly(poly1);
            this.getPoly2().setActualPoly(poly2);
            performAddition();
    }


    private void performAddition() {
        ArrayList <Monomial> poly1 = this.getPoly1().getArray();
        ArrayList <Monomial> poly2 = this.getPoly2().getArray();
        int maxDeg = Math.max(this.getPoly1().getPolynomialDegree(), this.getPoly2().getPolynomialDegree());

        Monomial zero = new Monomial();
        ArrayList <Monomial> res = new ArrayList<>(maxDeg + 1);
        for (int i = 0; i <= maxDeg; i++) {
            res.add(zero);
        }

        for(Monomial current1 : poly1) {
            if (res.get(maxDeg - current1.getDegree()) == zero)
                res.set(maxDeg - current1.getDegree(), current1);
            else
                res.get(maxDeg - current1.getDegree()).addCoef(current1.getCoef());
        }
        for(Monomial current2 : poly2) {
            if(res.get(maxDeg - current2.getDegree()) == zero)
                res.set(maxDeg - current2.getDegree(), current2);
            else
                res.get(maxDeg - current2.getDegree()).addCoef(current2.getCoef());
        }
        this.getResult().setActualPoly(res);
    }
}

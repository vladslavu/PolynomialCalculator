package Operations;

import DataType.Monomial;

import java.util.ArrayList;

public class Derivation extends OperationOnOne{

    public Derivation (String poly) throws Exception {
        this.getPoly().setActualPoly(poly);
        performDerivation();
    }

    private void performDerivation() {
        ArrayList<Monomial> poly = this.getPoly().getArray();
        ArrayList<Monomial> res = new ArrayList<>(this.getPoly().getPolynomialDegree());

        for (int i = 0; i <= this.getPoly().getPolynomialDegree(); i++)
            res.add(ZERO);

        for (Monomial current : poly)
            if(current.getCoef().doubleValue() != 0.0 && current.getDegree() != 0) {
                Monomial nM = new Monomial(current);
                res.set(this.getPoly().getPolynomialDegree() - current.getDegree(), nM.derivateMonomial());
            }

        this.getResult().setActualPoly(res);
    }
}

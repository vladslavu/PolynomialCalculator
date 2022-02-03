package Operations;

import DataType.Monomial;

import java.util.ArrayList;

public class Integration extends OperationOnOne{

    public Integration (String poly) throws Exception {
        this.getPoly().setActualPoly(poly);
        performIntegration();
    }

    private void performIntegration() {
        ArrayList<Monomial> poly = this.getPoly().getArray();
        ArrayList<Monomial> res = new ArrayList<>(this.getPoly().getPolynomialDegree() + 2);

        for (int i = 0; i <= this.getPoly().getPolynomialDegree() + 1; i++)
            res.add(ZERO);

        for (Monomial current : poly)
            if(current.getCoef().doubleValue() != 0.0 || current.getDegree() != 0)
                res.set(this.getPoly().getPolynomialDegree() - current.getDegree(), current.integrateMonomial());

        this.getResult().setActualPoly(res);
    }
}

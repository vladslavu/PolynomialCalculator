package Operations;

import DataType.Monomial;
import DataType.Polynomial;

import java.awt.*;
import java.util.ArrayList;

public class Division extends Operation{

    private Polynomial rest = new Polynomial();

    public Division (String poly1, String poly2) throws Exception {
        this.getPoly1().setActualPoly(poly1);
        this.getPoly2().setActualPoly(poly2);
        performDivision();
    }

    private void performDivision() throws Exception {
        //cannot perform the division in poly2 is 0 or if deg(poly1) < deg(poly2)
        if (this.getPoly1().getPolynomialDegree() < this.getPoly2().getPolynomialDegree() || this.getPoly2().getPolynomialDegree() == -1)
            return;

        int maxDeg = this.getPoly1().getPolynomialDegree() - this.getPoly2().getPolynomialDegree();
        ArrayList <Monomial> res = new ArrayList<>(maxDeg + 1);
        Polynomial temp1 = this.getPoly1();
        Polynomial temp2 = this.getPoly2();
        Polynomial d;

        int i = 0;
        while (temp1.getPolynomialDegree() >= temp2.getPolynomialDegree()) {
            d = shiftLeft(temp2, temp1.getPolynomialDegree() - temp2.getPolynomialDegree()); //meaning that i want to multiply that poly with a power of x s.t. deg(temp1) = deg(temp2)
            Monomial nM = new Monomial();
            nM.setDegree(i);
            nM.setCoef(temp1.getArray().get(0).getCoef().doubleValue() / temp2.getArray().get(0).getCoef().doubleValue());
            res.add(nM);

            for (Monomial current : d.getArray())
                current.multiplyCoef(nM.getCoef()); //multiply with the division factors

            nM.setDegree(temp1.getPolynomialDegree() - temp2.getPolynomialDegree());
            Subtraction sub = new Subtraction(temp1, d);

            temp1 = sub.getResult();
            i++;
        }
        this.rest = temp1;
        this.getResult().setActualPoly(res);
    }

    private Polynomial shiftLeft(Polynomial temp2, int index) {
        Polynomial t = new Polynomial();
        ArrayList <Monomial> arrayTemp2 = temp2.getArray();

        for (Monomial current : arrayTemp2) {
                Monomial newM = new Monomial();
                newM.setDegree(current.getDegree() + index);
                newM.setCoef(current.getCoef());
                t.getArray().add(newM);
        }

        return t;
    }

    public Polynomial getRest() {
        return this.rest;
    }

}

package DataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList <Monomial> actualPoly = new ArrayList<>();
    private final Monomial zero = new Monomial();

    public Polynomial() {
    }

    //I want to set the polynomial just by using the String introduced by the users
    /**
     * In a polynomial we have TERMS, delimited by the + or - signs. For my RegEx I will use this pattern because is powerful:
     * The first character may or may not be + or - (it is not mandatory for the first term to have a + in front of it)
     * For the other characters, we just want them to be different from + and - at the same time. When a + and - appears again, we know that our present term just finished
     * and the next one comes in.
     *
     */
    public void setActualPoly (String input) throws Exception {
        //we want to throw an exception because if there is no match, the user has to rewrite the polynomial
        //using the pattern obj presented in PDF
        if (input.equals(""))
            throw new Exception("Empty input");

        Pattern exact = Pattern.compile("[+-]?[^+-]+");
        Matcher token = exact.matcher(input);

        //while we have matches, create monomials and then add them to the polynomials
        while (token.find()) {
            Monomial newTerm = new Monomial(token.group());
            this.actualPoly.add(newTerm);
        }

        Collections.sort(actualPoly, new Comparator<Monomial>() {
            @Override
            public int compare(Monomial o1, Monomial o2) {
                Integer o1Degree = o1.getDegree();
                Integer o2Degree = o2.getDegree();
                return o2Degree.compareTo(o1Degree);
            }
        });

        ArrayList <Monomial> res = new ArrayList<>(this.getPolynomialDegree() + 1);
        setActualPoly2(res);
        actualPoly = res;
    }

    private void setActualPoly2(ArrayList<Monomial> res) {
        for (int i = 0; i <= this.getPolynomialDegree(); i++) {
            res.add(zero);
        }

        for(Monomial current1 : this.actualPoly) {
            if (res.get(this.getPolynomialDegree() - current1.getDegree()) == zero)
                res.set(this.getPolynomialDegree() - current1.getDegree(), current1);
            else
                res.get(this.getPolynomialDegree() - current1.getDegree()).addCoef(current1.getCoef());
        }
        this.escapeZeros();
        if(res.size() == 0)
            res.add(zero);
    }


    //getters
    public ArrayList<Monomial> getArray () {
        return this.actualPoly;
    }

    public int getPolynomialDegree() {
        if(actualPoly.size() == 0)
            return -1;

        if (actualPoly.get(0).getDegree() == 0 && actualPoly.get(0).getCoef().doubleValue() == 0.0)
            return -1;

        return this.actualPoly.get(0).getDegree();
    }


    //setters
    public void setActualPoly(ArrayList <Monomial> newPoly) {
        this.actualPoly = newPoly;
    }


    //other methods
    public String polyToString() {
        StringBuilder polyString = new StringBuilder();

        for (Monomial current : actualPoly) {
            polyString.append(current.monomialToString());
        }
        return polyString.toString();
    }

    public void escapeZeros() {
        int i = 0;
        while(i < this.actualPoly.size()) {
            if (actualPoly.get(i).getCoef().doubleValue() == 0.0) {
                actualPoly.remove(i);
                i--;
            }
            i++;
        }
    }
}

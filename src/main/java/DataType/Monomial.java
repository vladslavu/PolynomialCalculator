package DataType;

public class Monomial {
    private int degree;
    private Number coef;

    public Monomial() {
        this.degree = 0;
        this.coef = 0;
    }

    public Monomial(Monomial m) {
        degree = m.getDegree();
        coef = m.getCoef();
    }

    public Monomial(String input) throws Exception {
        //now we have to debate what values the degree and coef. will take based on that inputs
        if (!input.contains("x")) {
            if (!input.contains("/")) {
                    //we have only one positive coef. => monomial with degree 0
                    coef = Double.parseDouble(input);
                    degree = 0;
            }
            else {  //we have a coef. division
                int l = input.indexOf('/');
                coef = Double.parseDouble(input.substring(0, l));
                if(Double.parseDouble(input.substring(l + 1)) == 0.0)
                    throw new Exception("Div. 0");
                coef = coef.doubleValue()/Double.parseDouble(input.substring(l + 1));
                degree = 0;
            }
        }
        else
            continueConstructing(input);    //it contains x
    }

    private void continueConstructing(String input) throws Exception{
        if(input.equals("+x") || input.equals("-x") || input.equals("x") || input.equals("+x^1") || input.equals("-x^1")) {
            degree = 1;
            if (input.charAt(0) == 'x' || input.charAt(0) == '+')
                coef = 1;
            else
                coef = -1;
        }
        else if (input.substring(0, 2).equals("+x") || input.substring(0, 2).equals("-x") || input.charAt(0) == 'x') {
            if (input.charAt(0) == 'x' || input.charAt(0) == '+')
                coef = 1;
            else
                coef = -1;
            degree =  Integer.parseInt(input.substring(input.indexOf('^') + 1));
        }
        else {
            int starPos = input.indexOf('*');
            if (input.contains("/")) {
                int divPos = input.indexOf('/');
                coef = Double.parseDouble(input.substring(0, divPos));
                if(Double.parseDouble(input.substring(divPos + 1, starPos)) == 0.0)
                    throw new Exception("Div. 0");
                coef = coef.doubleValue() / Double.parseDouble(input.substring(divPos + 1, starPos));
            } else {
                coef = Double.parseDouble(input.substring(0, starPos));
            }
            if (input.indexOf('^') < 0) { //no power is explicitly shown => 1
                degree = 1;
            } else {
                degree = Integer.parseInt(input.substring(input.indexOf('^') + 1));
            }
        }
    }


    //setters
    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setCoef(Number coef) {
        this.coef = coef;
    }


    //getters
    public int getDegree() {
        return this.degree;
    }

    public Number getCoef() {
        return this.coef;
    }


    //other methods
    public void oposeCoef() {
        this.coef = 0.0 -this.coef.doubleValue();
    }

    public void addCoef(Number no) {
        this.coef = this.coef.doubleValue() + no.doubleValue();
    }

    public void subCoef(Number no) {
        this.coef = this.coef.doubleValue() - no.doubleValue();
    }

    public void multiplyCoef(Number no) {
        this.coef = this.coef.doubleValue() * no.doubleValue();
    }

    public String monomialToString() {
        String monomialString = "";
        if(coef.doubleValue() == 0.0)
            return "";
        if(coef.doubleValue() > 0.0)
            monomialString = monomialString + "+";  //add the plus

        if(Math.abs(coef.doubleValue()) == 1.0) {
            if(coef.doubleValue() > 0) {
                if (degree == 0)
                    return "+1";
                else if (degree == 1)
                    return "+x";
                else
                    return "+x^" + String.valueOf(degree);
            }
            else {
                if (degree == 0)
                    return "-1";
                else if (degree == 1)
                    return "-x";
                else
                    return "-x^" + String.valueOf(degree);
            }
        }
        monomialString = monomialToString2(monomialString);
        return monomialString;
    }

    private String monomialToString2(String monomialString) {
        if (Math.floor(coef.doubleValue()) == Math.ceil(coef.doubleValue())) //integer
            monomialString = monomialString + String.valueOf(coef.intValue());
        else
            monomialString = monomialString + String.valueOf(roundDouble(coef.doubleValue()));

        if (degree == 0)
            return monomialString;

        monomialString = monomialString + "*x";

        if (degree != 1)
            monomialString = monomialString + "^" + String.valueOf(degree);


        return monomialString;
    }

    private static double roundDouble(double value) {
        double scale = Math.pow(10, 3);
        return Math.round(value * scale) / scale;
    }

    public Monomial derivateMonomial () {
        if (degree != 0) {
            this.coef = coef.doubleValue() * degree;
            this.degree--;
        }
        else
            this.coef = 0.0;

        return this;
    }

    public Monomial integrateMonomial() {
        if (coef.doubleValue() != 0.0) {
            this.degree++;
            this.coef = coef.doubleValue()/(double) degree;
        }
        return this;
    }

    public Monomial multiply(Monomial current2) {
        Monomial newM = new Monomial();
        newM.setDegree(current2.getDegree() + degree);
        newM.setCoef(current2.getCoef().doubleValue() * coef.doubleValue());
        return newM;
    }
}

package Operations;

import DataType.Monomial;
import DataType.Polynomial;

public abstract class OperationOnOne {
    private final Polynomial poly = new Polynomial();
    private final Polynomial result = new Polynomial();
    public Monomial ZERO = new Monomial();


    //getters
    public Polynomial getPoly () {
        return poly;
    }

    public Polynomial getResult () {
        return result;
    }

}

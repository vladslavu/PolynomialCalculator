package Operations;
import DataType.*;


public abstract class Operation {
    private Polynomial poly1 = new Polynomial();
    private Polynomial poly2 = new Polynomial();
    private final Polynomial result = new Polynomial();
    public final Monomial zero = new Monomial();


    //seters
    public void setPoly1 (Polynomial poly1) {
        this.poly1 = poly1;
    }

    public void setPoly2 (Polynomial poly2) {
        this.poly2 = poly2;
    }


    //getters
    public Polynomial getPoly1 () {
        return poly1;
    }

    public Polynomial getPoly2 () {
        return poly2;
    }

    public Polynomial getResult () {
        return result;
    }

}

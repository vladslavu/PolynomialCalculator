package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import DataType.Polynomial;
import Operations.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        //defining the polynomials
        String poly1String = "x^3+3*x^2+6*x+5";
        String poly2String = "x^4+2*x^3+5*x+3";

        try {
            Addition add = new Addition(poly1String, poly2String);
            Subtraction sub = new Subtraction(poly1String, poly2String);
            Multiplication mult = new Multiplication(poly1String, poly2String);

            Derivation deriv = new Derivation(poly1String);
            Integration integration = new Integration(poly2String);

            Division div = new Division(poly2String, poly1String);

            //assertions
            Assert.assertEquals("+x^4+3*x^3+3*x^2+11*x+8", add.getResult().polyToString()); //addition
            Assert.assertEquals("-x^4-x^3+3*x^2+x+2", sub.getResult().polyToString());  //subtraction
            Assert.assertEquals("+x^7+5*x^6+12*x^5+22*x^4+28*x^3+39*x^2+43*x+15", mult.getResult().polyToString()); //multiplication
            Assert.assertEquals("+0.2*x^5+0.5*x^4+2.5*x^2+3*x", integration.getResult().polyToString());    //integration
            Assert.assertEquals("+3*x^2+6*x+6", deriv.getResult().polyToString());  //derivation
            Assert.assertEquals("+x-1", div.getResult().polyToString());    //division quotient
            Assert.assertEquals("-3*x^2+6*x+8", div.getRest().polyToString()); //division reminder

        } catch (Exception e) {
            System.out.println("Polynomials are not introduced correctly!");
        }
    }
}

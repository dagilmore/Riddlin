package com.riddlin.app.domain.riddle.americanIdiom;

import org.junit.*;
import static org.junit.Assert.*;

public class AmericanIdiomTest {

    @Test
    public void testGetClue() throws Exception {
    	AmericanIdiom americanIdiom = new AmericanIdiom();
    	americanIdiom.setIdiom("abbccc");
    	assertEquals(americanIdiom.getClue('a'), "a_____");
    	assertEquals(americanIdiom.getClue('b'), "abb___");
    	assertEquals(americanIdiom.getClue('c'), "abbccc");
    }

    public void testGetInitialState() throws Exception {
    	AmericanIdiom americanIdiom = new AmericanIdiom();
    	americanIdiom.setIdiom("a bb ccc");
    	assertEquals(americanIdiom.getInitialState(),"_ __ ___");
    }
}
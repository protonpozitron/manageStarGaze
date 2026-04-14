package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.astronomy.Coordinates;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        double d = Coordinates.getLocalTime(55.966)/15;
         int h = (int)d;
        int m = (int)((d-h)*60);
        int s = (int)(((d-h)*60)-m)*60;
        System.out.println( Coordinates.getLocalTime(55.966));
        System.out.println("h = "+h);
        System.out.println("m = "+m);
        System.out.println("s = "+s);

    }
}

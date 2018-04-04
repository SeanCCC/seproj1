package seanccc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        assertEquals(10, App.op(43, 3, 7));
        assertEquals(-4, App.op(45, 3, 7));
        assertEquals(21, App.op(42, 3, 7));
        assertEquals(10, App.op(47, 70, 7));
    }
}

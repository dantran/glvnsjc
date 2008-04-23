package org.glvnsjc.itest;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest
    extends TestCase
{

    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( CleanDBTest.suite() );
        st.addTest( AdminTest.suite() );
        st.addTest( ProfileTest.suite() );
        st.addTest( CleanDBTest.suite() );
        return new Tomcat5xTestSetup( st );
    }
}

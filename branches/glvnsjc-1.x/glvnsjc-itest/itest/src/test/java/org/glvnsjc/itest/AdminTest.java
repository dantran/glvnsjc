package org.glvnsjc.itest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdminTest
    extends TestCase
{

    
    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( BannerTest.suite() );
        st.addTest( LoginTest.suite() );
        st.addTest( ConfigurationTest.suite() );
        st.addTest( SchoolCalendarTest.suite() );
        st.addTest( SchoolTest.suite() );
        return st;
    }
}

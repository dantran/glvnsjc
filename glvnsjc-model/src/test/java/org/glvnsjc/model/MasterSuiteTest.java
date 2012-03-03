package org.glvnsjc.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MasterSuiteTest
    extends TestCase
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        //suite.addTest( ClassSubNameTest.suite() );
        return suite;
    }

}

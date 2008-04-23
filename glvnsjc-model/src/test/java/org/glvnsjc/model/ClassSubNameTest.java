package org.glvnsjc.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ClassSubNameTest
    extends TestCase
{
    public ClassSubNameTest( String name ) 
    {
        super( name );
    }
    
    public void testPresentation()
    {
        assertEquals( "A", ClassSubName.A.toString() );
        
        assertEquals( "", ClassSubName.UNASSIGNED.toString() );
    }
    
    public void testEnum()
    {
        assertEquals( new Integer( 1 ), (Integer) ClassSubName.A.getEnumCode() );
    }

    public void testBoundary()
    {
        int E = ( (Integer)ClassSubName.E.getEnumCode() ).intValue();

        int UNASSIGNED = ( (Integer)ClassSubName.UNASSIGNED.getEnumCode() ).intValue();
        
        assertEquals( 5, E - UNASSIGNED );
    }
    
    public static Test suite()
    {
        TestSuite st = new TestSuite();
        st.addTest( new ClassSubNameTest( "testPresentation" ) );
        st.addTest( new ClassSubNameTest( "testEnum" ) );
        st.addTest( new ClassSubNameTest( "testBoundary" ) );
        return st;
    }
    
}

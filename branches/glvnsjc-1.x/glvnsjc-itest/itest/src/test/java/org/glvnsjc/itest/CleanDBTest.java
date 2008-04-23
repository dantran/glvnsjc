/**
 * 
 */
package org.glvnsjc.itest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Remove all record in DB
 * @author dtran
 *
 */
public class CleanDBTest
    extends TestCase
{

    /**
     * @param arg0
     */
    public CleanDBTest( String arg0 )
    {
        super( arg0 );
    }

    public void testRemoveStudents()
    {
        
    }
    
    public void testRemoveSchools()
    {
        
    }
    
    public void testRemoveSchoolCalendar()
    {
        
    }
    
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( new SchoolTest( "testRemoveSchools" ) );
        return suite;
    }    
    
    
}

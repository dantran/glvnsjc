package org.glvnsjc.test;

import java.io.File;

public class TestCase
    extends junit.framework.TestCase
{
    public TestCase()
    {
        super();
    }
    
    public TestCase ( String name )
    {
        super( name );
    }
    
    private static  File baseDir;
    
    private static  File testDir;
    
    protected static File getBasedir()
    {
        if ( baseDir == null )
        {
            baseDir = new File ( System.getProperty( "basedir", "./" ) );
        }
        return baseDir;
    }
    
    protected static File getTestDir()
    { 
        if ( testDir == null )
        {
            testDir = new File ( getBasedir(), "target/glvnscj-test" );
        }
        return testDir;
    }
    
    public void setUp()
    {
        if ( ! getTestDir().isDirectory() ) 
        {
            testDir.mkdirs();
        }
    }
}
  

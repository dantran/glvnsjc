package org.glvnsjc.util;

import java.io.File;

import org.glvnsjc.test.TestCase;

public class DBBackupTest
    extends TestCase
{

    private static String TEST_FILE_NAME = "hsqldb.script";
    
    public void testBackup()
        throws Exception
    {
        File testFile = new File ( getBasedir() +  "/src/test/resources/" + TEST_FILE_NAME );
        
        File backupFile = new File ( getTestDir(), TEST_FILE_NAME );

        if ( backupFile.exists() )
        {
            backupFile.delete();
        }

        assertTrue( "Backup file exists", ! backupFile.exists() );
        
        File compressFile = DBBackup.run( testFile, backupFile );

        assertTrue( "Backup file is not created", backupFile.exists() );
        
        // ensure compress file is in the same dir as the backup file 
        assertEquals( backupFile.getParent(), compressFile.getParent() );
        
        assertNull( "Backup happens when there is no change", DBBackup.run( testFile, backupFile ) );
        
    }
    
}

package org.glvnsjc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.plexus.archiver.ArchiverException;
import org.codehaus.plexus.archiver.bzip2.BZip2Archiver;
import org.codehaus.plexus.util.FileUtils;

/**
 * Copy a file ( from ) to another file ( to ), and then compress the new file with 
 * bzip format.
 * @author dtran
 *
 */
public class DBBackup
{
    public static File run( File from , File to )
        throws IOException
    {
        if ( ! isDataChanged( from, to ) )
        {
            return null;
        }
        
        if ( to.getParentFile().exists() )
        {
            to.getParentFile().mkdirs();
        }
        
        FileUtils.copyFile( from, to );
        
        //compress backup file
        File compressedBackupFile = new File ( to.getAbsolutePath() + "." + getTimestamp() + ".bz2" );
        
        BZip2Archiver compressor = new BZip2Archiver();
        
        try 
        {
            compressor.addFile( to, FileUtils.filename( to.getPath() ) );
            
            compressor.setDestFile( compressedBackupFile );
            
            compressor.createArchive();
        }
        catch ( ArchiverException e )
        {
            throw new IOException ( e.getMessage() );
        }
        
        return compressedBackupFile;
    }
    
    private static String getTimestamp()
    {
        SimpleDateFormat dt = new SimpleDateFormat( "yyyyMMddHHmmss" );
        
        StringBuffer buf = new StringBuffer();
        
        Date now = new Date();
        
        buf = dt.format( now, buf, new java.text.FieldPosition( 0 ) );
        
        return buf.toString();
    }
    
    /* check if the previous backup file is same as the current file */
    private static boolean isDataChanged( File dbFile, File previousBackupFile )
        throws IOException
    {
        if ( ! previousBackupFile.exists() )
        {
            return true;
        }

        BufferedReader dbBuffer = new BufferedReader( new FileReader( dbFile ) );
        BufferedReader bakBuffer = new BufferedReader( new FileReader( previousBackupFile ) );
        String dbText = null;
        String bakText = null;
        while ( ( dbText = dbBuffer.readLine() ) != null )
        {
            bakText = bakBuffer.readLine();
            if ( bakText == null )
            {
                return true;
            }
            if ( !dbText.equals( bakText ) )
            {
                return true;
            }
        }

        bakText = bakBuffer.readLine();
        if ( bakText != null )
        {
            //backup file is longer than the current file
            return true;
        }

        return false;
    }
    
}

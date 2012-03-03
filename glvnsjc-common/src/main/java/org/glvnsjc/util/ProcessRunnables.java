package org.glvnsjc.util;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ProcessRunnables
    extends Thread
{

    private static final Log log = LogFactory.getLog( ProcessRunnables.class );

    private static ProcessRunnables s_instance = null;

    private LinkedList queue = new LinkedList();

    private boolean done = false;

    private boolean isIdle = false;

    static public ProcessRunnables getInstance()
    {
        if ( s_instance == null )
        {
            s_instance = new ProcessRunnables();
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

    static public void terminate()
    {
        if ( s_instance != null )
        {
            s_instance.abort();
            s_instance = null;
        }
    }

    private ProcessRunnables()
    {
        this.start();
    }

    public synchronized void submit( Runnable obj )
    {
        this.queue.addLast( obj );
        this.notify();
    }

    public void run()
    {
        while ( !this.done )
        {
            Runnable o = (Runnable) waitForObject();
            if ( o != null )
            {
                try
                {
                    o.run();
                }
                catch ( Throwable e )
                {
                    //should never happen
                    log.error( "Exception slipped thru Runnable.  Please check", e );
                }
                continue;
            }
        }
    }

    public synchronized void abort()
    {
        this.done = true;
        notify();
        try
        {
            this.join();
        }
        catch ( InterruptedException e )
        {
        }
    }

    private synchronized void setIdle( boolean isIdle )
    {
        this.isIdle = isIdle;
    }

    private synchronized Object waitForObject()
    {
        if ( this.queue.isEmpty() )
        {
            try
            {
                setIdle( true );
                wait();
            }
            catch ( InterruptedException e )
            {
            }
            setIdle( false );
        }
        if ( this.done )
        {
            return null;
        }
        return queue.removeFirst();
    }

}
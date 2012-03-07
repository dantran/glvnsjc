package org.glvnsjc.model;

import java.util.Date;

public class Eucharist
{
    private Date date;
    
    private String location = "";
    
    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    
}

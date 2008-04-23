package org.glvnsjc.model;

/**
 * @author Dan Tran
 * @hibernate.class  table="day"
 */

public class Day
    implements java.io.Serializable
{

    private java.util.Date day;

    public Day()
    {
    }

    /**
     * @hibernate.id  generator-class="assigned"
     */

    public java.util.Date getDay()
    {
        return this.day;
    }

    public void setDay( java.util.Date value )
    {
        this.day = value;
    }

}

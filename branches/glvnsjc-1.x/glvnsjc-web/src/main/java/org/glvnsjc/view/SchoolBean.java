package org.glvnsjc.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class SchoolBean
    implements java.io.Serializable
{

    private String objId;

    private String shortName;

    private String longName;

    public SchoolBean()
    {
    }

    public String getObjId()
    {
        return this.objId;
    }

    public void setObjId( String newValue )
    {
        this.objId = newValue;
    }

    public String getShortName()
    {
        return this.shortName;
    }

    public void setShortName( String newValue )
    {
        this.shortName = newValue;
    }

    public String getLongName()
    {
        return this.longName;
    }

    public void setLongName( String newValue )
    {
        this.longName = newValue;
    }
}
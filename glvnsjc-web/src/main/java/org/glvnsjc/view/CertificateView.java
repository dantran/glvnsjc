package org.glvnsjc.view;

import org.glvnsjc.model.CertificateType;

@SuppressWarnings( "serial" )
public class CertificateView
    implements java.io.Serializable
{
    private String id; // primary key

    private String typeId;

    private String date;

    private boolean persisted = false;

    public CertificateView()
    {
    }

    public CertificateView( CertificateType type, String date )
    {
        this.typeId = type.getEnumCode().toString();
        this.date = date;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getTypeId()
    {
        return typeId;
    }

    public void setTypeId( String id )
    {
        this.typeId = id;
    }

    //for display only
    public String getDescription()
    {
        return CertificateType.fromNumber( Integer.parseInt( typeId ) ).getDisplay();
    }

    public String getDate()
    {
        return date;
    }

    public void setDate( String certifiedDate )
    {
        this.date = certifiedDate;
    }

    public boolean isPersisted()
    {
        return persisted;
    }

    public void setPersisted( boolean persisted )
    {
        this.persisted = persisted;
    }

}

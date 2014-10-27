package org.glvnsjc.view;


public class CertificateView
    implements java.io.Serializable
{
    private int id; //primary key

    private String certificateTypeId;

    private String descripion;

    private String certifiedDate;

    private boolean editAllow = false;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getCertificateTypeId()
    {
        return certificateTypeId;
    }

    public void setSertificateTypeId( String id )
    {
        this.certificateTypeId = id;
    }

    public String getDescripion()
    {
        return descripion;
    }


    public String getCertifiedDate()
    {
        return certifiedDate;
    }

    public void setCertifiedDate( String certifiedDate )
    {
        this.certifiedDate = certifiedDate;
    }

    public boolean isEditAllow()
    {
        return editAllow;
    }

    public void setEditAllow( boolean editAllow )
    {
        this.editAllow = editAllow;
    }

}

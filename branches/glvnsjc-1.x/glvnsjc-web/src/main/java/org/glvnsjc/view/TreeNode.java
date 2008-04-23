/* NeXt:recursion tutorial
 * next@keyboardmonkey.com
 */
package org.glvnsjc.view;

import java.util.ArrayList;

/** This is a class you write yourself in the < NeXt:recursion tutorial >
 */
public class TreeNode
    implements java.io.Serializable
{

    /* constructor for the node */
    public TreeNode( String name, int level )
    {
        this.level = level;
        this.nodeName = name;
        this.children = new ArrayList();
        this.showChildren = false;
    }

    /* return the collection of children */
    public ArrayList getChildCollection()
    {
        return this.children;
    }

    /* add child method */
    public void addChild( TreeNode newChild )
    {
        this.children.add( newChild );
    }

    /* getter method for the "nodeName" property */
    public String getNodeName()
    {
        return this.nodeName;
    }

    /* setter method for the "nodeName" property */
    public void setNodeName( String newName )
    {
        this.nodeName = newName;
    }

    /* getter to return the size of our indent */
    public int getNodeIndent()
    {
        return ( this.level * 10 );
    }

    /* getter method for the "showChildren" property */
    public boolean getShowChildren()
    {
        return this.showChildren;
    }

    /* setter method for the "showChildren" property */
    public void setShowChildren( boolean showChildren )
    {
        this.showChildren = showChildren;
    }

    /* return true if this node has child nodes */
    public boolean getHasChildren()
    {
        return ( this.children.size() > 0 );
    }

    /* a "fake" nested bean property (for image submit) */
    public TreeNode getToggle()
    {
        return this;
    }

    /* setter for the image submit's ".x" property */
    public void setX( int i )
    {
        /* reverse the showChildren boolean */
        this.showChildren = !this.showChildren;
    }

    /* empty setter for the image submit's ".y" property */
    public void setY( int i )
    {
    }

    public TreeNode setHref( String href )
    {
        this.href = href;
        return this;
    }

    public String getHref()
    {
        return href;
    }

    /* usual student variables */
    private int level;

    private ArrayList children;

    private String nodeName;

    private boolean showChildren;

    private String href;
}
package org.glvnsjc.util;

/**
 * <p>Title: Constants </p>
 * <p>Description: Global Constants.  hibernate.properties and log4j.properties must conform to this file </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: GLVNSJC </p>
 * @author Dan Tran
 * @version 1.0
 */

public final class Constants
{
    public static final String DATA_DIR = "glvnsjc/";

    public static final String BAK_DIR = DATA_DIR + "backup/";

    public static final String DB_FILE_NAME = "hsqldb.script";

    public static final String DB_FULL_PATH = DATA_DIR + DB_FILE_NAME;

    public static final String DB_BAK_FULL_PATH = BAK_DIR + DB_FILE_NAME;

}
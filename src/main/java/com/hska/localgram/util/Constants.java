package com.hska.localgram.util;

/**
 * Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
public class Constants {

    public static final String FILE_ROOT_PATH = System.getProperty("catalina.home");
    public static final int MAX_UPLOAD_SIZE = 100_000_000;
    public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    public static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    public static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    public static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    public static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    public static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
}

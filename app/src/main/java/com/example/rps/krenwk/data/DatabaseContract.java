package com.example.rps.krenwk.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API DatabaseContract for the SQL Contact List app.
 * Defines a database and interaction with it through the Content Provider.
 */
public final class DatabaseContract {

    // Content Authority provider name
    public static final String PROVIDER_NAME = "com.sundaydevblog.sqlcontactlist";

    // Takes in URI string and parse it (return) as URI.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);

    // Stores the path for the table.
    public static final String PATH_CONTACTS = "contacts";

    // Empty constructor to prevent someone from accidentally instantiating the contract class
    public DatabaseContract() {
    }

    /**
     * Inner class that defines constant values for the Contact List database table.
     */
    public static final class ContactEntry implements BaseColumns {

        /**
         * The content URI to access the contact data in the provider which contains the scheme
         * and the content authority.
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CONTACTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of contacts.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PROVIDER_NAME + "/" + PATH_CONTACTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single contact.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PROVIDER_NAME + "/" + PATH_CONTACTS;

        /**
         * Database table name.
         */
        public final static String TABLE_NAME = "contacts";

        /**
         * Database columns.
         */
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_ADDRESS = "address";
        public final static String COLUMN_PHONE = "phone";
        public final static String COLUMN_PICTURE = "picture";

    }

}
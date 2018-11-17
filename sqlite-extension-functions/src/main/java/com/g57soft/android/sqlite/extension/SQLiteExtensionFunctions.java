package com.g57soft.android.sqlite.extension;

import android.content.pm.ApplicationInfo;

import io.requery.android.database.sqlite.SQLiteCustomExtension;

/**
 * Created by Ihor Havryliuk on 17.11.2018.
 */
public class SQLiteExtensionFunctions {

    public String getPath( final ApplicationInfo info ) {
        return info.nativeLibraryDir + "/libsqlitefunctions.so";
    }

    public SQLiteCustomExtension getExtension( final String path ) {
        //If the entry point is omitted then a default entry point function named
        // sqlite3_extension_init is called.
        // Use of the default entry point name is recommended.
        return new SQLiteCustomExtension(path, null);
    }

    public SQLiteCustomExtension getExtension( final ApplicationInfo info ) {
        return getExtension(getPath(info));
    }
}

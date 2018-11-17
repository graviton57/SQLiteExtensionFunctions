package org.sqlite.app.customsqlite;

import android.app.Application;
import android.content.Context;

import io.requery.android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Igor Havrylyuk on 28.08.2017.
 */

public class TestApp extends Application {

    private TestDBHelper dbHelper;

    @Override public void onCreate() {
        super.onCreate();
        dbHelper = new TestDBHelper(this);
    }

    public static SQLiteOpenHelper obtainDatabaseHelper(final Context context) {
        return ((TestApp)context.getApplicationContext()).dbHelper;
    }
}

package org.sqlite.app.customsqlite;

import android.content.Context;
import android.util.Log;

import com.g57soft.android.sqlite.extension.SQLiteExtensionFunctions;

import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteDatabaseConfiguration;
import io.requery.android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Igor Havrylyuk on 28.08.2017.
 */

public class TestDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public TestDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        Log.d("TestDBHelper", "Constructor db path=" + DATABASE_NAME);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TestDBHelper", "onCreate" );
        db.execSQL("CREATE TABLE salaries (per_moth INTEGER , job_title TEXT )");
        db.execSQL("INSERT INTO salaries VALUES (900, 'Junior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (1500, 'Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (3200, 'Senior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (800, 'Junior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (1300, 'Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (200, 'Senior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (600, 'Junior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (1200, 'Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (2400, 'Senior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (400, 'Junior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (1500, 'Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (2700, 'Senior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (300, 'Junior Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (1500, 'Software Engineer')");
        db.execSQL("INSERT INTO salaries VALUES (2600, 'Senior Software Engineer')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    protected SQLiteDatabaseConfiguration createConfiguration( final String path, final int openFlags ) {
        final SQLiteDatabaseConfiguration config = super.createConfiguration( path, openFlags );
        config.customExtensions.add( new SQLiteExtensionFunctions().getExtension( context.getApplicationInfo()) );
        return config;
    }
}

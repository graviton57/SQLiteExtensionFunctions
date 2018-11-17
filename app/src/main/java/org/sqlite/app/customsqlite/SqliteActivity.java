
package org.sqlite.app.customsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteStatement;


public class SqliteActivity extends AppCompatActivity {

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        testExtension();
    }

    public String getSQLiteVersion(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        SQLiteStatement st = db.compileStatement("SELECT sqlite_version()");
        String res  = st.simpleQueryForString();
        db.close();
        return String.format("SQLite version: %s\n\n", res);
    }

    private void testExtension(){
        String[] selections = new String[]{"lower_quartile(per_moth)","median(per_moth)","upper_quartile(per_moth)"};
        String version = getSQLiteVersion();
        new Thread(() -> {
            final Cursor c = TestApp.obtainDatabaseHelper(this)
                    .getReadableDatabase()
                    .query("salaries", selections, null, null, null, null, null);
            if (c != null && c.moveToFirst()) {
                String result = String.format("lower quartile = %s, median = %s, upper quartile = %s ", c.getString(0), c.getString(1),c.getString(2));
                info.setText(String.format("%s%s", version, result));
            }
        }).run();
    }
}



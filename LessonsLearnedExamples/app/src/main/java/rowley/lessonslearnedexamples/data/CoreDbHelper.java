package rowley.lessonslearnedexamples.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import rowley.lessonslearnedexamples.R;

public class CoreDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LessonsLearnedDb";
    private static final int VERSION = 1;

    protected Context mContext;

    public CoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(mContext.getString(R.string.sql_performance_test_table));
            db.setTransactionSuccessful();
        } catch(SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package rowley.lessonslearnedexamples.sql_performance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import java.util.Locale;

import rowley.lessonslearnedexamples.data.CoreDbHelper;

public class SqlPerformanceDao extends CoreDbHelper {
    private final String TABLE_NAME = "perf_test";
    private final String COLUMN_ID = "_id";
    private final String COLUMN_ONE = "val_one";
    private final String COLUMN_TWO = "val_two";
    private final String COLUMN_THREE = "val_three";
    private final String COLUMN_FOUR = "val_four";
    private final String COLUMN_FIVE = "val_five";
    private final String COLUMN_SIX = "val_six";


    public SqlPerformanceDao(Context context) {
        super(context);
    }

    public void addTestDataWithSqlStatement(int numToAdd) {
        SQLiteDatabase db = getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement(String.format(Locale.US, "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);", TABLE_NAME, COLUMN_ONE, COLUMN_TWO, COLUMN_THREE, COLUMN_FOUR, COLUMN_FIVE, COLUMN_SIX));
        db.beginTransaction();
        try {
            for(int i = 0; i < numToAdd; i++) {
                stmt.bindString(1, String.valueOf(i));
                stmt.bindString(2, String.valueOf(i));
                stmt.bindLong(3, i);
                stmt.bindLong(4, i);
                stmt.bindString(5, String.valueOf(i));
                stmt.bindLong(6, i);

                stmt.execute();

                stmt.clearBindings();

                db.yieldIfContendedSafely();
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            stmt.clearBindings();
            stmt.close();
            db.endTransaction();
            db.close();
        }
    }

    public void addTestDataWithSqlStatementNoTransaction(int numToAdd) {
        SQLiteDatabase db = getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement(String.format(Locale.US, "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);", TABLE_NAME, COLUMN_ONE, COLUMN_TWO, COLUMN_THREE, COLUMN_FOUR, COLUMN_FIVE, COLUMN_SIX));
//        db.beginTransaction();
        try {
            for(int i = 0; i < numToAdd; i++) {
                stmt.bindString(1, String.valueOf(i));
                stmt.bindString(2, String.valueOf(i));
                stmt.bindLong(3, i);
                stmt.bindLong(4, i);
                stmt.bindString(5, String.valueOf(i));
                stmt.bindLong(6, i);

                stmt.execute();

                stmt.clearBindings();

//                db.yieldIfContendedSafely();
            }
//            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            stmt.clearBindings();
            stmt.close();
//            db.endTransaction();
            db.close();
        }
    }

    public void addTestDataWithContentValues(int numToAdd) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            for(int i = 0; i < numToAdd; i++) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_ONE, String.valueOf(i));
                cv.put(COLUMN_TWO, String.valueOf(i));
                cv.put(COLUMN_THREE, i);
                cv.put(COLUMN_FOUR, i);
                cv.put(COLUMN_FIVE, String.valueOf(i));
                cv.put(COLUMN_SIX, i);

                db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_IGNORE);

                db.yieldIfContendedSafely();
            }
            db.setTransactionSuccessful();
        } catch(SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void addTestDataWithContentValuesWorstPerformance(int numToAdd) {
        SQLiteDatabase db = getWritableDatabase();
//        db.beginTransaction();

        try {
            for(int i = 0; i < numToAdd; i++) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_ONE, String.valueOf(i));
                cv.put(COLUMN_TWO, String.valueOf(i));
                cv.put(COLUMN_THREE, i);
                cv.put(COLUMN_FOUR, i);
                cv.put(COLUMN_FIVE, String.valueOf(i));
                cv.put(COLUMN_SIX, i);

                db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_IGNORE);

//                db.yieldIfContendedSafely();
            }
//            db.setTransactionSuccessful();
        } catch(SQLiteException e) {
            e.printStackTrace();
        } finally {
//            db.endTransaction();
            db.close();
        }
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
         try {
             SQLiteStatement stmt = db.compileStatement(String.format(Locale.US, "DELETE FROM %s WHERE %s NOT NULL", TABLE_NAME, COLUMN_ID));
             stmt.execute();
             stmt.clearBindings();
             stmt.close();
             db.setTransactionSuccessful();
         } catch(SQLiteException e) {
             e.printStackTrace();
         } finally {
             db.endTransaction();
         }
    }
}

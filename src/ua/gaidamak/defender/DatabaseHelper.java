import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.lang.UnsupportedOperationException;

/**
 * This class helps open, create, and upgrade the database file.
 */
class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "protected_database.db";
    private static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context) {
        // calls the super constructor, requesting the default cursor factory.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * Creates the underlying database with table name and column names taken from the
     * NotePad class.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UsersTable.TABLE_NAME + " ("
                   + Userstable._ID + " INTEGER PRIMARY KEY,"
                   + UsersTable.COLUMN_NAME_LOGIN + " TEXT,"
                   + UsersTable.COLUMN_NAME_PASSWORD + " TEXT"
                   + ");");

        ContentValues cv = new ContentValues();
        cv.put(UsersTable.COLUMN_NAME_LOGIN, "login");
        cv.put(UsersTable.COLUMN_NAME_PASSWORD, "pass");

        db.insert(UsersTable.TABLE_NAME, null, cv);
    }

    /**
     *
     * Demonstrates that the provider must consider what happens when the
     * underlying datastore is changed. In this sample, the database is upgraded the database
     * by destroying the existing data.
     * A real application should upgrade the database in place.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException();
    }
}

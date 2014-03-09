import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import java.util.HashMap;

class DefenderProvider extends ContentProvider {
    // A projection map used to select columns from the database
    private final HashMap<String, String> mUsersProjectionMap;
    // XXX in second iteration
    // private final HashMap<String, String> mNotesProjectionMap;
    // Uri matcher to decode incoming URIs.
    private final UriMatcher mUriMatcher;

    private static final int USER = 1;
    private static final int USER_ID = 2;
    // XXX Unused for now
    //private static final int NOTE = 1;
    //private static final int NOTE_ID = 2;

    // Handle to a new DatabaseHelper.
    // XXX Implement DatabaseHelper
    //private DatabaseHelper mOpenHelper;

    /**
     * Global provider initialization.
     */
    public DefenderProvider() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(UsersTable.AUTHORITY, UsersTable.TABLE_NAME, USER);
        mUriMatcher.addURI(UsersTable.AUTHORITY, UsersTable.TABLE_NAME + "/#", USER_ID);
        
        mUsersProjectionMap = new HashMap<String, String>();
        mUsersProjectionMap.put(UsersTable._ID, UsersTable._ID);
        mUsersProjectionMap.put(UsersTable.COLUMN_NAME_LOGIN, UsersTable.COLUMN_NAME_LOGIN);
        mUsersProjectionMap.put(UsersTable.COLUMN_NAME_PASSWORD, UsersTable.COLUMN_NAME_PASSWORD);
        // XXX In second iteration
        // mNotesProjectionMap = new HashMap<String, String>();
    }

    /**
     * Perform provider creation.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * Handle incoming queries.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(UsersTable.TABLE_NAME);

        switch (mUriMatcher.match(uri)) {
        case USER:
            qb.setProjectionMap(mUsersProjectionMap);
            break;
        case USER_ID:
            qb.setProjectionMap(mUsersProjectionMap);
            qb.appendWhere(UsersTable._ID + "=?");
            selectionArgs = DatabaseUtils.appendSelectionArgs(selectionArgs,
                                                              new String[] { uri.getLastPathSegment() });
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        } 

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor c = qb.query(db, projection, selection, selectionArgs,
                            null, null, null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    /**
     * Return the MIME type for an known URI in the provider.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * Handler inserting new data.
     */
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        return null;
    }

    /**
     * Handle deleting data.
     */
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        return 0;
    }

    /**
     * Handle updating data.
     */
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        return 0;
    }

    DatabaseHelper mOpenHelper;
}

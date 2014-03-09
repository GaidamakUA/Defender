import android.net.Uri;
import android.provider.BaseColumns;

public final class UsersTable implements BaseColumns {

    public static final String AUTHORITY = "ua.gaidamak.defender";

    // This class cannot be instantiated
    private UsersTable() {}

    /**
     * The table name offered by this provider
     */
    public static final String TABLE_NAME = "users";

    /**
     * The content:// style URL for this table
     */
    public static final Uri CONTENT_URI =  Uri.parse("content://" + AUTHORITY + "/main");

    /**
     * The content URI base for a single row of data. Callers must
     * append a numeric row id to this Uri to retrieve a row
     */
    public static final Uri CONTENT_ID_URI_BASE
        = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME + "/");

    public static final String COLUMN_NAME_LOGIN = "login";
    public static final String COLUMN_NAME_PASSWORD = "password";
}

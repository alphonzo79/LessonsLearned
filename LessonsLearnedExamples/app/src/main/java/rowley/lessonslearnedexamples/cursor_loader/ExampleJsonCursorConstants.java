package rowley.lessonslearnedexamples.cursor_loader;

public class ExampleJsonCursorConstants {
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_NAME = 1;
    public static final int COLUMN_COUNT = 2;
    public static final int COLUMN_POSITION = 3;

    public static String[] getExampleCursorColumns() {
        return new String[]{"_id", "name", "count", "position"};
    }
}

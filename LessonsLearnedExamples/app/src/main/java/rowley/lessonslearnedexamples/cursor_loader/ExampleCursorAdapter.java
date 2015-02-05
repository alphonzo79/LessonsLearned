package rowley.lessonslearnedexamples.cursor_loader;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class ExampleCursorAdapter extends CursorAdapter {
    public ExampleCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public ExampleCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        CursorExampleListCell cell = new CursorExampleListCell(context);
        bindView(cell, context, cursor);
        return cell;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(ExampleJsonCursorConstants.COLUMN_NAME);
        int count = cursor.getInt(ExampleJsonCursorConstants.COLUMN_COUNT);
        int position = cursor.getInt(ExampleJsonCursorConstants.COLUMN_POSITION);

        ((CursorExampleListCell)view).setData(name, count, position);
    }
}

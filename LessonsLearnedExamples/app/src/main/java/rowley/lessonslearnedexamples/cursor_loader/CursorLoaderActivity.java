package rowley.lessonslearnedexamples.cursor_loader;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import rowley.lessonslearnedexamples.R;

public class CursorLoaderActivity extends Activity {

    private final int CURSOR_LOADER_ID = 1;
    private LoaderCallbacks<Cursor> cursorLoaderCallbacks;

    private ListView listView;
    private CursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cursor_adapter);

        listView = (ListView)findViewById(R.id.cursor_list_view);
        listView.setEmptyView(findViewById(R.id.empty_view));

        cursorLoaderCallbacks = new LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                ExampleCursorLoader loader = new ExampleCursorLoader(CursorLoaderActivity.this);

                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                adapter = new ExampleCursorAdapter(CursorLoaderActivity.this, data, 0);
                listView.setAdapter(adapter);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        };

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, cursorLoaderCallbacks);
    }
}

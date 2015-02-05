package rowley.lessonslearnedexamples.cursor_loader;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rowley.lessonslearnedexamples.R;

public class ExampleCursorLoader extends CursorLoader {
    private Cursor result;
    private Context context;

    public ExampleCursorLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onStartLoading() {
        if(result != null)
            deliverResult(result);

        if(takeContentChanged() || result == null)
            forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();

        if (result != null) {
            result = null;
        }
    }

    @Override
    public void deliverResult(Cursor data) {
        result = data;

        if(isReset()){
            if(result != null){
                result = null;
                return;
            }
        }
        if(isStarted()){
            super.deliverResult(result);
        }
    }

    @Override
    public Cursor loadInBackground() {
        Cursor jsonCursor = null;

        try {
            InputStream fis = context.getResources().openRawResource(R.raw.sample_json);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String jsonString = reader.readLine();
            JsonArray jsonArray = new JsonParser().parse(jsonString).getAsJsonArray();
            jsonCursor = new JsonCursor(ExampleJsonCursorConstants.getExampleCursorColumns());
            ((JsonCursor)jsonCursor).addToCursor(jsonArray);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(JsonParseException e) {
            e.printStackTrace();
        }

        return jsonCursor;
    }
}

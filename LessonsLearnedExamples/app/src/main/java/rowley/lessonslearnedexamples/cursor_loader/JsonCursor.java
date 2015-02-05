package rowley.lessonslearnedexamples.cursor_loader;

import android.database.AbstractCursor;

import com.google.gson.JsonArray;

public class JsonCursor extends AbstractCursor {

    protected JsonArray jsonArray;
    protected String[] cols;

    public JsonCursor(String[] columns) {
        cols = columns;
    }

    @Override
    public int getColumnIndex(String columnName) {
        return super.getColumnIndex(columnName);

    }

    public void addToCursor(JsonArray json){
        if(jsonArray == null)
            jsonArray = new JsonArray();
        jsonArray.addAll(json);
    }

    public JsonArray getJsonArray(){
        return jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.size();
    }

    @Override
    public String[] getColumnNames() {
        return cols;
    }

    @Override
    public String getString(int column) {
        return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsString();
    }

    @Override
    public short getShort(int column) {
        return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsShort();
    }

    @Override
    public int getInt(int column) {
        if(cols[column].equals("_id"))
            return mPos;
        else
            return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsInt();
    }

    @Override
    public long getLong(int column) {
        if(cols[column].equals("_id"))
            return mPos;
        else
            return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsLong();
    }

    @Override
    public float getFloat(int column) {
        return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsFloat();
    }

    @Override
    public double getDouble(int column) {
        return jsonArray.get(mPos).getAsJsonObject().get(cols[column]).getAsDouble();
    }

    @Override
    public boolean isNull(int column) {
        return (jsonArray.get(mPos).getAsJsonObject().get(cols[column]).isJsonNull());
    }

}

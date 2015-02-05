package rowley.lessonslearnedexamples.cursor_loader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rowley.lessonslearnedexamples.R;

public class CursorExampleListCell extends RelativeLayout {
    private TextView name, count, position;

    public CursorExampleListCell(Context context) {
        super(context);
        init(context);
    }

    public CursorExampleListCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CursorExampleListCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.cursor_list_cell, this);
        name = (TextView)findViewById(R.id.name_value);
        count = (TextView)findViewById(R.id.count_value);
        position = (TextView)findViewById(R.id.position_value);
    }

    public void setData(String name, int count, int position) {
        this.name.setText(name);
        this.count.setText(String.valueOf(count));
        this.position.setText(String.valueOf(position));
    }
}

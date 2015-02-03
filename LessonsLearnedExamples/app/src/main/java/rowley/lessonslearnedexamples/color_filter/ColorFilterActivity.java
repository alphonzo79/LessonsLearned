package rowley.lessonslearnedexamples.color_filter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import rowley.lessonslearnedexamples.R;

public class ColorFilterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_color_filter);

        ((ImageView)findViewById(R.id.image_two)).setColorFilter(0xcafebabe);
        ((ImageView)findViewById(R.id.image_three)).setColorFilter(0xffff0000);
        ((ImageView)findViewById(R.id.image_four)).setColorFilter(0xff00ff00);
        ((ImageView)findViewById(R.id.image_five)).setColorFilter(0xff0000ff);
        ((ImageView)findViewById(R.id.image_six)).setColorFilter(0xffffff00);

        findViewById(R.id.image_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

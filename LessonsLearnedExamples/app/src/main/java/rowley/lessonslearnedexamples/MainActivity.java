package rowley.lessonslearnedexamples;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import rowley.lessonslearnedexamples.color_filter.ColorFilterActivity;
import rowley.lessonslearnedexamples.sql_performance.SqlPerformanceActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sql_performance_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SqlPerformanceActivity.class));
            }
        });

        findViewById(R.id.color_filter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorFilterActivity.class));
            }
        });
    }
}

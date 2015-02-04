package rowley.lessonslearnedexamples;

import android.widget.Toast;

public class MainActivity extends MainActivityParent {
    @Override
    protected void showBuildType() {
        Toast.makeText(this, R.string.release_notification, Toast.LENGTH_SHORT).show();
    }
}

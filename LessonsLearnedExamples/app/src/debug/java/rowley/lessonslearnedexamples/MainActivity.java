package rowley.lessonslearnedexamples;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends MainActivityParent {
    @Override
    protected void showBuildType() {
        new AlertDialog.Builder(this).setMessage(R.string.debug_notification).setCancelable(true).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}

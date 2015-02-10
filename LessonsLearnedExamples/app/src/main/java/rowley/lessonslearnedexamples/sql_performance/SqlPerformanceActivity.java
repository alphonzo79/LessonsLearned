package rowley.lessonslearnedexamples.sql_performance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import rowley.lessonslearnedexamples.R;

public class SqlPerformanceActivity extends Activity implements View.OnClickListener {
    SqlPerformanceDao dao;
    AlertDialog progress;
    long startTime;

    TextView elapsedTimeDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sql_performance);
        dao = new SqlPerformanceDao(this);

        elapsedTimeDisplay = (TextView) findViewById(R.id.time_value);

        progress = new ProgressDialog.Builder(this).setMessage(R.string.working).setCancelable(false).create();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.raw_100:
                doInserts(100, false, false);
                break;
            case R.id.raw_100_nt:
                doInserts(100, false, true);
                break;
            case R.id.cv_100:
                doInserts(100, true, false);
                break;
            case R.id.cv_100_worst:
                doInserts(100, true, true);
                break;
            case R.id.raw_1000:
                doInserts(1000, false, false);
                break;
            case R.id.raw_1000_nt:
                doInserts(1000, false, true);
                break;
            case R.id.cv_1000:
                doInserts(1000, true, false);
                break;
            case R.id.cv_1000_worst:
                doInserts(1000, true, true);
                break;
            case R.id.raw_10000:
                doInserts(10000, false, false);
                break;
            case R.id.raw_10000_nt:
                doInserts(10000, false, true);
                break;
            case R.id.cv_10000:
                doInserts(10000, true, false);
                break;
            case R.id.cv_10000_worst:
                doInserts(10000, true, true);
                break;
            case R.id.raw_100000:
                doInserts(100000, false, false);
                break;
            case R.id.raw_100000_nt:
                doInserts(100000, false, true);
                break;
            case R.id.cv_100000:
                doInserts(100000, true, false);
                break;
            case R.id.cv_100000_worst:
                doInserts(100000, true, true);
                break;
            case R.id.raw_1000000:
                doInserts(1000000, false, false);
                break;
            case R.id.raw_1000000_nt:
                doInserts(1000000, false, true);
                break;
            case R.id.cv_1000000:
                doInserts(1000000, true, false);
                break;
            case R.id.cv_1000000_worst:
                doInserts(1000000, true, true);
                break;
        }
    }

    private void doInserts(int count, boolean withCv, boolean withWorstPerf) {
        progress.show();
        elapsedTimeDisplay.setText("");
        dao.clearTable();
        startTime = System.currentTimeMillis();
        new AsyncTask<Object, Void, Void>() {
            @Override
            protected Void doInBackground(Object... params) {
                if((Boolean)params[1]) {
                    if((Boolean)params[2]) {
                        dao.addTestDataWithContentValuesWorstPerformance((Integer)params[0]);
                    } else {
                        dao.addTestDataWithContentValues((Integer) params[0]);
                    }
                } else {
                    if((Boolean)params[2]) {
                        dao.addTestDataWithSqlStatementNoTransaction((Integer) params[0]);
                    } else {
                        dao.addTestDataWithSqlStatement((Integer) params[0]);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                long endTime = System.currentTimeMillis();
                long diff = endTime - startTime;

                String message = "";
                if(diff > 10000) {
                    long diffInSeconds = diff / 1000;
                    if(diffInSeconds > 60) {
                        int minutes = (int)diffInSeconds / 60;
                        int seconds = (int)diffInSeconds % 60;
                        message = String.format(Locale.US, "%d minutes and %d seconds", minutes, seconds);
                    } else {
                        message = String.format(Locale.US, "%d seconds", diffInSeconds);
                    }
                } else {
                    message = String.format(Locale.US, "%,d milliseconds", diff);
                }

                elapsedTimeDisplay.setText(message);
                progress.dismiss();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, count, withCv, withWorstPerf);
    }
}

package ru.synergy.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {

    private TextView mInfoTextView;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    private ProgressBar mHorisontalProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        mInfoTextView = (TextView) findViewById(R.id.text_info);
        mStartButton = (Button) findViewById(R.id.button_start);
        mHorisontalProgressBar = findViewById(R.id.progressBar);

    }

    public void onClick(View v) {
        CurrierTask currierTask = new CurrierTask();
        currierTask.execute();
    }
    class CurrierTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            mInfoTextView.setText("Доставщик защель в Вас");
            mStartButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
           mInfoTextView.setText("Этаж" + values[0]);
           mHorisontalProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int numberOfFlors = 14;
                int counter = 0;
                for (int i = 0; i<numberOfFlors; i++){
                    getFloor(counter);
                    publishProgress(++counter);
                 }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void unused) {
           mInfoTextView.setText ("Тарабан по тормозам. Забери жратву кастомер");
           mStartButton.setVisibility(View.VISIBLE);
           mHorisontalProgressBar.setProgress(0);
        }

        private void getFloor(int counter) throws InterruptedException{
            TimeUnit.SECONDS.sleep(1);
        }
    }


}


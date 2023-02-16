package ru.synergy.asyncexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThreadExample extends AppCompatActivity {

    int mCounter;

    Handler handler = new Handler() {

        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            TextView mInfoTextView = (TextView) findViewById(R.id.textViewInfo);
            mInfoTextView.setText("Сегодня было гопников" + mCounter + "Челиков");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_example);


    }

    public void onClick(View v) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("SPARROWS", ("Сегодня пробегало гопоты" + mCounter++ + "Индивидов"));

                    handler.sendEmptyMessage(0);
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();


    }

}
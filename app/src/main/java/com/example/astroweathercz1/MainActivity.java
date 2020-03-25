package com.example.astroweathercz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Fragment1.IFragment1Listener, Fragment2.IFragment2AListener {
    private TextView textViewActualDate;
    private Date date;

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private void updateActualDateTextView() {
        date = Calendar.getInstance().getTime();
        textViewActualDate.setText(date.toString());
    }

    Thread threadDateAndTime = new Thread() {
        @Override
        public void run() {
            try {
                while (!threadDateAndTime.isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // update TextView here!
                            updateActualDateTextView();
                        }
                    });
                }
            } catch (InterruptedException e) {
                Log.e("Problem z watkiem", "Problem z watkiem od czasu/daty");
            }
        }
    };

    private void setTextViews() {
        textViewActualDate = findViewById(R.id.actual_date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTextViews();
        threadDateAndTime.start();

//        utworzenie instancji dla dwóch fragmentów

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

//        w miejsce fragment1_container -> patrz activity_main.xml wrzucamy obiekt fragment1, który utworzony jest z frament1_fragmnet.xml
//        reszta analogicznie
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment1_container, fragment1)
                .replace(R.id.fragment2_container, fragment2)
                .commit();
    }

    @Override
    public void onInput1Sent(CharSequence input) {
        fragment2.updateEditText(input);
    }

    @Override
    public void onInput2Sent(CharSequence input) {
        fragment1.updateEditText(input);
    }
}

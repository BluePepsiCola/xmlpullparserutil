package com.xaskysad.parser_use;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xaskysad.xmlparser_master.xmlparser_master.XmlParserUtil3;
import com.xaskysad.parser_use.domain.Persons;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Long>() {
                    @Override
                    protected Long doInBackground(Void... params) {

                        long mTime = 0 ;
                        Persons persons = null;
                        try {
                            mTime = SystemClock.currentThreadTimeMillis();

                            for (int i = 0; i < 100; i++) {

                                /**
                                 * if the Persons not the only tab, ex list<Persons>, should user a wrapper class to contain it and use
                                 */
                                persons = x3HandleFile("person.xml", Persons.class);
                            }

                            System.out.println(persons);

                            mTime = SystemClock.currentThreadTimeMillis()-mTime;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return mTime;
                    }

                    @Override
                    protected void onPostExecute(Long aVoid) {

                        Log.e("tag", "avg time---->" + (aVoid/100.0));

                    }
                }.execute();


            }
        });

    }

    public <T> T x3HandleFile(String file, Class<T> clazz) throws Exception {
        InputStream input = getAssets().open(file);
        T data = XmlParserUtil3.parser(input, "utf-8", clazz);

        return data;
    }

}

package net.devwiki.devlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.devwiki.log.DevLog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DevLog.d();
        DevLog.i();
        DevLog.d("hehe");
        DevLog.i();
        DevLog.d(TAG, "hehe");
    }
}

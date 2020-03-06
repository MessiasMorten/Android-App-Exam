package com.example.lab61;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        LinearLayout body = findViewById(R.id.body);
        for (int i = 0; i < 12; i++) {
            body.addView(new QuestionView(this) {});
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

//            case R.id.action_end:
//                finish();
//                break;
        }
        return true;
    }

    @Override
    protected void onStop() {
        this.writeToSharedPreference();
        super.onStop();
    }

    private void writeToSharedPreference() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean saveOnExit = sharedPreferences.getBoolean("PREF_SAVE_ON_EXIT", false);
        if (saveOnExit) {
//            EditText editText = (EditText)findViewById(R.id.editText1);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("MY_TEXT", editText.getText().toString());
//            editor.commit();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        SharedPreferences myprefs = PreferenceManager.getDefaultSharedPreferences(MyPreferenceTestActivity.this);
    }

}

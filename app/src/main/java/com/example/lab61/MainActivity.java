package com.example.lab61;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.EditTextPreference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab61.adapter.QuestionAdapter;
import com.example.lab61.subclasses.Quiz;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    DataViewModel viewModel;
    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    Button btn_submit;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        initViews();

        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        viewModel.downloadQuiz(MainActivity.this, sharedPreferences);
        viewModel.getUsersData().observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                Log.d(TAG, "data received" + quizzes.toString());
                questionAdapter = new QuestionAdapter(MainActivity.this, quizzes);
                recyclerView.setAdapter(questionAdapter);
                btn_submit.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_question);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setVisibility(View.INVISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

    @Override
    protected void onStop() {
        this.writeToSharedPreference();
        super.onStop();
    }

    private void writeToSharedPreference(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        EditText quantity = findViewById(R.id.quantity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("QUANTITY", quantity.getText().toString());
        editor.apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences myprefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        viewModel.downloadQuiz(MainActivity.this, myprefs);
    }

    public void showResult(View view) {
        Intent intent=new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(intent);
    }
}

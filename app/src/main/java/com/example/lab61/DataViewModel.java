package com.example.lab61;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab61.subclasses.Quiz;

import java.util.List;

public class DataViewModel extends ViewModel {

    private Repository mRepository;
    private MutableLiveData<List<Quiz>> mQuizData;
    private MutableLiveData<String> mErrorMessage;

    public DataViewModel() {
        mRepository = new Repository();
        mQuizData = mRepository.getQuiz();
    }

    public MutableLiveData<List<Quiz>> getUsersData() {return mQuizData;}
    public MutableLiveData<String> getErrorMessage() {return mErrorMessage;}

    public void downloadQuiz(Context context, SharedPreferences preferences) {
        mRepository.downloadQuizByJSON(context, preferences);
    }
}

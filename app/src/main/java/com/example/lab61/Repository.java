package com.example.lab61;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.lab61.subclasses.Quiz;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private RequestQueue queue;
    private MutableLiveData<List<Quiz>> quiz = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MutableLiveData<List<Quiz>>getQuiz() {
        return quiz;
    }

    public MutableLiveData<String>getErrorMessage() {
        return errorMessage;
    }


    public void downloadQuizByJSON(Context context, String jsonurl) {

        queue = MySingletonQueue.getInstance(context).getRequestQueue();

        //Download
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonurl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Gson gson = new Gson();

                    ArrayList<Quiz> tmpList = new ArrayList<>();
                    for (int i=0; i <response.length(); i++){
                        JSONObject userJSON = response.getJSONObject(i);
                        Quiz quiz = gson.fromJson(userJSON.toString(), Quiz.class);
                        tmpList.add(quiz);
                    }
                    quiz.postValue(tmpList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                errorMessage.postValue(error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

}

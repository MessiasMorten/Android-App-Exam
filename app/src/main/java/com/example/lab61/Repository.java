package com.example.lab61;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab61.subclasses.Quiz;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String TAG = "Repository";
    private RequestQueue queue;
    private MutableLiveData<List<Quiz>> quiz = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    static String URL = "https://opentdb.com/api.php?amount=10&category=12&difficulty=easy&type=multiple";


//   public Repository(Application application) {
//        downloadQuizByJSON(application, URL);
//    }

    public MutableLiveData<List<Quiz>> getQuiz() {
        return quiz;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }


    void downloadQuizByJSON(Context context) {

        queue = MySingletonQueue.getInstance(context).getRequestQueue();

        //Download
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", "got response"+response.toString());

                        try {
                            Gson gson = new Gson();
                            ArrayList<Quiz> tmpList = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Quiz quiz = gson.fromJson(jsonObject.toString(), Quiz.class);

                                Log.d(TAG, "onResponse: adding to the temp" + quiz.toString());
                                tmpList.add(quiz);

                            }

                            quiz.setValue(tmpList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            errorMessage.setValue(e.getMessage());
                            Log.d(TAG, "onResponse: error in converting the json");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
                errorMessage.setValue(error.getMessage());
                // hide the progress dialog
            }
        });

        queue.add(jsonObjReq);
    }

}

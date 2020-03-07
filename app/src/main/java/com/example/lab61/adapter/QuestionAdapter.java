package com.example.lab61.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab61.R;
import com.example.lab61.subclasses.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private static final String TAG = "QuestionAdapter";
    private List<Quiz> quizzes = new ArrayList<>();
    private Context context;

    public QuestionAdapter(Context context, List<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (quizzes.get(position) != null) {
            try {
                holder.tv_question_num.setText(String.format("Question #%s :", String.valueOf(position)));
                holder.tv_question_text.setText(quizzes.get(position).getQuestion());
                List<String> questions = new ArrayList<>();
                questions.add(quizzes.get(position).getCorrectAnswer());
                questions.addAll(quizzes.get(position).getIncorrectAnswers());
                Collections.shuffle(questions);


                holder.option1.setText(questions.get(0));
                holder.option2.setText(questions.get(1));
                holder.option3.setText(questions.get(2));
                holder.option4.setText(questions.get(3));


            } catch (Exception e) {
                Log.d(TAG, "error occurred ");
            }

        }


    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_question_num, tv_question_text;
        RadioButton option1, option2, option3, option4;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_question_num = itemView.findViewById(R.id.question_num);
            tv_question_text = itemView.findViewById(R.id.question_text);
            option1 = itemView.findViewById(R.id.radioButton);
            option2 = itemView.findViewById(R.id.radioButton2);
            option3 = itemView.findViewById(R.id.radioButton3);
            option4 = itemView.findViewById(R.id.radioButton4);
        }
    }
}

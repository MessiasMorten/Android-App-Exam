package com.example.lab61.subclasses;

public class Quiz {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private IncorrectAnswers incorrect_aswers;

    public Quiz(String category, String type, String difficulty, String question, String correct_answer, IncorrectAnswers incorrect_aswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_aswers = incorrect_aswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public IncorrectAnswers getIncorrect_aswers() {
        return incorrect_aswers;
    }

    public void setIncorrect_aswers(IncorrectAnswers incorrect_aswers) {
        this.incorrect_aswers = incorrect_aswers;
    }
}

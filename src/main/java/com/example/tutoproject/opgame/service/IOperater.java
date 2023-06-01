package com.example.tutoproject.opgame.service;

public interface IOperater {
    void setA(int a);

    void setB(int b);

    void generateQuestion(int max);

    String getQuestionMsg();

    boolean isEquals(int answer);
}

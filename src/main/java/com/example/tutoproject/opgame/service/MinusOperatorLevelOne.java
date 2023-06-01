package com.example.tutoproject.opgame.service;

public class MinusOperatorLevelOne implements IOperater{
    int a;
    int b;
    @Override
    public void setA(int a) {
        this.a= a;

    }

    @Override
    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void generateQuestion(int max) {
        a = (int) (Math.random() * max);
        b = (int) (Math.random() * max);
        if (a < b) {
            int tmp = b;
            b = a;
            a = tmp;
        }

    }

    @Override
    public String getQuestionMsg() {
        return String.format("%d - %d = ", a, b);
    }

    @Override
    public boolean isEquals(int answer) {
        return (a - b) == answer;
    }
}

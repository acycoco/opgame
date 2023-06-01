package com.example.tutoproject.opgame.service;

public class OpGame {

    int remainingAnswers;
//    int a, b;
    String cheeringUpMsg = "한번 더 해보자";
    IOperater op;

    public OpGame(IOperater op) {
        this.op = op;
    }

    public void makeQuestion() {  //테스팅하는 함수
        remainingAnswers = 3;
//        a = 1;
//        b = 1;

        op.setA(1);
        op.setB(1);
    }

    public void makeQuestion(int max) {
/*      a = (int) (Math.random() * max);
        b = (int) (Math.random() * (max - a));*/
        op.generateQuestion(max);
    }

    public String getQuestion() {
/*        return String.format("%d + %d = ", a, b);*/
        return op.getQuestionMsg();
    }

    public int getRemainingAnswers() {
        return remainingAnswers;
    }

    public boolean isAnswer(int answer) {
        remainingAnswers--;
//        return (a + b) == answer;
        return op.isEquals(answer);
    }


    public String getCheeringUpMsg() {
        return cheeringUpMsg;
    }
}

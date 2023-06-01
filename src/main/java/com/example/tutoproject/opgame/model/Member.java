package com.example.tutoproject.opgame.model;

public class Member {
    String id;
    boolean isLogined;

    public Member(String id, boolean isLogined) {
        this.id = id;
        this.isLogined = isLogined;
    }

    public String getID() {
        return id;
    }

    public boolean isLogined() {
        return isLogined;
    }
}

package com.gnayuil.acost.data.bean;

public class InfoItem {
    private boolean check;
    private String console = "0";
    private String lambda = "0";
    private String title;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getLambda() {
        return lambda;
    }

    public void setLambda(String lambda) {
        this.lambda = lambda;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

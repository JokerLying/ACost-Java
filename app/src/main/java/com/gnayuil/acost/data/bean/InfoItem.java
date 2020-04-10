package com.gnayuil.acost.data.bean;

public class InfoItem {
    private boolean check;
    private String console;
    private String lambda;
    private String title;
    private ItemType type;

    public enum ItemType {
        NORMAL, ADD
    }

    public InfoItem() {
        check = false;
        console = "0";
        lambda = "0";
        type = ItemType.NORMAL;
    }

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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}

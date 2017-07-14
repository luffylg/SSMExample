package com.unionpay.taskmonitor.bean;

/**
 * Created by luffylg on 2017/6/30.
 */
public class SpiderStatus {
    private int position = 0;
    private int isalive = 1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsalive() {
        return isalive;
    }

    public void setIsalive(int isalive) {
        this.isalive = isalive;
    }
}

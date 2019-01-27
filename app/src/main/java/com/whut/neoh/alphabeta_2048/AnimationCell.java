package com.whut.neoh.alphabeta_2048;

//添加了动画效果的间室
public class AnimationCell extends Cell {
    private int animationType;
    private long animationTime;
    private long delayTime;
    public int[] extras;

    private long timeElapsed;       //花费的时间

    public AnimationCell(int y, int x, int animationType, long animationTime, long delayTime, int[] extras) {
        super(y, x);
        this.animationType = animationType;
        this.animationTime = animationTime;
        this.delayTime = delayTime;
        this.extras = extras;
    }

    public int getAnimationType() {
        return animationType;
    }

    public void tick(long timeElapsed) {
        this.timeElapsed = this.timeElapsed + timeElapsed;
    }

    public boolean animateionDone() {
        return timeElapsed > animationTime + delayTime;
    }

    public double getPercentageDone() {
        return Math.max(0, 1.0 * (timeElapsed - delayTime) / animationTime);
    }

    public boolean isActive() {
        return (timeElapsed >= delayTime);
    }
}

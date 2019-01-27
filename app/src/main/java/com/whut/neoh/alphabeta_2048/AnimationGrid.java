package com.whut.neoh.alphabeta_2048;

import java.util.ArrayList;

public class AnimationGrid {
    public final ArrayList<AnimationCell> globalAnimation = new ArrayList<>();
    private final ArrayList<AnimationCell>[][] field;

    private int activeAnimations = 0; //活跃的动画间室数量
    private boolean oneMoreFrame = false;

    public AnimationGrid(int y, int x) {
        field = new ArrayList[y][x];
        for (int ty = 0; ty < y; ty++)
            for (int tx = 0; tx < x; tx++)
                field[y][x] = new ArrayList<>();
    }

    public void startAnimation(int y, int x, int animationType, long animationTime, long delayTime, int[] extras) {
        AnimationCell AC2Add = new AnimationCell(y, x, animationType, animationTime, delayTime, extras);
        if (x == -1 || y == -1)
            globalAnimation.add(AC2Add);
        else
            field[y][x].add(AC2Add);
        activeAnimations++;
    }

    public void tickAll(long timeElapsed) {
        ArrayList<AnimationCell> cancelledAnimations = new ArrayList<>();
        for (AnimationCell animationCell : globalAnimation) {
            animationCell.tick(timeElapsed);
            if (animationCell.animateionDone()) {
                cancelledAnimations.add(animationCell);
                activeAnimations--;
            }
        }

        for (ArrayList<AnimationCell>[] arrayLists : field) {
            for (ArrayList<AnimationCell> arrayList : arrayLists) {
                for (AnimationCell animationCell : arrayList) {
                    animationCell.tick(timeElapsed);
                    if (animationCell.animateionDone()) {
                        cancelledAnimations.add(animationCell);
                        activeAnimations--;
                    }
                }
            }
        }

        for (AnimationCell animationCell : cancelledAnimations) {
            cancelAnimation(animationCell);
        }
    }


    public boolean isAnimationActive(){
        if(activeAnimations!=0){
            oneMoreFrame =true;
            return true;
        }else if(oneMoreFrame){
            oneMoreFrame =false;
            return true;
        }else
            return false;

    }

    public ArrayList<AnimationCell> getAnimationgCell(int y,int x){
        return field[y][x];
    }

    public void cancelAnimations() {
        for (ArrayList<AnimationCell>[] arrayLists : field)
            for (ArrayList<AnimationCell> arrayList : arrayLists)
                arrayList.clear();
        globalAnimation.clear();
        activeAnimations = 0;

    }

    private void cancelAnimation(AnimationCell animationCell) {
        if (animationCell.getY() == -1 && animationCell.getX() == -1)
            globalAnimation.remove(animationCell);
        else
            field[animationCell.getY()][animationCell.getX()].remove(animationCell);
    }
}

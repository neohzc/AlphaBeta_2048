package com.whut.neoh.alphabeta_2048;

import android.content.Context;

public class MainGame {

    private final Context mContext;
    private final MainView mView;

    private static final int startingMaxValue = 2048;   //开始时默认最大值为2048
    private static int endingMaxValue;


    //一些参数常量
    public static final int SPAWN_ANIMATION = -1;   //选择生成动画
    public static final int MOVE_ANIMATION = 0;     //选择移动动画
    public static final int MERGE_ANIMATION = 1;    //选择合并动画
    public static final int FADE_GLOBAL_ANIMATION = 0;

    private static final long MOVE_ANIMATION_TIME = MainView.BASE_ANIMATION_TIME;   //移动动画时间长度
    private static final long SPAWN_ANIMATION_TIME = MainView.BASE_ANIMATION_TIME;  //生成动画时间长度
    private static final long NOTIFICATION_DELAY_TIME = MOVE_ANIMATION_TIME + SPAWN_ANIMATION_TIME; //提示延迟使劲
    private static final long NOTIFICATION_ANIMATION_TIME = MainView.BASE_ANIMATION_TIME * 5;       //提示动画时间


    //Odd state = game is not active
    //Even state = game is active
    //Win state = active state + 1
    private static final int GAME_WIN = 1;
    private static final int GAME_LOST = -1;
    private static final int GAME_NORMAL = 0;

    public int gameState = GAME_NORMAL;
    public int lastGameState = GAME_NORMAL;

    private int bufferGameState = GAME_NORMAL;
    private static final int GAME_ENDLESS = 2;
    private static final int GAME_ENDLESS_WON = 3;
    private static final String HIGH_SCORE = "high score";
    private static final String FIRST_RUN = "first run";



    public MainGame(Context context, MainView view) {
        mContext = context;
        mView = view;
        endingMaxValue = (int) Math.pow(2, view.numCellTypes - 1);
    }



}

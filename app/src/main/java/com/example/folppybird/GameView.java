package com.example.folppybird;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    public GameView(Context context)
    {
        super(context);
        initView();
    }

    private void initView()
    {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!gameThread.isRunning())
        {
            gameThread = new GameThread(holder);
            gameThread.start();
        }
        else
        {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if (gameThread.isRunning())
        {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry)
            {
                try {

                    gameThread.join();
                    retry = false;
                }
                catch (InterruptedException e)
                {

                }
            }
        }
    }
}

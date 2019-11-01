package com.example.folppybird;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
    SurfaceHolder surfaceHolder; //Surface Holder object reference
    boolean isRunning;// Flag to detect whether is thread is running or not
    long startTime, loopTime;//loop start time and loop duration
    long DELAY = 33;//DELAY in milliseconds between screen refreshes


    public GameThread(SurfaceHolder surfaceHolder)
    {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }


    @Override
    public void run()
    {
        //looping until the boolean is false
        while(isRunning)
        {
            startTime = SystemClock.uptimeMillis();
            //looking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null) ;
            if (canvas != null)
            {
                synchronized (surfaceHolder)
                {
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            //loopTime
            loopTime = SystemClock.uptimeMillis() - startTime;
            //Pausing here to make sure we update the right amount per second

            if (loopTime < DELAY)
            {
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch (InterruptedException e)
                {
                    Log.e("Interrupted","Interrupted while sleeping");
                }
            }
        }
    }


    public boolean isRunning()
    {
        return isRunning;
    }


    public void setIsRunning(boolean state)
    {
        isRunning = state;
    }

}

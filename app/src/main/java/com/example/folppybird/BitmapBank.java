package com.example.folppybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank
{
    Bitmap background;

    public BitmapBank(Resources res)
    {
        background = BitmapFactory.decodeResource(res, R.drawable.gamebk);

        background = scaleImage(background);
    }

    //Return backgroud bitmap

    public Bitmap getBackground()
    {
        return background;
    }

    //Return backgroud Width

    public int getBackgroundWidth()
    {
        return background.getWidth();
    }

    //Return backgroud Height

    public int getBackgroundHeight()
    {
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap)
    {
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }



}

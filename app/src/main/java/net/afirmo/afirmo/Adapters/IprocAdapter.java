package net.afirmo.afirmo.Adapters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import net.afirmo.afirmo.Interfaces.IprocInterface;

/**
 * Created by vic2_ on 29/11/2016.
 */
public class IprocAdapter implements IprocInterface {
    @Override
    public Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    @Override
    public Bitmap toBinary(Bitmap bmpOriginal) {
        int width, height, threshold;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        threshold = 127;
        final Bitmap bmpBinary = null;

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                int pixel = bmpOriginal.getPixel(x, y);

                //get grayscale value
                int gray = (int)(pixel & 0xFF);

                //get binary value
                if(gray < threshold){
                    bmpBinary.setPixel(x, y, 0);
                } else{
                    bmpBinary.setPixel(x, y, 255);
                }

            }
        }
        return bmpBinary;
    }
}

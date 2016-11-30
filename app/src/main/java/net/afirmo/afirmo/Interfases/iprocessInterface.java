package net.afirmo.afirmo.Interfases;

import android.graphics.Bitmap;

/**
 * Created by vic2_ on 30/11/2016.
 */
public interface iprocessInterface {
    public Bitmap toGrayscale(Bitmap bmpOriginal);
    public Bitmap toBinary(Bitmap bmpOriginal);
}

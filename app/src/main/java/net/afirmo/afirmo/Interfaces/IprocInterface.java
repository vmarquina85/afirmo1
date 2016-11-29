package net.afirmo.afirmo.Interfaces;

import android.graphics.Bitmap;

/**
 * Created by vic2_ on 29/11/2016.
 */
public interface IprocInterface {
    public Bitmap toGrayscale(Bitmap bmpOriginal);

    public Bitmap toBinary(Bitmap bmpOriginal);

}

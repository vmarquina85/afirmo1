package net.afirmo.afirmo.Interfases;

import android.graphics.Bitmap;

/**
 * Created by vic2_ on 30/11/2016.
 */
public interface iprocessInterface {
    public Bitmap toGrayscale(Bitmap bmpOriginal);
    public Bitmap toBinary(Bitmap bmpOriginal);
    public int[][] doZhangSuenThinning(int[][] givenImage, boolean changeGivenImage);

    public int getA(int[][] binaryImage, int y, int x);
    public int getB(int[][] binaryImage, int y, int x);
    public int [][] createImageData(Bitmap bin);
    public Bitmap UpdateBufferedImage(int[][] binaryImage,Bitmap bin, int angulo);
    public void aplicarConvolucion(Bitmap Esqueletizado,int[][] Kernel);
}

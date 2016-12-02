package net.afirmo.afirmo.Adapters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import net.afirmo.afirmo.Interfases.iprocessInterface;
import net.afirmo.afirmo.clases.Point;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vic2_ on 30/11/2016.
 */
public class iprocessAdapter implements iprocessInterface {
    public static final int NO_CONV = 0;
    public static final int TIPO_CONV_0 = 1;
    public static final int TIPO_CONV_45 = 2;
    public static final int TIPO_CONV_90 = 3;
    public static final int TIPO_CONV_135 = 4;
    public static final int[][] MATRIZ0 = {{1, 1}, {0, 0}};
    public static final int[][] MATRIZ45 = {{0, 1}, {1, 0}};
    public static final int[][] MATRIZ90 = {{1, 0}, {1, 0}};
    public static final int[][] MATRIZ135 = {{1, 0}, {0, 1}};

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
        Bitmap bmpBinary = Bitmap.createBitmap(bmpOriginal);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                int pixel = bmpOriginal.getPixel(x, y);
                int red = Color.red(pixel);

                //get binary value
                if(red < threshold){
                    bmpBinary.setPixel(x, y, 0xFF000000);
                } else{
                    bmpBinary.setPixel(x, y, 0xFFFFFFFF);
                }

            }
        }
        return bmpBinary;
    }

    @Override
    public int[][] doZhangSuenThinning(int[][] givenImage, boolean changeGivenImage) {
        int[][] binaryImage;
        if (changeGivenImage) {
            binaryImage = givenImage;
        } else {
            binaryImage = givenImage.clone();
        }
        int a, b;
        List<Point> pointsToChange = new LinkedList();
        boolean hasChange;
        do {
            hasChange = false;
            for (int y = 1; y + 1 < binaryImage.length; y++) {
                for (int x = 1; x + 1 < binaryImage[y].length; x++) {
                    a = getA(binaryImage, y, x);
                    b = getB(binaryImage, y, x);
                    if (binaryImage[y][x] == 1 && 2 <= b && b <= 6 && a == 1
                            && (binaryImage[y - 1][x] * binaryImage[y][x + 1] * binaryImage[y + 1][x] == 0)
                            && (binaryImage[y][x + 1] * binaryImage[y + 1][x] * binaryImage[y][x - 1] == 0)) {
                        pointsToChange.add(new Point(x, y));
//binaryImage[y][x] = 0;
                        hasChange = true;
                    }
                }
            }
            for (Point point : pointsToChange) {
                binaryImage[point.getY()][point.getX()] = 0;
            }
            pointsToChange.clear();
            for (int y = 1; y + 1 < binaryImage.length; y++) {
                for (int x = 1; x + 1 < binaryImage[y].length; x++) {
                    a = getA(binaryImage, y, x);
                    b = getB(binaryImage, y, x);
                    if (binaryImage[y][x] == 1 && 2 <= b && b <= 6 && a == 1
                            && (binaryImage[y - 1][x] * binaryImage[y][x + 1] * binaryImage[y][x - 1] == 0)
                            && (binaryImage[y - 1][x] * binaryImage[y + 1][x] * binaryImage[y][x - 1] == 0)) {
                        pointsToChange.add(new Point(x, y));
                        hasChange = true;
                    }
                }
            }
            for (Point point : pointsToChange) {
                binaryImage[point.getY()][point.getX()] = 0;
            }
            pointsToChange.clear();
        } while (hasChange);
        return binaryImage;
    }

    @Override
    public int getA(int[][] binaryImage, int y, int x) {
        int count = 0;
//p2 p3
        if (y - 1 >= 0 && x + 1 < binaryImage[y].length && binaryImage[y - 1][x] == 0 && binaryImage[y - 1][x + 1] == 1) {
            count++;
        }
//p3 p4
        if (y - 1 >= 0 && x + 1 < binaryImage[y].length && binaryImage[y - 1][x + 1] == 0 && binaryImage[y][x + 1] == 1) {
            count++;
        }
//p4 p5
        if (y + 1 < binaryImage.length && x + 1 < binaryImage[y].length && binaryImage[y][x + 1] == 0 && binaryImage[y + 1][x + 1] == 1) {
            count++;
        }
//p5 p6
        if (y + 1 < binaryImage.length && x + 1 < binaryImage[y].length && binaryImage[y + 1][x + 1] == 0 && binaryImage[y + 1][x] == 1) {
            count++;
        }
//p6 p7
        if (y + 1 < binaryImage.length && x - 1 >= 0 && binaryImage[y + 1][x] == 0 && binaryImage[y + 1][x - 1] == 1) {
            count++;
        }
//p7 p8
        if (y + 1 < binaryImage.length && x - 1 >= 0 && binaryImage[y + 1][x - 1] == 0 && binaryImage[y][x - 1] == 1) {
            count++;
        }
//p8 p9
        if (y - 1 >= 0 && x - 1 >= 0 && binaryImage[y][x - 1] == 0 && binaryImage[y - 1][x - 1] == 1) {
            count++;
        }
//p9 p2
        if (y - 1 >= 0 && x - 1 >= 0 && binaryImage[y - 1][x - 1] == 0 && binaryImage[y - 1][x] == 1) {
            count++;
        }
        return count;
    }

    @Override
    public int getB(int[][] binaryImage, int y, int x) {
        return binaryImage[y - 1][x] + binaryImage[y - 1][x + 1] + binaryImage[y][x + 1]
                + binaryImage[y + 1][x + 1] + binaryImage[y + 1][x] + binaryImage[y + 1][x - 1]
                + binaryImage[y][x - 1] + binaryImage[y - 1][x - 1];
    }

    @Override
    public int[][] createImageData(Bitmap bin) {
        int[][] imageData = new int[bin.getHeight()][bin.getWidth()];
        Color c;
        for (int y = 0; y < imageData.length; y++) {
            for (int x = 0; x < imageData[y].length; x++) {

                if (bin.getPixel(x, y) == Color.BLACK) {
                    imageData[y][x] = 1;
                } else if (bin.getPixel(x, y) == Color.WHITE) {
                    imageData[y][x] = 0;

                }
            }
        }
        return imageData;
    }

    @Override
    public Bitmap UpdateBufferedImage(int[][] binaryImage, Bitmap bin, int angulo) {
        for (int y = 0; y < binaryImage.length; y++) {

            for (int x = 0; x < binaryImage[y].length; x++) {
                if (angulo == iprocessAdapter.TIPO_CONV_0) {
                    if (binaryImage[y][x] == 1) {
                        bin.setPixel(x, y, Color.BLACK);

                    } else if (binaryImage[y][x] == 0) {
                        bin.setPixel(x, y, Color.WHITE);
                    } else {
                        bin.setPixel(x, y, Color.CYAN);
                    }
                }
                if (angulo == iprocessAdapter.TIPO_CONV_90) {
                    if (binaryImage[y][x] == 1) {
                        bin.setPixel(x, y, Color.BLACK);

                    } else if (binaryImage[y][x] == 0) {
                        bin.setPixel(x, y, Color.WHITE);
                    } else {
                        bin.setPixel(x, y, Color.BLUE);
                    }
                }
                if (angulo == iprocessAdapter.TIPO_CONV_45) {
                    if (binaryImage[y][x] == 1) {
                        bin.setPixel(x, y, Color.BLACK);

                    } else if (binaryImage[y][x] == 0) {
                        bin.setPixel(x, y, Color.WHITE);
                    } else {
                        bin.setPixel(x, y, Color.RED);
                    }
                }
                if (angulo == iprocessAdapter.TIPO_CONV_135) {
                    if (binaryImage[y][x] == 1) {
                        bin.setPixel(x, y, Color.BLACK);

                    } else if (binaryImage[y][x] == 0) {
                        bin.setPixel(x, y, Color.WHITE);
                    } else {
                        bin.setPixel(x, y, Color.GREEN);
                    }
                }
                if (angulo == iprocessAdapter.NO_CONV) {
                    if (binaryImage[y][x] == 1) {
                        bin.setPixel(x, y, Color.BLACK);

                    } else if (binaryImage[y][x] == 0) {
                        bin.setPixel(x, y, Color.WHITE);
                    }
                }

            }
        }
        return bin;
    }

    @Override
    public void aplicarConvolucion(Bitmap Esqueletizado, int[][] Kernel) {
        iprocessAdapter adapter = new iprocessAdapter();
        int[][] ImagenMatriz = adapter.createImageData(Esqueletizado);

        int fila = ImagenMatriz.length;
        int col = ImagenMatriz[0].length;

        int[][] resultante = new int[fila][col];

        for (int i = 0; i < (ImagenMatriz.length) - 1; i++) {
            for (int j = 0; j < (ImagenMatriz[i].length) - 1; j++) {
                int resultPixel = ImagenMatriz[i][j] * Kernel[0][0] + ImagenMatriz[i][j + 1] * Kernel[0][1] + ImagenMatriz[i + 1][j] * Kernel[1][0] + ImagenMatriz[i + 1][j + 1] * Kernel[1][1];

                resultante[i][j] = resultPixel;

            }
        }


        if (Kernel == iprocessAdapter.MATRIZ0) {
            Bitmap resultado = adapter.UpdateBufferedImage(resultante, Esqueletizado, iprocessAdapter.TIPO_CONV_0);
            File img4 = new File("d:/firma/conv0.jpg");

        }
        if (Kernel == iprocessAdapter.MATRIZ135) {
            Bitmap resultado = adapter.UpdateBufferedImage(resultante, Esqueletizado, iprocessAdapter.TIPO_CONV_135);
            File img4 = new File("d:/firma/conv135.jpg");

        }
        if (Kernel == iprocessAdapter.MATRIZ45) {
            Bitmap resultado = adapter.UpdateBufferedImage(resultante, Esqueletizado, iprocessAdapter.TIPO_CONV_45);
            File img4 = new File("d:/firma/conv45.jpg");

        }
        if (Kernel == iprocessAdapter.MATRIZ90) {
            Bitmap resultado = adapter.UpdateBufferedImage(resultante, Esqueletizado, iprocessAdapter.TIPO_CONV_90);
            File img4 = new File("d:/firma/conv90.jpg");

        }
    }
}

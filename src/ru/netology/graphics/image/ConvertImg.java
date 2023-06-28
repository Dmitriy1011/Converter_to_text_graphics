package ru.netology.graphics.image;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.Arrays;

public class ConvertImg implements TextGraphicsConverter {

    protected TextColorSchema colorScheme = new ColorScheme();

    protected double maxRatio;
    protected int maxWidth;
    protected int maxHeight;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));


        double height = img.getHeight();
        double width = img.getWidth();

        double ratio = height / width;
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("ratio: " + ratio);

        if ((ratio) > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        double wDifference = width / maxWidth;
        double hDifference = height / maxHeight;

        System.out.println("wDifference: " + wDifference);
        System.out.println("hDifference: " + hDifference);

        int newWidth = maxWidth;
        int newHeight = maxHeight;

        if(width > maxWidth && height > maxHeight) {
            if(wDifference == hDifference) {
                newWidth = (int) Math.round(width / wDifference);
                newHeight = (int) Math.round(height / hDifference);
            } else if (wDifference > hDifference) {
                newWidth = (int) Math.round(width / wDifference);
                newHeight = (int) Math.round(height / wDifference);
            } else if (wDifference < hDifference) {
                newWidth = (int) Math.round(width / hDifference);
                newHeight = (int) Math.round(height / hDifference);
            }
        } else if(width > maxWidth && height <= maxHeight) {
                newWidth = (int) Math.ceil(width / wDifference);
        } else if(height > maxHeight && width <= maxWidth) {
                newHeight = (int) Math.ceil(height / hDifference);
        } else {
            newWidth = (int) width;
            newHeight = (int) height;
        }



        System.out.println("maxW: " + maxWidth + " maxH: " + maxHeight + " newW: " + newWidth + " newH: " + newHeight);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

//        ImageIO.write(imageObject, "png", new File("out.png"));

        WritableRaster bwRaster = bwImg.getRaster();

        char[][] symbols = new char[newHeight][newWidth];

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int color = bwRaster.getPixel(j, i, new int[3])[0];
                char c = colorScheme.convert(color);

                symbols[i][j] = c;

            }
        }

        for (int m = 0; m < symbols.length; m++) {
            char[] arr = symbols[m];
            for (int n = 0; n < arr.length; n++) {
                String symbolToStr = Character.toString(arr[n]);
                String twiceSymbol = symbolToStr + symbolToStr + symbolToStr;
                str.append(twiceSymbol);
            }
            str.append('\n');
        }

        return str.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }


    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.colorScheme = schema;
    }
}

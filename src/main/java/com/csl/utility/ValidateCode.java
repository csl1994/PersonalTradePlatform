package com.csl.utility;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.Date;
import java.util.Random;

/**
 * Created by csl on 2017/4/6.
 */
public class ValidateCode {
    private static ValidateCode ourInstance;

    private static String validateCode;

    private static final String RAND_STRING = "0123456789abcdefghijklmnopqrstuvwxyz";

    private static final int WIDTH = 100;//图片宽

    private static final int HEIGHT = 35;//图片高

    private static final int LINE_SIZE = 40;//干扰线数量

    private static final int CODE_COUNT = 4;//随机产生字符数量

    private static final float NOISE_RATE = 0.05f;//噪点率

    private static final int IMAGE_COUNT = 20;

    private static Random random = new Random();

    private ValidateCode() {
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = random.nextInt(Math.abs(bc - fc));
        int g = random.nextInt(Math.abs(bc - fc));
        int b = random.nextInt(Math.abs(bc - fc));
        return new Color(r, g, b);
    }

    private static void drawContext(Graphics2D graphics) {
        String result = "";
        for (int i = 0; i < CODE_COUNT; i++) {
            graphics.setFont(new Font("Algerian", Font.ITALIC, 30));
            graphics.setColor(getRandColor(random.nextInt(110), random.nextInt(255)));
            String item = String.valueOf(RAND_STRING.charAt(random.nextInt(RAND_STRING.length())));
            graphics.translate(random.nextInt(3), random.nextInt(3));
            graphics.drawString(item, 20 * i, 25);
            result += item;
        }
        validateCode = result;
    }

    private static void drawLine(Graphics2D graphics) {
        for (int i = 0; i < LINE_SIZE; i++) {
            int x1 = random.nextInt(WIDTH - 1);
            int y1 = random.nextInt(HEIGHT - 1);
            int x2 = random.nextInt(6) + 1;
            int y2 = random.nextInt(12) + 1;
            graphics.setColor(getRandColor(200, 255));
            graphics.drawLine(x1, y1, x1 + x2, y1 + y2 + 60);
        }
    }

    private static void drawNoise(BufferedImage image) {
        int area = (int) (NOISE_RATE * WIDTH * HEIGHT);
        for (int i = 0; i < area; i++) {
            image.setRGB(random.nextInt(WIDTH), random.nextInt(HEIGHT), getRandomIntColor());
        }
    }

    private static int getRandomIntColor() {
        int[] rgb = new int[3];
        int color = 0;
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    public static BufferedImage getImage() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Algerian", Font.ITALIC, 30));
        graphics.fillRect(0, 2, WIDTH, HEIGHT - 4);
        drawLine(graphics);
        drawNoise(image);
        drawContext(graphics);
        graphics.dispose();
        return image;
    }

    public static String generateImages() {
        BufferedImage image = getImage();
        long name = new Date().getTime();
        try {
            ImageIO.write(image, "PNG", new File("D:/project/PersonalTradePlatform/src/main/webapp/validateCode/" + name + ".PNG"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return name + "";
    }

    public static ValidateCode getInstance() {
        if (ourInstance == null) {
            synchronized (ValidateCode.class) {
                if (ourInstance == null) {
                    ourInstance = new ValidateCode();
                }
            }
        }
        return ourInstance;
    }

    public static String getValidateCode() {
        if (validateCode != null && validateCode != "") {
            return validateCode;
        }
        return "";
    }
}

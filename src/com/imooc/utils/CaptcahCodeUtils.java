package com.imooc.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptcahCodeUtils {
    /**
     * 字母数字验证码
     * @param response
     * @return
     */
    public static String drawImage(HttpServletResponse response) {
        //  1：定义以字符串拼接的StringBuilder类
        StringBuilder builder = new StringBuilder();
        //  准备产生4个字符串的随机数
        for (int i = 0; i < 4; i++) {
            builder.append(randomChar());
        }
        String code = builder.toString();

        //  2：定义图片的宽度和高度
        int width = 120;
        int height = 25;
        //  简历bufferedImage对象，制定图片的长度和宽度以及色彩
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        //  3：获取到Graphics2D绘制对象，开始绘制验证码
        Graphics2D g = bi.createGraphics();
        //  4：设置文字的字体和大小
        Font font = new Font("微软雅黑",Font.PLAIN,20);
        //  5：设置字体的颜色
        Color color = new Color(0,0,0);
        //  设置字体
        g.setFont(font);
        //  设置颜色
        g.setColor(color);
        //  设置背景
        g.setBackground(new Color(226,226,240));
        //  开始绘制对象
        g.clearRect(0,0,width,height);
        //  绘制形状，获取矩形对象
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code,context);
        //  计算文件的坐标和间距
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;

        g.drawString(code,(int)x,(int)baseY);
        g.dispose();

        //  图片输出
        try {
            ImageIO.write(bi,"jpg",response.getOutputStream());
            //  刷新相应流
            response.flushBuffer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return code;
    }

    /**
     * 算术表达式验证码
     *
     * 1、干扰线的产生
     * 2、范围随机颜色，随机数
     *
     * @param response
     * @return
     */
    public static String drawImageVerificate(HttpServletResponse response) {
        int width = 100,height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(240,250));
        g.setFont(new Font("微软雅黑",Font.PLAIN,22));
        g.fillRect(0,0,width,height);

        //  干扰线的绘制
        g.setColor(getRandomColor(180,230));
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);

            g.drawLine(x,y,x1,y1);
        }

        //  开始对验证表达式的拼接
        int num1 = (int)(Math.random() * 10 + 1);
        int num2 = (int)(Math.random() * 10 + 1);
        int sbFlag = random.nextInt(3);

        if (num1 < num2) {
            num2 = num1 + num2;
            num1 = num2 - num1;
            num2 = num2 - num1;
        }

        String symbol = null;
        int result = 0;

        switch (sbFlag) {
            case 0:
                symbol = "+";
                result = num1 + num2;
                break;

            case 1:
                symbol = "-";
                result = num1 - num2;
                break;

            case 2:
                symbol = "x";
                result = num1 * num2;
                break;

            default:
                break;
        }

        //  计算表达式
        String calc = num1 + " " + symbol + " " + num2 + " = ?";
        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
        //  绘制表达式
        g.drawString(calc,5,25);
        //  结束绘制
        g.dispose();
        try {
            ImageIO.write(image,"JPEG",response.getOutputStream());
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 范围随机颜色
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandomColor(int fc,int bc) {
        Random random = new Random();

        fc = fc > 255? 255: fc;
        bc = bc > 255? 255: bc;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r,g,b);
    }

    /**
     * 此方法用于产生随机数字母和数字
     * @return
     */
    public static char randomChar() {
        //  1：定义验证码需要的字母和数字
        String string = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        //  2：定义随机对象
        Random random = new Random();
        return string.charAt(random.nextInt(string.length()));
    }
}

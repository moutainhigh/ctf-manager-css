package com.zdf.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码生成工具类
 * @author bocong.zheng
 */
public class AuthCodeUtil {
	public static final int AUTHCODE_LENGTH = 5; // length of verification code  
    public static final int SINGLECODE_WIDTH = 10; // width of one digit or character in the image  
    public static final int SINGLECODE_HEIGHT = 25; // height of one digit or character in the image  
    public static final int SINGLECODE_GAP = 5; // margin of a digit or character  
    public static final int PADDING_TOP_BOT = 10;// padding of top and bottom
    public static final int PADDING_LEFT_RIGHT = 10; //padding of left and right
    public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP) + PADDING_LEFT_RIGHT;  
    public static final int IMG_HEIGHT = SINGLECODE_HEIGHT+PADDING_TOP_BOT;  
    public static final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8',  
        '9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',  
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',  
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
    public static final char[] NUMBER_CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8',
        '9' };
    static Random random = new Random();
      

    public static String getAuthCode() {  
        StringBuffer buffer = new StringBuffer();  
        for (int i = 0; i < 5; i++) {// generate 6 digit and character  
            buffer.append(CHARS[random.nextInt(CHARS.length)]);  
        }  
        return buffer.toString();  
    }

    public static String getNumberAuthCode() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {// generate 4 digit and character
            buffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
        }
        return buffer.toString();
    }

    public static BufferedImage getAuthImg(String authCode) {  
        // Set img width height type 
        // RGB：red、green、blue  
        BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,  
                BufferedImage.TYPE_INT_BGR);  
 
        Graphics g = img.getGraphics();  
        // Set the img background
        g.setColor(new Color(255, 255, 230));  
        // draw a rectangle  
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);  
        // color of verification code 
        g.setColor(Color.BLACK);  
        // Set font details 
        g.setFont(new Font("Arial", Font.PLAIN, SINGLECODE_HEIGHT + 5));  
		//draw the code in the image
        char c;  
        for (int i = 0; i < authCode.toCharArray().length; i++) {  
      
            c = authCode.charAt(i);  
            
            g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP)  
                    + SINGLECODE_GAP / 2 +PADDING_LEFT_RIGHT/2, IMG_HEIGHT-PADDING_TOP_BOT/2);  
        }  
        Random random = new Random();  
        // add interferential elements - draw some random lines  
        for (int i = 0; i < 10; i++) {  
            int x = random.nextInt(IMG_WIDTH);  
            int y = random.nextInt(IMG_HEIGHT);  
            int x2 = random.nextInt(IMG_WIDTH);  
            int y2 = random.nextInt(IMG_HEIGHT);  
            g.drawLine(x, y, x + x2, y + y2);  
        }

        //干扰线
        for(int i=0;i<4;i++){
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            //画线
            g.drawLine(random.nextInt(120), random.nextInt(30), random.nextInt(120), random.nextInt(30));
        }
        //图片生成
        g.dispose();

        return img;  
    }

    public static void main(String[] args) {
        System.out.println(getNumberAuthCode());
    }
}
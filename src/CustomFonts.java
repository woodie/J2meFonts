import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

public class CustomFonts extends MIDlet {

    Display display;
CustumCanvas canvas;

    //constructor 
    public CustomFonts() {
        display = Display.getDisplay(this);
        canvas = new CustumCanvas(this);
    }

    //application starts
    public void startApp() {
        display.setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    //to exit the application 
    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }
}	

class CustumCanvas extends Canvas {

   //array each character width in the image file 
   private final int fontWidth[] = {8, 7, 8, 8, 7, 7, 8, 8, 4, 5, 7, 6, 10, 8, 9, 7, 9, 8, 8, 8, 8,  
                                               8, 12, 8, 7, 7, 7, 7, 6, 7, 7, 4, 7, 7, 3, 4, 6, 3, 11, 7, 7,     
                                               7, 7, 5, 6, 4, 7, 6, 10, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,       
                                               9, 7, 4, 12, 9, 7, 12, 9, 9, 8, 5, 5, 7, 8, 5, 9, 6, 6, 6, 5, 
                                               5, 5, 5, 5, 5, 3, 9, 9, 6, 4, 4, 5};

    //array each character initial position in the image file
private final int fontStartPos[] = {0, 8, 15, 23, 31, 38, 45, 53, 61, 65, 70, 77, 83, 93, 
                                                101, 110, 117, 126, 134, 142, 150, 158, 166, 178,   
                                                186, 195, 200, 207, 214, 220, 227, 234, 238, 245,   
                                                252, 255, 259, 265, 268, 279, 286, 293, 300, 307, 
                                                312, 318, 322, 329, 335, 345, 351, 357, 363, 370,  
                                                373, 384, 391, 398, 405, 412, 419, 428, 433, 442,     
                                                449, 453, 465, 474, 481, 493, 502, 511, 519, 524,  
                                                529, 536, 544, 549, 558, 564, 570, 576, 581, 586,  
                                                591, 596, 601, 606, 609, 618, 627, 633, 637, 641};


//image file characters
private final String imageChars =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$ %^&*()_+-={}|[]\\:”;’<>?,./";

//space between words
    private final int wordSpace = 3;
    private Graphics g;
    Image fontImage = null;
    int screenWidth;
    int screenHeight;
    int fontImageHeight;
    CustomFonts midlet;
    public final String exit="EXIT";

    //constructor 
public CustumCanvas(CustomFonts midlet) {
        this.midlet = midlet;
      //creating image containing fonts 
        try {
            fontImage = Image.createImage("/myFonts.png");
        } catch (Exception exe) {
            exe.printStackTrace();
        }

        //image file width 
        fontImageHeight = fontImage.getHeight();

        //screen width and height
        screenWidth = getWidth();
        screenHeight = getHeight();

    }

    public void paint(Graphics g) {
        this.g = g;
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, screenWidth, screenHeight);
        showFonts("These are", 8, 15);
        showFonts("Custom Fonts", 8, 30);
        showFonts(exit, screenWidth - (getStringWidth(exit)), screenHeight -  
        fontImageHeight);
    }

    //character the width of the character string in the image file
    public int getStringWidth(String str) {
        char imageFonts;
        int strWidth = 0;
        for (int i = 0; i < str.length(); i++) {
            imageFonts = str.charAt(i);
            for (int j = 0; j < imageChars.length(); j++) {
                if (imageFonts == imageChars.charAt(j)) {
                    if (imageFonts == ' ') {
                        strWidth += (wordSpace);
                        break;
                    } else {
                        strWidth += (fontWidth[j]);
                        break;
                    }
                }
            }
        }
        return strWidth;
}

    //clips the characters from the image file
    public void showFonts(String fonts, int fontx, int fonty) {
        for (int i = 0; i < fonts.length(); i++) {
            char imageFonts = fonts.charAt(i);
            for (int j = 0; j < imageChars.length(); j++) {
                if (imageFonts == ' ') {
                    fontx += (wordSpace);
                    break;
                } else if (imageFonts == imageChars.charAt(j)) {
                    g.setClip(fontx, fonty, fontWidth[j], fontImageHeight);
                    g.drawImage(fontImage, fontx - fontStartPos[j], fonty, Graphics.LEFT |  
                     Graphics.TOP);
                    fontx += (fontWidth[j]);
                    break;
                }
            }
        }
}

    //called on pressing key 
    public void keyPressed(int keycode) {
        if (keycode == -7) {
            midlet.exitMIDlet();
        }
    }

    //called on touching the screen
    public void pointerPressed(int x, int y) {
        if (x > (screenWidth - getStringWidth(exit)) && y > (screenHeight - 
            fontImageHeight)) {
            midlet.exitMIDlet();
        }
    }
}

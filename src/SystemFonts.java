import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class SystemFonts extends MIDlet {

    Display display;
    SystemCanvas canvas;

    //constructor 
    public SystemFonts() {
        display = Display.getDisplay(this);
        canvas = new SystemCanvas(this);
    }

    //starts the application
    public void startApp() {
        display.setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    //exit the application
    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
     }

}

class SystemCanvas extends Canvas {

    Font font = null;
    //Attributes values in an array
    public final int[] styles = {Font.STYLE_PLAIN, Font.STYLE_BOLD,   
                                     Font.STYLE_ITALIC};
     public final int[] sizes = {Font.SIZE_SMALL, Font.SIZE_MEDIUM, 
                                  Font.SIZE_LARGE};
    public final int[] faces = {Font.FACE_SYSTEM, Font.FACE_MONOSPACE,  
                             Font.FACE_PROPORTIONAL};
    //screen width and height
    int screenWidth; 
    int screenHeight;
    SystemFonts midlet;
    public final String exit="EXIT";

    //constructor
    public SystemCanvas(SystemFonts midlet) {
        this.midlet = midlet;
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    public void paint(Graphics g) {

        int y = 20;
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.setColor(255, 255, 255);
        g.drawString("Different sytem Fonts", 1, 1, Graphics.TOP | Graphics.LEFT);
        for (int size = 0; size < sizes.length; size++) {
            for (int face = 0; face < faces.length; face++) {
                int x = 0;
                for (int style = 0; style < styles.length; style++) {
                    font = Font.getFont(faces[face], styles[style], sizes[size]);
                    g.setFont(font);
                    g.drawString("Fonts |", x + 1, y + 1, Graphics.TOP | Graphics.LEFT);

                    x += font.stringWidth("Fonts |") + 1;
                }
                y += font.getHeight() + 4;
            }
        }

        g.drawString(exit, screenWidth - font.stringWidth(exit), screenHeight -  
                          font.getHeight(), 0);

    }

    //called on pressing key
    public void keyPressed(int keycode) {
        if (keycode == -7) {
            midlet.exitMIDlet();
        }
    }

    //called on touch event
    public void pointerPressed(int x, int y) {
        if (x > (screenWidth - font.stringWidth(exit)) && y > (screenHeight -   
            font.getHeight())) {
            midlet.exitMIDlet();
        }
    }
}


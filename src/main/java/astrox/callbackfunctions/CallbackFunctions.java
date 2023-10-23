package astrox.callbackfunctions;

import astrox.mainframe.MainFrame;
import org.cef.CefApp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallbackFunctions {

    private MainFrame astro;

    public CallbackFunctions(MainFrame astro) {
        this.astro = astro;
    }

    public void closeWindow() {
        //JOptionPane.showConfirmDialog(astro, "Want to Exit?", "Exit", JOptionPane.YES_NO_OPTION);
        astro.mainFrame.requestFocus();
        /*
        try {
            //save isDarkTheme
            if (guiSettings.getIsDarkTheme() != propertyService.getBoolean(Props.isDarkTheme)) {
                propertyService.setProperty("isDarkTheme", guiSettings.getIsDarkTheme().toString());
            }
            //save LightTheme
            if (guiSettings.getLightTheme() != guiSettings.lightThemesList.getSelectedValue().toString()) {
                propertyService.setProperty("LightTheme", guiSettings.lightThemesList.getSelectedValue().toString());
            }
            //save DarkTheme
            if (guiSettings.getDarkTheme() != guiSettings.darkThemesList.getSelectedValue().toString()) {
                propertyService.setProperty("DarkTheme", guiSettings.darkThemesList.getSelectedValue().toString());
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(astro, "error: " + exception, "title", 1);
        }

        database.closeDB();

        */
        try {
            CefApp.getInstance().dispose();
            System.exit(0);
        } catch (Exception exception) {
            System.out.println("Error exit: " + exception);
        }
    }

    private boolean isFullScreen = false;
    private Dimension originalSize;
    private Point originalLocation;
    private Timer timer;
    private long startTime = 100;
    private int animationDuration = 1000; // Duration of the animation in milliseconds
    private int animationDelay = 20; // Time interval for animation updates
    private int animationSteps;
    private int animationStepCounter = 0;
    private int initialWidth = 800; // Initial window width
    private int initialHeight = 600; // Initial window height
    private int targetWidth;
    private int targetHeight;
    private int currentWidth = initialWidth;
    private int currentHeight = initialHeight;
    private int originalDeltaXLeft;
    private int originalDeltaXRight;
    private int originalDeltaYUp;
    private int originalDeltaYDown;
    private int deltaXLeftStep;
    private int deltaXRightStep;
    private int deltaYUpStep;
    private int deltaYDownStep;
    
    

    public void FullScreenAnimation() {

        originalSize = astro.mainFrame.getSize();
        originalLocation = astro.mainFrame.getLocation();

        targetWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // Full-screen width
        targetHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // Full-screen height

        System.out.println("Target Width: " + targetWidth);
        System.out.println("Target Height: " + targetHeight);

        originalDeltaXLeft = originalLocation.x;
        originalDeltaXRight = targetWidth - (originalLocation.x + originalSize.width);

        originalDeltaYUp = originalLocation.y;
        originalDeltaYDown = targetHeight - (originalLocation.y + originalSize.height);

        System.out.println("originalDeltaXLeft: " + originalDeltaXLeft);
        System.out.println("originalDeltaXRight: " + originalDeltaXRight);

        System.out.println("originalDeltaYUp: " + originalDeltaYUp);
        System.out.println("originalDeltaYDown: " + originalDeltaYDown);

        animationStepCounter = 0;

        animationSteps = animationDuration / animationDelay;

        deltaXLeftStep = originalDeltaXLeft / animationSteps;
        deltaXRightStep = originalDeltaXRight / animationSteps;

        deltaYUpStep = originalDeltaYUp / animationSteps;
        deltaYDownStep = originalDeltaYDown / animationSteps;

        System.out.println("deltaXLeftStep: " + deltaXLeftStep);
        System.out.println("deltaXRightStep: " + deltaXRightStep);

        System.out.println("deltaYUpStep: " + deltaYUpStep);
        System.out.println("deltaYDownStep: " + deltaYDownStep);

        timer = new Timer(animationDelay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            if (animationStepCounter <= animationSteps) {
                int actXLocation = originalLocation.x - animationStepCounter * deltaXLeftStep;
                int actYLocation = originalLocation.y - animationStepCounter * deltaYUpStep;
                int actWidth = originalSize.width + animationStepCounter * deltaXLeftStep + animationStepCounter * deltaXRightStep;
                int actHeight = originalSize.height + animationStepCounter * deltaYUpStep + animationStepCounter * deltaYDownStep;
                astro.mainFrame.setBounds(actXLocation, actYLocation, actWidth, actHeight);
                animationStepCounter++;
                System.out.println("X: " + actXLocation);
                System.out.println("Y: " + actYLocation);
                System.out.println("Width: " + actWidth);
                System.out.println("Height: " + actHeight);
            }
            else {
                timer.stop();
                astro.mainFrame.setBounds(0,0, targetWidth, targetHeight);
                astro.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            astro.mainFrame.revalidate();
            astro.mainFrame.repaint();

/*
                if (elapsedTime >= animationDuration) {
                    // Animation complete
                    timer.stop();
                    astro.mainFrame.setSize(targetWidth, targetHeight);
                    astro.mainFrame.setLocation(0, 0);
                    astro.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } else {
                    // Calculate the new window size based on the elapsed time
                    double progress = (double) elapsedTime / animationDuration;
                    int newWidth = (int) (initialWidth + progress * (targetWidth - initialWidth));
                    int newHeight = (int) (initialHeight + progress * (targetHeight - initialHeight));
                    astro.mainFrame.setSize(newWidth, newHeight);

                    // Center the window during the animation
                    int x = (targetWidth - newWidth) / 2;
                    int y = (targetHeight - newHeight) / 2;
                    astro.mainFrame.setLocation(x, y);


                }
*/
            }
        });
    }

    public void startAnimation() {
        timer.start();
    }

    public void ScreenCheck() {
        // Get the default graphics environment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get an array of all available screen devices
        GraphicsDevice[] screens = ge.getScreenDevices();

        // Get the bounds of your frame
        Rectangle frameBounds = astro.mainFrame.getBounds();

        // Iterate through each screen to check if your frame is on it
        for (int i = 0; i < screens.length; i++) {
            Rectangle screenBounds = screens[i].getDefaultConfiguration().getBounds();

            if (screenBounds.contains(frameBounds)) {
                System.out.println("Your frame is on Screen " + i);
                break; // You can break out of the loop once you find the screen
            }
        }
    }

    public void toggleFullscreen() {

        if (isFullScreen) {
            // Exit full-screen mode
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            device.setFullScreenWindow(null);
            astro.mainFrame.setSize(originalSize);
            isFullScreen = false;
        } else {
            // Enter full-screen mode
            originalSize = astro.mainFrame.getSize();
            originalLocation = astro.mainFrame.getLocation();
            FullScreenAnimation();
            startAnimation();
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (device.isFullScreenSupported()) {
                device.setFullScreenWindow(astro.mainFrame);
                isFullScreen = true;
            } else {
                System.err.println("Full-screen mode not supported.");
            }
        }
    }

    public void resizeBrowserPanel(int width, int height, int left, int right, int top, int bottom) {

        astro.browserPanel.setBounds(left, top, width, height);
        //System.out.println("resizeBrowserPanel left: " + left + "top" + top);
        astro.browserPanel.revalidate();
        astro.browserPanel.repaint();
    }
}

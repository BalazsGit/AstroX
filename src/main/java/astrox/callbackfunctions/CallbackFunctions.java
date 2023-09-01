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
    private int animationStep = 20; // Time interval for animation updates
    private int initialWidth = 800; // Initial window width
    private int initialHeight = 600; // Initial window height
    private int targetWidth;
    private int targetHeight;
    private int currentWidth = initialWidth;
    private int currentHeight = initialHeight;

    public void FullScreenAnimation() {

        originalSize = astro.mainFrame.getSize();
        originalLocation = astro.mainFrame.getLocation();

        targetWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // Full-screen width
        targetHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // Full-screen height

        timer = new Timer(animationStep, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                }

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

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
            }
        });
    }

    public void startAnimation() {
        timer.start();
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
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (device.isFullScreenSupported()) {
                device.setFullScreenWindow(astro.mainFrame);
                isFullScreen = true;
            } else {
                System.err.println("Full-screen mode not supported.");
            }
        }
    }
}

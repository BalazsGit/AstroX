package astrox.callbackfunctions;

import astrox.mainframe.MainFrame;
import org.cef.CefApp;

import java.awt.*;

public class CallbackFunctions {

    private MainFrame astro;
    private boolean isFullScreen = false;
    private Dimension originalSize;

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

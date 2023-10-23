package astrox.init;

import astrox.mainframe.MainFrame;
import org.cef.CefApp;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InitClose {
    public InitClose(MainFrame astro) {
        astro.mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
                CefApp.getInstance().dispose();
                //astro.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);

            }

        });
    }
}

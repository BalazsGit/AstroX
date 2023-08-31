package astrox;

import astrox.init.*;
import astrox.mainframe.MainFrame;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.UnsupportedPlatformException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {

        MainFrame astro = new MainFrame("astrox");

        new InitPropertyService(astro);
        //new InitClose(astro);

        //new InitFiles(astro);

        //new InitUIManager(astro);
        //new InitGUISettins(astro); // nullpointer excepsion

        //new InitCefBrowser(astro, args);
        //new InitTabbedBrowserPanel(astro); // nullpointer excepsion

        //createNewBrowserTab(astro, "https://google.com");

        JLabel label = new JLabel("Hello, World!");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        //astro.mainPanel.add(label, BorderLayout.CENTER);
        astro.mainPanel.add(label);

        astro.mainPanel.setVisible(true);
        astro.mainFrame.setVisible(true);
        //astro.pack();
        //astro.mainPanel.revalidate();
        //astro.mainPanel.repaint();
        //SwingUtilities.updateComponentTreeUI(astro);

    }
}

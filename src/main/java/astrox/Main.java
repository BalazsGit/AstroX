package astrox;

import astrox.browserframe.BrowserFrame;
import astrox.init.*;
import astrox.mainframe.MainFrame;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.UnsupportedPlatformException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException, URISyntaxException {

        MainFrame astro = new MainFrame("astrox");

        new InitPropertyService(astro);

        //new InitFiles(astro);

        //new InitUIManager(astro);
        //new InitGUISettins(astro); // nullpointer excepsion

        new InitCefBrowser(astro, args);

        BrowserFrame browserFrame = new BrowserFrame(astro, "file://" + astro.htmlResourcePath);
        //BrowserFrame browserFrame = new BrowserFrame(astro, "www.google.com");

        astro.mainFrame.add(browserFrame.browserUI);
        astro.mainFrame.setVisible(true);

        //SwingUtilities.updateComponentTreeUI(astro);

    }
}

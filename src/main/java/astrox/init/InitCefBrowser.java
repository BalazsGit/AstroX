package astrox.init;

import astrox.mainframe.MainFrame;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefFocusHandlerAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class InitCefBrowser {

    private final JTextField address = null;
    private CefAppBuilder cefAppBuilder;
    private CefApp cefApp;
    private CefClient cefClient;
    private CefBrowser cefBrowser;
    private Component browserUI;
    private CefMessageRouter cefMessageRouter;
    private boolean browserFocus = true;

    public InitCefBrowser(MainFrame astro, String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {

        // (0) Initialize CEF using the maven loader
        astro.cefAppBuilder = new CefAppBuilder();
        //builder = new CefAppBuilder();

        // windowless_rendering_enabled must be set to false if not wanted.
        astro.cefAppBuilder.getCefSettings().windowless_rendering_enabled = astro.useOSR;
        //cefAppBuilder.getCefSettings().windowless_rendering_enabled = useOSR;
        // USE builder.setAppHandler INSTEAD OF CefApp.addAppHandler!
        // Fixes compatibility issues with MacOSX
        astro.cefAppBuilder.setAppHandler(new MavenCefAppHandlerAdapter() {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefApp.CefAppState.TERMINATED) System.exit(0);
            }
        });

        if (args.length > 0) {
            astro.cefAppBuilder.addJcefArgs(args);
        }

        astro.cefApp = astro.cefAppBuilder.build();

    }
}

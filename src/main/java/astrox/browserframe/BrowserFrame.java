package astrox.browserframe;

import astrox.config.Props;
import astrox.mainframe.MainFrame;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.browser.CefRequestContext;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;
import astrox.callbackfunctions.CallbackFunctions;
import org.cef.handler.CefRequestContextHandlerAdapter;
import org.json.JSONObject;

import java.awt.*;
import java.util.Map;

public class BrowserFrame {

    private CefApp cefApp;
    private CefBrowser cefBrowser;
    private CefClient cefClient;
    private CefMessageRouter cefMessageRouter;
    private String browserFrameURL;
    public Component browserUI;
    private boolean isTransparent = true;
    private CallbackFunctions callbackFunctions;

    // Define your Java function that will be called from JavaScript.
    private void myJavaFunction() {
        // Implement your Java logic here.
        System.out.println("Java function called from JavaScript");
    }
    public BrowserFrame(MainFrame astro, String URL) {

        callbackFunctions = new CallbackFunctions(astro);

        //cefApp = CefApp.getInstance(DEFAULT_SETTINGS);
        //client = cefApp.createClient();

        cefClient = astro.cefApp.createClient();

        // (3) Create a simple message router to receive messages from CEF.
        cefMessageRouter = CefMessageRouter.create();
        cefClient.addMessageRouter(cefMessageRouter);

        // (4) One CefBrowser instance is responsible to control what you'll see on
        //     the UI component of the instance. It can be displayed off-screen
        //     rendered or windowed rendered. To get an instance of CefBrowser you
        //     have to call the method "createBrowser()" of your CefClient
        //     instances.
        //
        //     CefBrowser has methods like "goBack()", "goForward()", "loadURL()",
        //     and many more which are used to control the behavior of the displayed
        //     content. The UI is held within a UI-Compontent which can be accessed
        //     by calling the method "getUIComponent()" on the instance of CefBrowser.
        //     The UI component is inherited from a java.awt.Component and therefore
        //     it can be embedded into any AWT UI.
        //cefBrowser = cefClient.createBrowser(startURL, useOSR, isTransparent);
        //browserUI = cefBrowser.getUIComponent();

        // (5) For this minimal browser, we need only a text field to enter an URL
        //     we want to navigate to and a CefBrowser window to display the content
        //     of the URL. To respond to the input of the user, we're registering an
        //     anonymous ActionListener. This listener is performed each time the
        //     user presses the "ENTER" key within the address field.
        //     If this happens, the entered value is passed to the CefBrowser
        //     instance to be loaded as URL.


        // Update the address field when the browser URL changes.
       /*cefClient.addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                urlField.setText(url);
            }
        });

        */

        browserFrameURL = URL;

        if (URL != null) {
            //urlField.setText(browserFrameURL);
            cefBrowser = cefClient.createBrowser(URL, astro.useOSR, astro.isTransparent);
        } else {
            browserFrameURL = astro.propertyService.getString(Props.startPageURL);
            //urlField.setText(browserFrameURL);
            cefBrowser = cefClient.createBrowser(browserFrameURL, astro.useOSR, astro.isTransparent);
        }
/*
        class CustomRequestContextHandler extends CefRequestContextHandlerAdapter {
            @Override
            public void onRequestContextInitialized(CefBrowser browser) {
                // Set the X-Frame-Options header for the RequestContext
                setXFrameOptions(browser, "DENY");
            }
        }

        // Get the global request context
        CefRequestContextHandlerAdapter requestContextHandler = new CustomRequestContextHandler(null);
        CefRequestContext globalRequestContext = CefRequestContext.createContext(requestContextHandler);

        // Set the X-Frame-Options header for the global request context
        requestContextHandler.setXFrameOptions("DENY");

        // Assign the global request context to the browser
        cefBrowser.setRequestContext(globalRequestContext);
*/
        browserUI = cefBrowser.getUIComponent();

        // Handle the query from CEF message router
        cefMessageRouter.addHandler(new CefMessageRouterHandlerAdapter() {
            @Override
            public boolean onQuery(CefBrowser browser, CefFrame frame, long queryId, String request, boolean persistent, CefQueryCallback callback) {
                if (request.equals("myQuery")) {
                    // Call your Java function here
                    //String javaResponse = myJavaFunction();
                    myJavaFunction();
                    callback.success(null);
                    System.out.println("Function called in java side");
                    // Send a response back to the CEF browser
                    //cefMessageRouter.sendQueryResponse(queryId, javaResponse);
                    return true;
                }
                if (request.equals("closeWindow")) {
                    // Call your Java function here
                    //String javaResponse = myJavaFunction();
                    callbackFunctions.closeWindow();
                    callback.success(null);
                    System.out.println("Function called in java side");
                    // Send a response back to the CEF browser
                    //cefMessageRouter.sendQueryResponse(queryId, javaResponse);
                    return true;
                }
                if (request.equals("toggleFullscreen")) {
                    // Call your Java function here
                    //String javaResponse = myJavaFunction();
                    callbackFunctions.toggleFullscreen();
                    callback.success(null);
                    System.out.println("Function called in java side");
                    // Send a response back to the CEF browser
                    //cefMessageRouter.sendQueryResponse(queryId, javaResponse);
                    return true;
                }
                if (request.contains("resizeBrowserPanel")) {

                    String prefix = "resizeBrowserPanel?";

                    request = request.substring(prefix.length());

                    JSONObject params = new JSONObject(request);

                    // Check if the JSON object contains the "width" and "height" fields
                    if (!params.isEmpty()) {
                        int width = params.getInt("width");
                        int height = params.getInt("height");
                        int left = params.getInt("left");
                        int right = params.getInt("right");
                        int top = params.getInt("top");
                        int bottom = params.getInt("bottom");

                        callbackFunctions.resizeBrowserPanel(width, height, left, right, top, bottom);

                        // Now you have the width and height as integers, use them as needed
                        System.out.println("Received width: " + width + ", height: " + height);

                    }
                    else {
                        //no parameters
                    }

                        callback.success(null);
                        //System.out.println("Function called in java side");
                        // Send a response back to the CEF browser
                        //cefMessageRouter.sendQueryResponse(queryId, javaResponse);
                        return true;
                }
                return false;
            }
        }, isTransparent);
        //browserPanel.add(browserUI);
    }
}

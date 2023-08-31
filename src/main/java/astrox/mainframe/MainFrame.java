package astrox.mainframe;

import astrox.config.PropertyService;
import me.friwi.jcefmaven.CefAppBuilder;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;
import org.h2.engine.Database;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Properties;

public class MainFrame {

    public JFrame mainFrame;
    public JPanel mainPanel;
    //public JTextField urlField;
    public JButton start_scavenger;
    public JButton start_turboplotter;
    public JButton start_signum;
    public JComboBox themeBox;
    public JPanel textEditor1;
    public JTabbedPane tabbedTextEditor;
    public JTabbedPane tabbedBrowserPanel;
    //public DnDTabbedPane tabbedBrowserPanel = (DnDTabbedPane) tabbedBrowserPanel_;
    public JTabbedPane tabbedApplications;
    public JLabel themeLabel;
    public JTabbedPane tabbedSettings;
    private JPanel mainCardPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTabbedPane tabbedPane;
    private JPanel browserPanel;
    private JPanel applicationsPanel;
    private JPanel textEditorPanel;
    private JPanel settingsPanel;
    private JPanel BRSAPIPanel;
    private JPanel BRSAPI;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel loadingPagePanel;
    private JPanel lockScreenPanel;

    //CEF
    public CefAppBuilder cefAppBuilder;
    public CefApp cefApp;
    public CefClient cefClient;
    public CefBrowser cefBrowser;
    public Component browserUI;
    public CefMessageRouter cefMessageRouter;
    public boolean browserFocus = true;
    public boolean useOSR = false;
    public boolean isTransparent = false;

    public PropertyService propertyService;
    public String flatLafConfigDirPath = "PROJECT/CONFIG/FLATLAF";
    public Properties flatLafLightProperties;
    public Properties flatLafDarkProperties;
    public String configDirPath = "PROJECT/CONFIG/";
    public String propertiesFileName = "astro.properties";
    public String propertiesFileNameFlatlafLight = "FlatlafLightLaf.properties";
    public String propertiesFileNameFlatlafDark = "FlatlafDarkLaf.properties";

    public static String  signumDirPath = "PROJECT/APPLICATIONS/WALLET/SIGNUM/";
    //WALLET/PHOENIX
    public static String  phoenixDirPath = "PROJECT/APPLICATIONS/WALLET/PHOENIX/";
    //EXCHANGE/BTDEX
    public static String  btdexDirPath = "PROJECT/APPLICATIONS/EXCHANGE/BTDEX/";
    //MINER/SCAVENGER
    public static String  scavengerDirPath = "PROJECT/APPLICATIONS/MINER/SCAVENGER/";
    //PLOTTER/TURBOPLOTTER
    public static String  turboplotterDirPath = "PROJECT/APPLICATIONS/PLOTTER/TURBOPLOTTER/";
    //PLOTTER/ENGRAVER
    public static String  engraverDirPath = "PROJECT/APPLICATIONS/PLOTTER/ENGRAVER/";
    //IMAGES
    public static String  imagesDirPath = "PROJECT/IMAGES/";
    //ICONS
    public static String iconsDirPath = "PROJECT/ICONS/";

    public static Database database;

    //empty play icon
    public static String play1 = "\u25B7"; //OK

    //filled play icon
    public static String play2 = "\u25B6"; //OK

    //empty stop icon
    public static String stop1 = "\u25FB"; //OK

    //filled stop icon
    public static String stop2 = "\u25FC"; //OK

    //settings icon ☰
    public static String settings1 = "\u2630"; //OK

    //settings icon Ⅲ
    public static String settings2 = "\u2162";

    //close icon
    static String close1 = "\u2715"; //OK
    static String close2 = "\u274C"; //OK

    //plus icon
    public static String plus1 = "\uFF0B"; //OK
    static String plus2 = "\uFE62"; //same
    static String plus3 = "\u2795"; //OK
    static String plus4 = "\u2795"; //OK very good

    //arrow left "\u276E"
    static String arrowLeft1 = "\u2770"; //OK
    static String arrowLeft2 = "\u2770\u2796"; //OK

    //arrow right "\u276F"
    static String arrowRight1 = "\u2771"; //OK
    static String arrowRight2 = "\u2796\u25B6"; //OK
    public static String arrowRight3 = "\u279C"; //OK best
    static String arrowRight4 = "\u2794"; //OK

    //vertical separator line
    public static String separator1 = "\u2758"; //OK

    private final JTextField address = null;

    private final CefBrowser browser = null;
    private final Component browerUI = null;

    //public Boolean isDarkTheme = true;

    //public CefBrowser browser;


    //public ArrayList browserList = new ArrayList<CefBrowser>();

    //public boolean browserFocus_ = true;
    private boolean resizing = false;
    private boolean resizingHorizontal = false;
    private boolean resizingVertical = false;

    private boolean resizingHorizontalLeft = false;
    private boolean resizingHorizontalRight = false;
    private boolean resizingVerticalUp = false;
    private boolean resizingVerticalDown = false;
    private int deltaX;
    private int deltaY;
    private int deltaLocX;
    private int deltaLocY;
    private int resizeZoneWidth = 4;
    private int resizeZoneHeight = 4;
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private int prevLocX;
    private int prevLocY;
    private int prevWidth;
    private int prevHeight;
    private int prevDeltaX;
    private int prevDeltaY;
    private boolean lockZoneLeft = false;
    private boolean lockZoneRight = false;
    private boolean lockZoneUp = false;
    private boolean lockZoneDown = false;
    private boolean lockZoneLeftUp = false;
    private boolean lockZoneLeftDown = false;
    private boolean lockZoneRightUp = false;
    private boolean lockZoneRightDown = false;

    private boolean isInHorizontalResizeZoneLeft(MouseEvent e) {
        return e.getX() <= resizeZoneWidth;
    }
    private boolean isInHorizontalResizeZoneRight(MouseEvent e) {
        return e.getX() >= mainFrame.getWidth() - resizeZoneWidth;
    }
    private boolean isInVerticalResizeZoneUp(MouseEvent e) {
        return e.getY() <= resizeZoneHeight;
    }
    private boolean isInVerticalResizeZoneDown(MouseEvent e) {
        return e.getY() >= mainFrame.getHeight() - resizeZoneHeight;
    }
    private boolean isInDiagonalResizeZoneLeftUp(MouseEvent e) {
        return isInHorizontalResizeZoneLeft(e) && isInVerticalResizeZoneUp(e);
    }
    private boolean isInDiagonalResizeZoneLeftDown(MouseEvent e) {
        return isInHorizontalResizeZoneLeft(e) && isInVerticalResizeZoneDown(e);
    }
    private boolean isInDiagonalResizeZoneRightUp(MouseEvent e) {
        return isInHorizontalResizeZoneRight(e) && isInVerticalResizeZoneUp(e);
    }
    private boolean isInDiagonalResizeZoneRightDown(MouseEvent e) {
        return isInHorizontalResizeZoneRight(e) && isInVerticalResizeZoneDown(e);
    }

    public MainFrame(String title) {

        mainFrame = new JFrame();
/*
        this.add(mainCardPanel);
        mainCardPanel.add(mainPanel);
        mainCardPanel.add(loadingPagePanel);
        mainCardPanel.add(lockScreenPanel);
*/
        //urlField.setText("Loading ...");
        //urlField.setFocusable(true);
        //urlField.setVisible(true);
        //browserTab.setVisible(true);

        //mainFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        mainFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                mainFrame.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {

                prevWidth = mainFrame.getWidth();
                prevHeight = mainFrame.getHeight();

                prevX = e.getXOnScreen();
                prevY = e.getYOnScreen();

                prevLocX = mainFrame.getLocation().x;
                prevLocY = mainFrame.getLocation().y;

                if ( isInDiagonalResizeZoneLeftUp(e) ) {
                    lockZoneLeftUp = isInDiagonalResizeZoneLeftUp(e);
                } else if ( isInDiagonalResizeZoneLeftDown(e) ) {
                    lockZoneLeftDown = isInDiagonalResizeZoneLeftDown(e);
                } else if ( isInDiagonalResizeZoneRightUp(e) ) {
                    lockZoneRightUp = isInDiagonalResizeZoneRightUp(e);
                } else if ( isInDiagonalResizeZoneRightDown(e) ) {
                    lockZoneRightDown = isInDiagonalResizeZoneRightDown(e);
                } else if ( isInHorizontalResizeZoneLeft(e) ) {
                    lockZoneLeft = isInHorizontalResizeZoneLeft(e);
                } else if ( isInHorizontalResizeZoneRight(e) ) {
                    lockZoneRight = isInHorizontalResizeZoneRight(e);
                } else if ( isInVerticalResizeZoneUp(e) ) {
                    lockZoneUp = isInVerticalResizeZoneUp(e);
                } else if ( isInVerticalResizeZoneDown(e) ) {
                    lockZoneDown = isInVerticalResizeZoneDown(e);
                } else {

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                lockZoneLeft = false;
                lockZoneRight = false;
                lockZoneUp = false;
                lockZoneDown = false;
                lockZoneLeftUp = false;
                lockZoneLeftDown = false;
                lockZoneRightUp = false;
                lockZoneRightDown = false;

            }
        });


        mainFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                if ( isInDiagonalResizeZoneLeftUp(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                } else if ( isInDiagonalResizeZoneLeftDown(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                } else if ( isInDiagonalResizeZoneRightUp(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                } else if ( isInDiagonalResizeZoneRightDown(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                } else if ( isInHorizontalResizeZoneLeft(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if ( isInHorizontalResizeZoneRight(e)) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if ( isInVerticalResizeZoneUp(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else if ( isInVerticalResizeZoneDown(e) ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else {
                    mainFrame.setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                deltaX = prevX - e.getXOnScreen();
                deltaY = prevY - e.getYOnScreen();

                if ( lockZoneLeftUp ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    mainFrame.setBounds(prevLocX - deltaX, prevLocY - deltaY, prevWidth + deltaX, prevHeight + deltaY);
                } else if ( lockZoneLeftDown ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    mainFrame.setBounds(prevLocX - deltaX, prevLocY, prevWidth + deltaX, prevHeight - deltaY);
                } else if (  lockZoneRightUp ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    mainFrame.setBounds(prevLocX, prevLocY - deltaY, prevWidth - deltaX, prevHeight + deltaY);
                } else if ( lockZoneRightDown ) {
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    mainFrame.setSize(prevWidth - deltaX, prevHeight - deltaY);
                } else if ( lockZoneLeft && prevDeltaX != deltaX) {
                    prevDeltaX = deltaX;
                    System.out.println("deltaX :" + deltaX);
                    System.out.println("prevLocX :" + prevLocX);
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    mainFrame.setBounds(prevLocX - deltaX, prevLocY, prevWidth + deltaX, prevHeight);
                } else if (lockZoneRight && prevDeltaX != deltaX) {
                    prevDeltaX = deltaX;
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                    mainFrame.setSize(prevWidth - deltaX, prevHeight);
                } else if (lockZoneUp && prevDeltaY != deltaY) {
                    prevDeltaY = deltaY;
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                    mainFrame.setBounds(prevLocX, prevLocY - deltaY, prevWidth, prevHeight + deltaY);
                } else if (lockZoneDown && prevDeltaY != deltaY) {
                    prevDeltaY = deltaY;
                    mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    mainFrame.setSize(prevWidth, prevHeight - deltaY);
                } else {

                }
            }
        });

        mainFrame.setSize(800, 600);

        mainFrame.setLocationRelativeTo(null);

        mainFrame.setUndecorated(true);

        mainFrame.setBackground(Color.BLACK);
/*
        // Add a mouse listener to handle resizing
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;
            private boolean resizing = false;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                resizing = isResizingZone(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizing) {
                    Point currentPoint = e.getLocationOnScreen();
                    int deltaX = currentPoint.x - initialClick.x;
                    int deltaY = currentPoint.y - initialClick.y;
                    frame.setSize(frame.getWidth() + deltaX, frame.getHeight() + deltaY);
                    initialClick = currentPoint;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                resizing = false;
            }

            private boolean isResizingZone(MouseEvent e) {
                int borderSize = 8; // Size of the resizing border
                return e.getX() > frame.getWidth() - borderSize || e.getY() > frame.getHeight() - borderSize;
            }
        };

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
*/



        JPanel contentPane = new JPanel();
        mainFrame.setContentPane(contentPane);

        mainFrame.setVisible(true);


    // Custom resizable border

        //this.setTitle(title);
        //setLayout(new BorderLayout());

        mainPanel = new JPanel();
        //mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(800, 600);
        mainPanel.setBackground(Color.GRAY);
        //mainPanel.setVisible(true);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
/*
        JLabel label = new JLabel("Hello, World!");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(label, BorderLayout.CENTER);

        /*
        BrowserTabHeader header = new BrowserTabHeader();
        BrowserFrame browserFrame = new BrowserFrame(this, header, "https://google.com");
        browserFrame.setSize(800,600);
        mainPanel.add(browserFrame.browserPanel);

        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        */
/*
        themeBox.addItem("FlatDarkLaf");
        themeBox.addItem("FlatLightLaf");
        themeBox.addItem("ARC - Orange");

        isDarkTheme = propertyService.getBoolean(Props.isDarkTheme);

        if(isDarkTheme) {
            //later set icon
            isDarkThemeLabel.setText("DarkTheme");
        }
        else{
            //later set icon
            isDarkThemeLabel.setText("LightTheme");
        }

        isDarkThemeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (isDarkTheme){
                    isDarkTheme = false;
                    propertyService.setProperty("isDarkTheme", "true");
                    isDarkThemeLabel.setText("DarkTheme");
                    UIManager.setLookAndFeel(darkThemesHashMap.get(propertyService.getString(Props.DarkTheme)).getClassName());
                }
                else{
                    isDarkTheme = true;
                    propertyService.setProperty("isDarkTheme", "false");
                    isDarkThemeLabel.setText("LightTheme");
                    UIManager.setLookAndFeel(darkThemesHashMap.get(propertyService.getString(Props.DarkTheme)).getClassName());
                }
                SwingUtilities.updateComponentTreeUI(mainPanel);

            }
        });
        */
    }
}

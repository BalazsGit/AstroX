package astrox.init;

import astrox.config.Props;
import astrox.mainframe.MainFrame;
import astrox.util.FlatTabbedPaneAddIcon;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class InitUIManager {

    public InitUIManager(MainFrame astro) {
        //put to GUI settings later
        //UIManager settings
        //set GUI theme
        //Font defaultFont
        Font defaultFont = UIManager.getDefaults().getFont("defaultFont");

        Font userFont = new Font(astro.propertyService.getString(Props.fontFamily), astro.propertyService.getInt(Props.fontStyle), astro.propertyService.getInt(Props.fontSize));

        //Menu.opaque = false
        //MenuItem.opaque = false

        UIManager.put("defaultFont", userFont);
        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.scrollButtonsPolicy", "asNeededSingle");
        UIManager.put("TabbedPane.tabsPopupPolicy", "never");
        UIManager.put("TabbedPane.tabSelectionHeight", 1);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("SplitPaneDivider.gripDotCount", 0);
        UIManager.put("List.background", "@background");
        //UIManager.put( "ToolBar.separatorSize", 1 );

        //not working
        /*
            UIManager.put( "ToolBar.separatorWidth", 20 );
        UIManager.put( "Separator.height", 20 );
        UIManager.put( "Separator.stripeWidth", 20 );
        UIManager.put( "Separator.stripeIndent", 20 );
        UIManager.put( "ToolBar.gripColor", "@foreground" );
        UIManager.put( "ToolBar.separatorColor", "$Separator.foreground" );

         */
        //end not working

        //fast up GUI
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        setDefaultLookAndFeelDecorated(true);

    }
}

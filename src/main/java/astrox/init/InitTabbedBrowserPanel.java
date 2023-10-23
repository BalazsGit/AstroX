package astrox.init;

import astrox.config.Props;
import astrox.mainframe.MainFrame;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import astrox.util.FlatTabbedPaneAddIcon;

public class InitTabbedBrowserPanel {

    //Browser Tab
    //add new browserTab button
    public InitTabbedBrowserPanel(MainFrame astro) {

        /*
        JLabel newBrowserTabButton = new JLabel();
        FlatTabbedPaneAddIcon flatTabbedPaneAddIcon = new FlatTabbedPaneAddIcon();

        int index = astro.tabbedBrowserPanel.getTabCount();

        //newBrowserTabButton.setIcon(flatTabbedPaneAddIcon);
        newBrowserTabButton.setText(astro.plus1);
        //newBrowserTabButton.setEnabled(false);

        astro.tabbedBrowserPanel.insertTab("+", null, null, "new browser tab", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedBrowserPanel.setTabComponentAt(index, newBrowserTabButton);

        //create new browserTab
        createNewBrowserTab(astro, null);
        //add new browser tab with "+" button
        newBrowserTabButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                createNewBrowserTab(astro, astro.propertyService.getString(Props.newTabURL));

            }
        });
         */


    }
}

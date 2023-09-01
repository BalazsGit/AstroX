package astrox.init;

import astrox.config.PropertyService;
import astrox.config.PropertyServiceImpl;
import astrox.config.Props;
import astrox.mainframe.MainFrame;
import astrox.util.Reload;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Timer;

public class InitPropertyService {
    public InitPropertyService(MainFrame astro) {
        // Start property Service
        astro.propertyService = new PropertyServiceImpl(astro.configDirPath + astro.propertiesFileName);
        //database = new Database();

        java.util.Timer timer = new Timer();
        timer.schedule(new Reload(astro.propertyService, astro.configDirPath + astro.propertiesFileName), 0, astro.propertyService.getLong(Props.reload));
    }

}

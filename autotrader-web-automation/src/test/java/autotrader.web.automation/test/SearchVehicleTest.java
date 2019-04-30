package autotrader.web.automation.test;

import autotrader.web.automation.page.SearchVehicle;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchVehicleTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(SearchVehicleTest.class);
    @Test
    public void testSearchVehicle()throws IOException{
        try {
            SetUp();
            SearchVehicle select = new SearchVehicle(BaseTest.getdriver());
            select.EnterSearchInfo();
            select.AdvanceSearchInfo();
        } catch (IOException e) {
            logger.error("<b> - Searching for vehicle failed - <font color=\"red\">ERROR</font></b>, exception: " + e);
            throw e;
        }
    }
}

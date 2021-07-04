package Tests;

import main.Backend.Maps.MapStructs.Point;
import org.junit.Assert;
import org.junit.Test;

public class OpenRouteServiceTests {

    public String TestingToken = Const.ORSToken;

    @Test
    public void DistanceAccuracyTest() {
        var startpoint = new Point(73.50527286529542, 4.175673819830992);
        var endpoint = new Point(73.51314783096315, 4.177171868265313);
        var handler = new main.Backend.Maps.OpenRouteService.ORSHandler(TestingToken, false);
        double error = Math.abs(handler.GetRoutes(startpoint, endpoint).get(0).Distance -  1173.5);
        double errorMargin = 30;
        Assert.assertTrue(errorMargin > error);

    }
}

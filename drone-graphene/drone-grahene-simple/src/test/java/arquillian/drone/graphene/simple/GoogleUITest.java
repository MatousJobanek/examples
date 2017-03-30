package arquillian.drone.graphene.simple;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunWith(Arquillian.class)
public class GoogleUITest {

    @Drone
    private WebDriver browser;

    @FindBy(id = "hplogo")
    private WebElement bigLogo;

    @FindBy(id = "logo")
    private WebElement smallLogo;

    @FindBy(id = "lst-ib")
    private WebElement input;

    @Test
    public void openGooglePageTest() {
        browser.get("http://www.google.com");

        Graphene.waitAjax().until().element(bigLogo).is().visible();
        input.sendKeys("arquillian drone");

        Graphene.waitAjax().until().element(smallLogo).is().visible();
    }

}

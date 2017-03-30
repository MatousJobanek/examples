package org.arquillian.drone.graphene.deployment;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunWith(Arquillian.class)
@RunAsClient
public class SimpleDeploymentTest {

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addAsWebResource("index.html");
    }

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @FindBy(tagName = "h1")
    private WebElement title;

    @Test
    public void runSimpleTest() {
        browser.get(deploymentUrl.toString());
        Graphene.waitModel().until().element(title).text().contains("Simple Page");

    }
}

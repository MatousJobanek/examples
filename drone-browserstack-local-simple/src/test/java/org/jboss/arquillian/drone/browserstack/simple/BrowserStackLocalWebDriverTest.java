/**
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.drone.browserstack.simple;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunWith(Arquillian.class)
public class BrowserStackLocalWebDriverTest {

    private static final String USERNAME = "demo";
    private static final String PASSWORD = "demo";

    @Drone
    WebDriver driver;

    @ArquillianResource
    URL contextPath;

    @FindBy(id = "login-status")
    private WebElement loginStatus;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @Deployment(testable = false)
    public static WebArchive deploySample() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addAsWebResource(new File("src/test/resources/form.html"), "form.html")
            .addAsWebResource(new File("src/test/resources/js/jquery-1.8.2.min.js"), "js/jquery-1.8.2.min.js");
    }

    @Test
    @InSequence(1)
    public void login() throws InterruptedException {
        driver.get(contextPath.toString() + "form.html");
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        Graphene.waitAjax().until().element(loginStatus).text().contains("User logged in!");
    }

    @Test
    @InSequence(2)
    public void logout() {
        logoutButton.click();
        Graphene.waitAjax().until().element(loginStatus).text().contains("Not logged in!");
    }
}

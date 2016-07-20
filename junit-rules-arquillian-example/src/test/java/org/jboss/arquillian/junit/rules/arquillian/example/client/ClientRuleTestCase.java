package org.jboss.arquillian.junit.rules.arquillian.example.client;

import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.junit.rules.arquillian.example.rules.RuleWithArqResInStatement;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunAsClient
public class ClientRuleTestCase extends AbstractTestCase {

    @ArquillianResource
    private URL url;

    @Rule
    public TestRule rule = new RuleWithArqResInStatement();

    @Test
    public void urlShouldBeInjected(){
        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));
    }

}

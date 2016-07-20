package org.jboss.arquillian.junit.rules.arquillian.example.client;

import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This test case requires url to be injected before @BeforeRules phase
 * this use-case could be fixed by: https://github.com/MatousJobanek/arquillian-core/commit/98ceda611fd0ae07f999aa9b20442db9faaffc0e
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunAsClient
public class ClientInnerRuleFailingTestCase extends AbstractTestCase {

    @ArquillianResource
    private URL url;

    @Rule
    public TestRule rule = new TestRule() {
        public Statement apply(Statement statement, Description description) {
            Assert.assertNotNull(url);
            Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));

            return statement;
        }
    };

    @Test
    public void urlShouldBeInjected(){
        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));
    }

}

package org.jboss.arquillian.junit.rules.arquillian.example.rules;

import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class RuleWithArqResInStatement implements TestRule {

    @ArquillianResource
    private URL url;

    public Statement apply(final Statement base, final Description description) {

        return new Statement() {
            @Override public void evaluate() throws Throwable {
                Assert.assertNotNull(url);
                Assert.assertThat(url.toString(), CoreMatchers.containsString(AbstractTestCase.DEPLOYMENT_NAME));

                base.evaluate();
            }
        };
    }
}

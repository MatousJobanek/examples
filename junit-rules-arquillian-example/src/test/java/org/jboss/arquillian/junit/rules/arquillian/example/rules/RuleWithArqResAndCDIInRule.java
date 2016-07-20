package org.jboss.arquillian.junit.rules.arquillian.example.rules;

import java.net.URL;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.junit.rules.arquillian.example.SimpleBean;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule requires url to be injected before @BeforeRules phase
 * this use-case could be partially fixed by: https://github.com/MatousJobanek/arquillian-core/commit/98ceda611fd0ae07f999aa9b20442db9faaffc0e
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class RuleWithArqResAndCDIInRule implements TestRule {

    @Inject
    private SimpleBean bean;

    @ArquillianResource
    private URL url;

    public Statement apply(final Statement base, final Description description) {
        // At this point I cannot use the assertion for the CDI bean - this method is run on both the client side and server side
        // so for the first run (when it's run on client side), the bean is null; for the second run it should be already injected
        if (bean == null){
            System.err.println("This one should be displayed in client log");
        } else {
            System.err.println("This one should be displayed in container log");
        }
        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(AbstractTestCase.DEPLOYMENT_NAME));

        return new Statement() {
            @Override public void evaluate() throws Throwable {
                // do whatever you want
                base.evaluate();
            }
        };
    }
}

package org.jboss.arquillian.junit.rules.arquillian.example.container;

import java.net.URL;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.junit.rules.arquillian.example.SimpleBean;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Depends on the fix: https://github.com/arquillian/arquillian-core/pull/110
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class ContainerInnerRuleFixedTestCase extends AbstractTestCase {

    @Inject
    private SimpleBean bean;

    @ArquillianResource
    private URL url;

    @Rule
    public TestRule rule = new TestRule() {
        public Statement apply(final Statement statement, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    // checks whether it is null
                    Assert.assertNotNull(bean);
                    // increments counter
                    bean.increment();
                    Assert.assertEquals(1, bean.getCounter());

                    Assert.assertNotNull(url);
                    Assert.assertThat(url.toString(), CoreMatchers.containsString(AbstractTestCase.DEPLOYMENT_NAME));

                    statement.evaluate();
                }
            };
        }
    };

    @Test
    public void beanAndUrlShouldBeInjected(){
        Assert.assertNotNull(bean);
        // now if you called bean.getCounter(), you could expect 1 as a returned value.
        // But it returns 0 because the bean has been "reinjected" by a new instance
        // This problem can be solved by specifying an appropriate scope for the injected bean.

        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));
    }

}

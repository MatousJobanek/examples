package org.jboss.arquillian.junit.rules.arquillian.example.container;

import java.net.URL;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.junit.rules.arquillian.example.SimpleBean;
import org.jboss.arquillian.junit.rules.arquillian.example.rules.RuleWithArqResAndCDIInStatement;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * Depends on the fix: https://github.com/arquillian/arquillian-core/pull/110
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class ContainerRuleFixedTestCase extends AbstractTestCase {

    @Inject
    private SimpleBean bean;

    @ArquillianResource
    private URL url;

    @Rule
    public TestRule rule = new RuleWithArqResAndCDIInStatement();

    @Test
    public void beanAndUrlShouldBeInjected(){
        Assert.assertNotNull(bean);
        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));
    }

}

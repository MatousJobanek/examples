package org.jboss.arquillian.junit.rules.arquillian.example.container;

import java.net.URL;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.rules.arquillian.example.AbstractTestCase;
import org.jboss.arquillian.junit.rules.arquillian.example.SimpleBean;
import org.jboss.arquillian.junit.rules.arquillian.example.rules.RuleWithArqResAndCDIInRule;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * This test case requires url to be injected before @BeforeRules phase
 * this use-case could be partially fixed by: https://github.com/MatousJobanek/arquillian-core/commit/98ceda611fd0ae07f999aa9b20442db9faaffc0e
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class ContainerRuleFailingTestCase extends AbstractTestCase {

    @Inject
    private SimpleBean bean;

    @ArquillianResource
    private URL url;

    @Rule
    public TestRule rule = new RuleWithArqResAndCDIInRule();

    @Test
    public void beanAndUrlShouldBeInjected(){
        Assert.assertNotNull(bean);
        Assert.assertNotNull(url);
        Assert.assertThat(url.toString(), CoreMatchers.containsString(DEPLOYMENT_NAME));
    }

}

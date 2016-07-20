package org.jboss.arquillian.junit.rules.arquillian.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
@RunWith(Arquillian.class)
public abstract class AbstractTestCase {

    public static final String DEPLOYMENT_NAME = "cdi-test-rule";

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, DEPLOYMENT_NAME + ".war")
            .addClasses(SimpleBean.class)
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}

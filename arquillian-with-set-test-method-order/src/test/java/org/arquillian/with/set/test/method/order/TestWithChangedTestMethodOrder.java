package org.arquillian.with.set.test.method.order;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestWithChangedTestMethodOrder {

    private static int number = 0;

    @Test
    @InSequence(4)
    public void should_run_as_fourth_test(){
        number++;
        Assert.assertEquals(4, number);
    }

    @Test
    @InSequence(3)
    public void should_run_as_third_test(){
        number++;
        Assert.assertEquals(3, number);
    }

    @Test
    @InSequence(1)
    public void should_run_as_first_test(){
        number++;
        Assert.assertEquals(1, number);
    }

    @Test
    @InSequence(2)
    public void should_run_as_second_test(){
        number++;
        Assert.assertEquals(2, number);
    }
}

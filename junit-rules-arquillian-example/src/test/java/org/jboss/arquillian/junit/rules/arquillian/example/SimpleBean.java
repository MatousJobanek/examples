package org.jboss.arquillian.junit.rules.arquillian.example;

import java.io.Serializable;

/**
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class SimpleBean implements Serializable{

    private int counter = 0;

    public SimpleBean(){
    }

    public void increment(){
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

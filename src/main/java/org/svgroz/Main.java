package org.svgroz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by SGrozovsky on 17.08.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        for (;;) {
            Thread.sleep(500);
            context.getBean(JMSSender.class).send();
        }
    }
}

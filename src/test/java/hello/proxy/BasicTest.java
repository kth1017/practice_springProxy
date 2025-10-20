package hello.proxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {
    @Test
    void basicContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);

        A beanA = context.getBean("beanA", A.class);
        beanA.helloA();

        //B는 불가
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> context.getBean(B.class));

    }

    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }


    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }
    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}

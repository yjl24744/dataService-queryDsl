package com.yss.datamiddle.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class HibernatePropertiesAspect {

    @Before(value = "execution(* org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties.*(..)) && args(jpaProperties, settings)")
    public Object doAround(JoinPoint pjp, Map<String, String> jpaProperties, HibernateSettings settings) {
        jpaProperties.put("hibernate.hbm2ddl.auto","none");
        return jpaProperties;
    }
}

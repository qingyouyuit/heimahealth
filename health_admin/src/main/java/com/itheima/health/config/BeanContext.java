package com.itheima.health.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanContext implements ApplicationContextAware, BeanPostProcessor {

    private ApplicationContext myAppContext;

    public BeanContext(){
        System.out.println("构造方法.......");
    }

    //需求:按照bean的名字获取bean对象
    public <T> T getBeanByName(String  beanName){
        return (T)this.myAppContext.getBean(beanName);
    }
    public <T> T getBeanByType(Class clazz){
        return (T)this.myAppContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.myAppContext = applicationContext;
        System.out.println("setApplicationContext方法.......");
    }


    /*@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization方法.......");
        return bean;
    }

    //需求：项目启动期间，更改redisTemplate的 key的序列化
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization方法.......");
        String format = String.format("bean的名字%s", beanName);
        System.out.println(format);
        return bean;
    }*/
}

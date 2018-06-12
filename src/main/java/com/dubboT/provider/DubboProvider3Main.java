package com.dubboT.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider3Main {
    public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider3.xml"});
        context.start();  
  
        System.in.read();  
    }  
}  
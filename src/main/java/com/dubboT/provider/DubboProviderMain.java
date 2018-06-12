package com.dubboT.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProviderMain {
    public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();  
  
        System.in.read();  
    }  
}  
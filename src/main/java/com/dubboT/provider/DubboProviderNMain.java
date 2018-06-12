package com.dubboT.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;
  
/**  
 * Desc:  
 * Mail:v@terminus.io  
 * author:Michael Zhao  
 * Date:2015-03-04.  
 */  
public class DubboProviderNMain {
    public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"providerN.xml"});
        context.start();  
  
        System.in.read();  
    }  
}  
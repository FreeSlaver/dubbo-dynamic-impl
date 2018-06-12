package com.demo.service;

import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Desc:测试Dubbo实时映射bean
 * Mail:v@terminus.io
 * author:Michael Zhao
 * Date:2015-03-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:consumer.xml"})
@Slf4j
public class RealReference {
    //用于将bean关系注入到当前的context中  
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void realReference() {
        String url = "dubbo://localhost:22880/com.demo.service.DemoService";
        //通过url获取Dubbo服务Bean
        getServiceByUrl(url);
        //通过group获取Dubbo服务bean
//        getServiceByGroup("g2");
    }

    private void getServiceByGroup(String group) {
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ReferenceBean<DemoService> referenceBean = new ReferenceBean<DemoService>();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setInterface(com.demo.service.DemoService.class);
        referenceBean.setGroup(group);
        referenceBean.setRegistry(registry);

        try {
            referenceBean.afterPropertiesSet();
            DemoService demoService = referenceBean.get();
            System.out.print(demoService.deal("HAHAHA"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getServiceByUrl(String url) {
        ReferenceBean<DemoService> referenceBean = new ReferenceBean<DemoService>();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setInterface(com.demo.service.DemoService.class);
        referenceBean.setUrl(url);
//        referenceBean.setGroup("g1");

        try {
            referenceBean.afterPropertiesSet();
            DemoService demoService = referenceBean.get();
            System.out.print(demoService.deal("Tester"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  
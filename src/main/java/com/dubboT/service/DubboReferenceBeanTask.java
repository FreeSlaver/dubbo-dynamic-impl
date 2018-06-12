package com.dubboT.service;

import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 001844 on 2018/6/12.
 */
@Service
@Slf4j
public class DubboReferenceBeanTask {
    private static final long FIXED_RATE = 1000 * 60 * 10;
    @Autowired
    private ApplicationContext applicationContext;

    private String groups = "g1,g2,g3";
    //最好将此beanMap放到业务类中
    private Map<String, DemoService> beanMap = new HashMap<>();

    @Scheduled(fixedRate = FIXED_RATE)
    public void refreshDubboBean() {
        if (StringUtils.isEmpty(groups)) {
            log.error("获取Dubbo短信回调接口实例出错，smsCallbackSwitch未配置");
            return;
        }
        String[] groups = this.groups.split(",");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setProtocol("zookeeper");

        ReferenceBean<DemoService> referenceBean = new ReferenceBean<DemoService>();
        referenceBean.setRegistry(registryConfig);
        referenceBean.setInterface(com.demo.service.DemoService.class);
        referenceBean.setApplicationContext(applicationContext);
        for (String group : groups) {
            referenceBean.setGroup(group);
            try {
                referenceBean.afterPropertiesSet();
                DemoService demoService = referenceBean.get();
                beanMap.put(group, demoService);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}

package com.dubboT.service;

import com.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 
 * Desc:Dubbo动态加载服务测试 
 * Mail:v@terminus.io 
 * author:Michael Zhao 
 * Date:2015-03-04. 
 */  
@Service  
@Slf4j  
public class DemoService3Impl implements DemoService {
    @Override  
    public String deal(String s) {  
        return "DemoService3Impl deal " + s;
    }  
}  
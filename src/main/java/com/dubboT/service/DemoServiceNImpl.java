package com.dubboT.service;  
  
import com.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 
 * Desc: 
 * Mail:v@terminus.io 
 * author:Michael Zhao 
 * Date:2015-03-05. 
 */
@Service
@Slf4j
public class DemoServiceNImpl implements DemoService {  
    @Override  
    public String deal(String s) {  
        return "This is DemoServiceNImpl " + s;  
    }  
}  
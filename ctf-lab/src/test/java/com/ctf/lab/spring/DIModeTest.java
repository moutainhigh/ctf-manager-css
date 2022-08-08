package com.ctf.lab.spring;

import com.ctf.lab.spring.DI.ByConstructorService;
import com.ctf.lab.spring.DI.ByDefaultService;
import com.ctf.lab.spring.DI.ByNameService;
import com.ctf.lab.spring.DI.ByTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试4种自动装配模型，默认no,(no,byName,byName,constructor)
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Slf4j
public class DIModeTest {

    @Test
    void noTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/DI/DIModel.xml");
        applicationContext.refresh();
        ByDefaultService no = applicationContext.getBean("no", ByDefaultService.class);
        log.info("装配成功？{}", no.bean==null);
    }


    @Test
    void byName(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/DI/DIModel.xml");
        applicationContext.refresh();
        ByNameService byName = applicationContext.getBean("byName", ByNameService.class);
        log.info("装配成功?：{}",byName.bean != null);
    }

    @Test
    void byType(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/DI/DIModel.xml");
        applicationContext.refresh();
        ByTypeService byType = applicationContext.getBean("byType", ByTypeService.class);
        log.info("装配成功?：{}",byType.bean != null);
    }

    @Test
    void constructor(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/DI/DIModel.xml");
        applicationContext.refresh();
        ByConstructorService constructor = applicationContext.getBean("constructor", ByConstructorService.class);
        log.info("装配成功?：{}",constructor.getBean() != null);
    }



}

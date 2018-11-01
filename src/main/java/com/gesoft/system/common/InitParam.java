package com.gesoft.system.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.gesoft.system.common.Constant.PROJECT_NAME;

@Component
@Slf4j
public class InitParam implements CommandLineRunner {


    @Autowired
    private ApplicationProperties applicationProperties;


    @Override
    public void run(String... args) throws Exception {
        log.info("系统资源 initialize ...");
        PROJECT_NAME = applicationProperties.getProjectName();
    }
}

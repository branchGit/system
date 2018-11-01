package com.gesoft.system.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${server.servlet.context-path}")
    private String projectName;

    public String getProjectName() {
        return projectName;
    }
}

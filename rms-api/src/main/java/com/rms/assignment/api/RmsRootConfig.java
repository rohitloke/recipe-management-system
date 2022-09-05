package com.rms.assignment.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.rms.assignment.core.CoreConfig;

@ComponentScan
@Configuration
@Import(value = { CoreConfig.class })
public class RmsRootConfig {

}

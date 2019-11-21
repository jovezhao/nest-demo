package com.zhaofujun.nest.demo;

import com.zhaofujun.nest.demo.application.PasswordChangedEventData;
import com.zhaofujun.nest.mybatis.paging.PageListPlugin;
import com.zhaofujun.nest.activemq.ActiveMQMessageChannel;
import com.zhaofujun.nest.configuration.EventConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public PageListPlugin getPageListPlugin() {
        return new PageListPlugin();
    }


    @Bean
    public Mapper mapper(@Value(value = "classpath*:dozer/*mapper.xml") Resource[] resourceArray) throws IOException {
        List<String> mappingFileUrlList = new ArrayList<>();
        for (Resource resource : resourceArray) {
            mappingFileUrlList.add(String.valueOf(resource.getURL()));
        }
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFileUrlList);
        return dozerBeanMapper;
    }


    @Bean
    public EventConfiguration changePasswordEvent() {
        EventConfiguration eventConfiguration = new EventConfiguration();
        eventConfiguration.setEventCode(PasswordChangedEventData.EVENT_CODE);
        eventConfiguration.setMessageChannelCode(ActiveMQMessageChannel.CHANNEL_CODE);

        return eventConfiguration;
    }


}

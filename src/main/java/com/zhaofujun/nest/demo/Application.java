package com.zhaofujun.nest.demo;

import com.guohuoxiang.nest.mybatis.pagination.PageListPlugin;
import com.guoshouxiang.nest.activemq.ActiveMQMessageChannel;
import com.guoshouxiang.nest.configuration.EventConfiguration;
import com.guoshouxiang.nest.container.BeanFinder;
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

    @Bean("ACTIVEMQ_CHANNEL")
    public ActiveMQMessageChannel activeMQMessageChannel(BeanFinder beanFinder){
        String brokers="tcp://127.0.0.1:61616";
        ActiveMQMessageChannel messageChannel=new ActiveMQMessageChannel(beanFinder,brokers);
        return messageChannel;
    }

    @Bean("change_password")
    public EventConfiguration changePasswordEvent(){
        EventConfiguration eventConfiguration=new EventConfiguration();
        eventConfiguration.setEventCode("change_password");
        eventConfiguration.setMessageChannelCode("ACTIVEMQ_CHANNEL");

        return eventConfiguration;
    }



}

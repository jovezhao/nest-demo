package com.zhaofujun.nest.demo.context;

import com.guoshouxiang.nest.context.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandLineRunner implements CommandLineRunner {
    @Autowired
    private EventBus eventBus;

    @Override
    public void run(String... args) throws Exception {
        eventBus.autoRegister();
    }
}

package com.guoshouxiang.nest.demo.context.handle;

import com.guoshouxiang.nest.context.event.EventArgs;
import com.guoshouxiang.nest.context.event.EventHandler;
import com.guoshouxiang.nest.demo.application.PasswordChangedEventData;
import org.springframework.stereotype.Component;

@Component("change_password")
public class PasswordChangedHandler implements EventHandler<PasswordChangedEventData> {
    @Override
    public String getEventCode() {
        return "change_password";
    }

    @Override
    public Class<PasswordChangedEventData> getEventDataClass() {
        return PasswordChangedEventData.class;
    }

    @Override
    public void handle(PasswordChangedEventData passwordChangedEventData, EventArgs eventArgs) {
        System.out.println("收到事件" + passwordChangedEventData);
    }
}

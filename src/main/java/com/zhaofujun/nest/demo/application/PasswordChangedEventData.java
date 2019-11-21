package com.zhaofujun.nest.demo.application;

import com.zhaofujun.nest.core.EventData;

public class PasswordChangedEventData extends EventData {
     private String oldPassword;
     private String newPassword;
     private String userId;

     public String getOldPassword() {
         return oldPassword;
     }

     public void setOldPassword(String oldPassword) {
         this.oldPassword = oldPassword;
     }

     public String getNewPassword() {
         return newPassword;
     }

     public void setNewPassword(String newPassword) {
         this.newPassword = newPassword;
     }

     public String getUserId() {
         return userId;
     }

     public void setUserId(String userId) {
         this.userId = userId;
     }

     @Override
     public String getEventCode() {
         return EVENT_CODE;
     }

     public final static String EVENT_CODE="change_password";
 }

package ps.demo.myteststarter.service;

import ps.demo.myteststarter.config.SmsProperties;

public class TencentSmsSenderImpl implements SmsSender {

    private SmsProperties.SmsMessage smsMessage;

    public TencentSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }

    @Override
    public boolean send(String message) {
        System.out.println(smsMessage.toString()+" send message: "+message);
        return true;
    }
}
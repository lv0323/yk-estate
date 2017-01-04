package com.lyun.estate.core.pojos;

public class Sms {
    private String mobile;
    private String templateId;
    private String[] arguments;

    public String getMobile() {
        return mobile;
    }

    public Sms setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Sms setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String[] getArguments() {
        return arguments;
    }

    public Sms setArguments(String[] arguments) {
        this.arguments = arguments;
        return this;
    }


}

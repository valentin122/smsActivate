package ru.zhigalov.sms;

public class SmsPart {
    private int id;
    private int agentId;
    private int phoneFrom;
    private int phoneTo;
    private String text;
    private int part;
    private int imei;
    private String date;
    private boolean status;
    private int smsId;

    public SmsPart(int id, int agentId, int phoneFrom, int phoneTo, String text, int part, int imei, String date, boolean status, int smsId) {
        this.id = id;
        this.agentId = agentId;
        this.phoneFrom = phoneFrom;
        this.phoneTo = phoneTo;
        this.text = text;
        this.part = part;
        this.imei = imei;
        this.date = date;
        this.status = status;
        this.smsId = smsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getPhoneFrom() {
        return phoneFrom;
    }

    public void setPhoneFrom(int phoneFrom) {
        this.phoneFrom = phoneFrom;
    }

    public int getPhoneTo() {
        return phoneTo;
    }

    public void setPhoneTo(int phoneTo) {
        this.phoneTo = phoneTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    @Override
    public String toString() {
        return "SmsPart{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", phoneFrom=" + phoneFrom +
                ", phoneTo=" + phoneTo +
                ", text='" + text + '\'' +
                ", part=" + part +
                ", imei=" + imei +
                ", date='" + date + '\'' +
                ", status=" + status +
                ", smsId=" + smsId +
                '}';
    }
}

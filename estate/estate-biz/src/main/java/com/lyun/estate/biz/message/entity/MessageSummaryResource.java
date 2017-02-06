package com.lyun.estate.biz.message.entity;

/**
 * Created by jesse on 2017/2/6.
 */
public class MessageSummaryResource {
    private Long receiverId;
    private Long senderId;
    private String senderName;
    private String senderImgUrl;
    private Integer unreadCount;
    private Long lastMessageId;
    private String lastMessageTitle;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderImgUrl() {
        return senderImgUrl;
    }

    public void setSenderImgUrl(String senderImgUrl) {
        this.senderImgUrl = senderImgUrl;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getLastMessageTitle() {
        return lastMessageTitle;
    }

    public void setLastMessageTitle(String lastMessageTitle) {
        this.lastMessageTitle = lastMessageTitle;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }
}

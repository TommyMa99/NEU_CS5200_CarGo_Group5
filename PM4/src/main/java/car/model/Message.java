package car.model;

import java.sql.Timestamp;

/**
 * Message model for Cargo.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */

public class Message {
    protected int messageId;
    protected Timestamp sendTime;
    protected String content;
    protected int fromId;
    protected int toId;

    public Message() {
    }

    public Message(int messageId, Timestamp sendTime, String content, int fromId, int toId) {
        this.messageId = messageId;
        this.sendTime = sendTime;
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
    }

    
    /** 
     * @return int
     */
    public int getMessageId() {
        return this.messageId;
    }

    
    /** 
     * @param messageId
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    
    /** 
     * @return Timestamp
     */
    public Timestamp getSendTime() {
        return this.sendTime;
    }

    
    /** 
     * @param sendTime
     */
    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    
    /** 
     * @return String
     */
    public String getContent() {
        return this.content;
    }

    
    /** 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    
    /** 
     * @return int
     */
    public int getFromId() {
        return this.fromId;
    }

    
    /** 
     * @param fromId
     */
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    
    /** 
     * @return int
     */
    public int getToId() {
        return this.toId;
    }

    
    /** 
     * @param toId
     */
    public void setToId(int toId) {
        this.toId = toId;
    }

}

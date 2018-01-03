package cn.jin.web.controller.websocket.entity;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type ResponseMessage
 * @Desc 响应消息实体
 * @Date 2017-12-15 13:25
 */
public class ResponseMessage {

    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


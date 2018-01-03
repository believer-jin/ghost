package cn.jin.web.controller.websocket;

import cn.jin.web.controller.websocket.entity.RequestMessage;
import cn.jin.web.controller.websocket.entity.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type WebSocketController
 * @Desc
 * @Date 2017-12-15 13:26
 */
@Controller
public class WebSocketController {

    public static Map<String, Session> clients= new ConcurrentHashMap<String, Session>();

    /**
     *广播发送
     * @param request
     * @return
     */
    @MessageMapping("/group")
    @SendTo("/queue/getResponse")
    public ResponseMessage sendGroup(RequestMessage request){
        System.out.println(request.getMessage());
        return new ResponseMessage("Hello,world!"+request.getMessage());
    }

    /**
     * 点对点发送
     * @param request
     * @return
     */
    @MessageMapping("/single")
    @SendToUser("/queue/getResponse")
    public ResponseMessage sendSingle(RequestMessage request){
        System.out.println(request.getMessage());
        return new ResponseMessage("Hello,world!"+request.getMessage());
    }



    /**
     *
     * @return
     */
    @GetMapping("/websocket")
    public String webSocket(){
        return "/websocket";
    }

    /**
     * 将数据传回客户端
     * 异步的方式
     * @param myWebsocket
     * @param message
     */
    public static void broadcast(String myWebsocket, String message) {
        if (clients.containsKey(myWebsocket)) {
            clients.get(myWebsocket).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException("[" + myWebsocket +"]Connection does not exist");
        }
    }
}

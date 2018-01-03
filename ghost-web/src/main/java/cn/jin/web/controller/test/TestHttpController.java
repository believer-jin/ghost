package cn.jin.web.controller.test;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type TestHttpController
 * @Desc
 * @Date 2017-10-30 10:39
 */
@RestController
public class TestHttpController {

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @return
     */
    @GetMapping(value = "/testHttp")
    public String testHttp(){
        try {
            int i = 374;
            for(; i >= 0;i--) {
               String msg = restTemplate.getForObject("http://apply.hzcb.gov.cn/apply/app/status/norm/person?pageNo=" + i
                        + "&issueNumber=201708", String.class);
               if(!StringUtils.isEmpty(msg) && (msg.contains("玉红")||msg.contains("5403101413"))){
                   System.out.println("contains 201708");
                   return msg;
               }
            }
            i = 495;
            for(; i >= 0;i--) {
               String msg = restTemplate.getForObject("http://apply.hzcb.gov.cn/apply/app/status/norm/person?pageNo=" + i
                        + "&issueNumber=201709", String.class);
               if(!StringUtils.isEmpty(msg) && (msg.contains("玉红")||msg.contains("5403101413"))){
                   System.out.println("contains 201709");
                   return msg;
               }
            }
            i = 300;
            for(; i >= 0;i--) {
               String msg = restTemplate.getForObject("http://apply.hzcb.gov.cn/apply/app/status/norm/person?pageNo=" + i
                        + "&issueNumber=201710", String.class);
               if(!StringUtils.isEmpty(msg) && (msg.contains("玉红")||msg.contains("5403101413"))){
                   System.out.println("contains 201710");
                   return msg;
               }
            }
        } catch (Exception e) {
            return "exception";
        }
        return "not fount";
    }
}

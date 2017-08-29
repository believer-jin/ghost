package cn.jin.userapi.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Alexander
 * @version 1.0
 * @sine 2017-07-11 22:01
 */
@Component
public class TestSchedule1 {

    private final static Logger LOG = LoggerFactory.getLogger(TestSchedule1.class);

   /* @Scheduled(cron = "0/1 * *  * * ? ")
    public void execute() {
        LOG.error("1111111111");
    }*/
}

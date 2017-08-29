package cn.jin.userapi.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by jin on 2017/5/17.
 */
public class SayHelloCommand extends HystrixCommand<String> {

    private final String _name;
    public SayHelloCommand(String name){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloServiceGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        _name = new String(name);

    }

    @Override
    protected String getFallback() {
        return String.format("[FallBack]Hello %s!", _name);
    }

    @Override
    protected String run() throws InterruptedException {
        Thread.sleep(600);
        return String.format("Hello %s!", _name);
    }
}

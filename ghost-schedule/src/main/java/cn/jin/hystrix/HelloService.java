package cn.jin.userapi.hystrix;

import java.util.concurrent.Future;

/**
 * Created by jin on 2017/5/17.
 */
public class HelloService {

    public static String sayHello(final String name)
    {
        return new SayHelloCommand(name).execute();
    }

    /**
     * call async
     * @param name
     * @return
     */
    public static Future<String> sayHelloAsync(final String name)
    {
        return new SayHelloCommand(name).queue();
    }
}

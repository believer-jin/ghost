package cn.jin.userapi.hystrix;

import com.netflix.hystrix.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jin on 2017/5/17.
 */
public class CommandHttpCall extends HystrixCommand<String> {
    private final String url;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommandHttpCall.class);

    public CommandHttpCall(String url) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.command.http"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.command.http"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.command.http"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerRequestVolumeThreshold(20)
                                        .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000).
                                        withFallbackEnabled(true).
                                        withExecutionIsolationThreadInterruptOnTimeout(true).withExecutionTimeoutInMilliseconds(1500)));
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        logger.info("Execution of Command: url={}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try(CloseableHttpResponse response = httpclient.execute(httpGet)) {
            org.apache.http.HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String total = "";
            String line = bufferedReader.readLine();
            while (line != null){
                total += line;
                line = bufferedReader.readLine();
            }
            return total;
        }
    }

    @Override
    protected String getFallback() {
        return "   failbackFor   " + url;
    }

}

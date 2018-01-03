package cn.jin.userapi.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-30 18:45
 */
public class CommandUsingRequestCache extends HystrixCommand<Boolean> {
    private final int value;

    protected CommandUsingRequestCache(int value) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.value = value;
    }

    @Override
    protected Boolean run() {
        return value == 0 || value % 2 == 0;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }

    public static class UnitTest {

        @Test
        public void testWithoutCacheHits() {
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                assertTrue(new CommandUsingRequestCache(2).execute());
                assertFalse(new CommandUsingRequestCache(1).execute());
                assertTrue(new CommandUsingRequestCache(0).execute());
                assertTrue(new CommandUsingRequestCache(58672).execute());
            } finally {
                context.shutdown();
            }
        }

        @Test
        public void testWithCacheHits() {
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
                CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);

                System.out.print(command2a.execute());
                System.out.print(command2a.isResponseFromCache());
                System.out.print(command2b.execute());
                System.out.print(command2b.isResponseFromCache());
            } finally {
                context.shutdown();
            }

            context = HystrixRequestContext.initializeContext();
            try {
                CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
                System.out.print(command3b.execute());
                System.out.print(command3b.isResponseFromCache());
            } finally {
                context.shutdown();
            }
        }
    }
}

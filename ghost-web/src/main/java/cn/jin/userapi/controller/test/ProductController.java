package cn.jin.userapi.controller.test;

import cn.jin.userapi.hystrix.CommandHttpCall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试hystrix的Controller
 * @author Alexander
 * @since 2017-05-17
 * @version 1.0
 */
@Controller
public class ProductController {

    @RequestMapping("/")
    public String hello(ModelMap map) {
         map.put("msg",new CommandHelloWorld("hystrix").execute());
        return "index";
    }

    @RequestMapping("/hello")
    public String sayHello(ModelMap map){
        map.put("msg",new CommandHelloWorld("你好，世界").execute());
        return "hello";
    }

    @RequestMapping("/product")
    @ResponseBody
    public String getProduct() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for product").execute();
    }

    @RequestMapping("/order")
    @ResponseBody
    public String getOrder() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for order").execute();
    }
    @RequestMapping("/cart")
    @ResponseBody
    public String getCart() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for cart").execute();
    }

    @RequestMapping("/observe")
    @ResponseBody
    public String getObserve() throws InterruptedException {
        Observable<String> productCall = new CommandHttpCall("http://localhost:8091/product").observe();
        Observable<String> orderCall = new CommandHttpCall("http://localhost:8091/order").observe();
        Observable<String> cartCall = new CommandHttpCall("http://localhost:8091/cart").observe();

        List<Observable<String>> result = new ArrayList<>();
        result.add(productCall);
        result.add(orderCall);
        Observable.merge(result).subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("product&order call complete");
                cartCall.subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("cart call complete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String v) {
                        System.out.println("onNext: " + v);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });

        return new CommandHelloWorld("this is content for observe").execute();
    }

    @RequestMapping("/future")
    @ResponseBody
    public String getFuture() throws InterruptedException {

        String msg = "";
        try {
            Future<String> productSyncCall = new CommandHttpCall("http://localhost:8091/product").queue();
            msg = productSyncCall.get()+"</br>";
            Future<String> orderSyncCall = new CommandHttpCall("http://localhost:8091/order").queue();
            msg += orderSyncCall.get()+"</br>";
            Future<String> cartSyncCall = new CommandHttpCall("http://localhost:8091/cart").queue();
            msg += cartSyncCall.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return msg;
    }
}

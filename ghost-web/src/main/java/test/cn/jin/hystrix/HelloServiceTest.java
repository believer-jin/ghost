package test.cn.jin.hystrix;

import cn.jin.userapi.hystrix.HelloService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* HelloService Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 17, 2017</pre> 
* @version 1.0 
*/ 
public class HelloServiceTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: sayHello(final String name) 
* 
*/ 
@Test
public void testSayHello() throws Exception {
    System.out.printf(HelloService.sayHello("world"));;
} 

/** 
* 
* Method: sayHelloAsync(final String name) 
* 
*/ 
@Test
public void testSayHelloAsync() throws Exception { 
//TODO: Test goes here... 
} 


} 

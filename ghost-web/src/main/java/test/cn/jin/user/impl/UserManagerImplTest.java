/*
package test.cn.jin.user.impl;

import cn.jin.WebApplication;
import cn.jin.userapi.WebApplication;
import cn.jin.userapi.configuration.AppConfig;
import cn.jin.userapi.configuration.HystrixConfiguration;
import cn.jin.userapi.user.UserManager;
import cn.jin.userapi.utils.HystrixCommonUtils;
import com.netflix.hystrix.HystrixInvokableInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

*/
/**
* UserManagerImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 30, 2017</pre> 
* @version 1.0 
*//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class})
public class UserManagerImplTest {
    private final static Logger LOG = LoggerFactory.getLogger(UserManagerImplTest.class);
    @Resource
    private UserManager userManager;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

*/
/**
* 
* Method: save(UserDO user) 
* 
*//*

@Test
public void testSave() throws Exception { 
//TODO: Test goes here... 
} 

*/
/**
* 
* Method: saveAsync(final UserDO user) 
* 
*//*

@Test
public void testSaveAsync() throws Exception { 
//TODO: Test goes here... 
} 

*/
/**
* 
* Method: getUserById(@CacheKey Long id) 
* 
*//*

@Test
public void testGetUserById() throws Exception { 

} 

*/
/**
* 
* Method: saveReactive(final UserDO user) 
* 
*//*

@Test
public void testSaveReactive() throws Exception { 
//TODO: Test goes here... 
} 

*/
/**
* 
* Method: getUserByIdAsync(String id) 
* 
*//*

@Test
public void testGetUserByIdAsync() throws Exception {
    userManager.getUserById(123L);
    HystrixInvokableInfo<?> getUserByIdCommand = HystrixCommonUtils.getLastExecutedCommand();
    LOG.error("Is use cache;{}",getUserByIdCommand.isResponseFromCache());
    userManager.getUserById(123L);
    HystrixInvokableInfo<?> getUserByIdCommand2 = HystrixCommonUtils.getLastExecutedCommand();
    LOG.error("Is use cache;{}",getUserByIdCommand2.isResponseFromCache());
} 

*/
/**
* 
* Method: getUserByIdReact(String id) 
* 
*//*

@Test
public void testGetUserByIdReact() throws Exception { 
//TODO: Test goes here... 
} 

*/
/**
* 
* Method: getUserByIds(List<String> ids) 
* 
*//*

@Test
public void testGetUserByIds() throws Exception { 
//TODO: Test goes here... 
} 


*/
/**
* 
* Method: fallback() 
* 
*//*

@Test
public void testFallback() throws Exception { 
//TODO: Test goes here... 
*/
/*
try { 
   Method method = UserManagerImpl.getClass().getMethod("fallback"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*//*

} 

*/
/**
* 
* Method: syncFallBack(UserDO user, Throwable e) 
* 
*//*

@Test
public void testSyncFallBack() throws Exception { 
//TODO: Test goes here... 
*/
/*
try { 
   Method method = UserManagerImpl.getClass().getMethod("syncFallBack", UserDO.class, Throwable.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*//*

} 

*/
/**
* 
* Method: AsyncFallback(UserDO user) 
* 
*//*

@Test
public void testAsyncFallback() throws Exception { 
//TODO: Test goes here... 
*/
/*
try { 
   Method method = UserManagerImpl.getClass().getMethod("AsyncFallback", UserDO.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*//*

} 

} 
*/

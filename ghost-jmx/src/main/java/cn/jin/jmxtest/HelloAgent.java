package cn.jin.jmxtest;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type HelloAgent
 * @Desc
 * @Date 2017-11-01 19:20
 */
public class HelloAgent {

    public static void main(String[] args)
            throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException,
            MBeanRegistrationException, IOException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String domainName = "MyMBean";
        ObjectName helloName = new ObjectName(domainName+":name=HelloWorld");
        mbs.registerMBean(new Hello(),helloName);
        ObjectName adapterName = new ObjectName(domainName+":name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.start();
        mbs.registerMBean(adapter,adapterName);

        int rmiPort = 1099;
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/"+domainName);
        JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        jmxConnector.start();
    }
}

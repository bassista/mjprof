package com.performizeit.jmxsupport;

import com.performizeit.mjstack.dataSource.JmxDataSourcePlugin;
import com.performizeit.mjstack.dataSource.JstackDataSourcePlugin;
import junit.framework.TestCase;

import org.junit.Test;

import javax.management.openmbean.CompositeData;


public class JMXConnectionTest extends TestCase {
    @Test
    public void testJMXConnection() throws Exception {
 //       JstackDataSourcePlugin  jdsp = new JstackDataSourcePlugin(70782,100,1000);
   //     jdsp.getThreadDumps();
       /* int pid = 6960;
        JMXConnection c = new JMXConnection(new Integer(pid).toString());
        long[] threadsIds = c.getThreadIds();
        CompositeData[] d = c.getThreads(threadsIds,10000);
        CompositeData thread = d[0] ;
        String name = (String) thread.get("threadName");

        CompositeData[] stackTraceElems = (CompositeData []) thread.get("stackTrace");   */


    }
}

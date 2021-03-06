package com.performizeit.mjstack.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Set;

import com.performizeit.mjstack.mappers.GroupByProp;
import com.performizeit.mjstack.terminals.ListProps;
import org.junit.Test;
import org.reflections.Reflections;

import com.performizeit.mjstack.MJStack;
import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.mappers.StackFrameContains;
import com.performizeit.mjstack.mappers.TrimTop;
import com.performizeit.mjstack.monads.StepInfo;

public class PluginTest {
	String stck = "\"qtp188618231-14\" prio=10 tid=0x0007fd8d8d5b000 nid=0xd17 waiting for monitor entry [0x00007fd8ae207000]\n" +
			"   java.lang.Thread.State: BLOCKED (on object monitor)\n" +
			"       at org.apache.hadoop.hdfs.DFSUtil.<clinit>(DFSUtil.java:128)\n" +
			"       at org.apache.hadoop.hdfs.DFSClient.<init>(DFSClient.java:437)\n" +
			"       at org.apache.hadoop.hdfs.DFSClient.<init>(DFSClient.java:410)\n" +
			"       at org.apache.hadoop.hdfs.DistributedFileSystem.initialize(DistributedFileSystem.java:127)\n" +
			"       at org.apache.hadoop.fs.FileSystem.createFileSystem(FileSystem.java:2273)\n" +
			"       at org.apache.hadoop.fs.FileSystem.access$200(FileSystem.java:86)\n" +
			"       at org.apache.hadoop.fs.FileSystem$Cache.getInternal(FileSystem.java:2307)\n" +
			"       at org.apache.hadoop.fs.FileSystem$Cache.get(FileSystem.java:2289)\n" +
			"       at org.apache.hadoop.fs.FileSystem.get(FileSystem.java:316)\n" +
			"       at com.akkka.aaa.bbbb.rest.FileSystemFactory.provide(FileSystemFactory.java:32)\n" +
			"       at com.akkka.aaa.bbb.rest.FileSystemFactory.provide(FlsFactory.java:44)\n";
	String stck2 =
			"       at org.jvnet.hk2.internal.FactoryCreator.create(FactoryCreator.java:56)\n" +
					"       at org.jvnet.hk2.internal.SystemDescriptor.create(SystemDescriptor.java:456)\n" +
					"       at org.jvnet.hk2.internal.PerLookupContext.findOrCreate(PerLookupContext.java:69)\n" +
					"       at org.jvnet.hk2.internal.Utilities.createService(Utilities.java:2350)\n" +
					"       at org.jvnet.hk2.internal.ServiceLocatorImpl.getService(ServiceLocatorImpl.java:580)\n" +
					"       at org.jvnet.hk2.internal.ThreeThirtyResolver.resolve(ThreeThirtyResolver.java:77)\n" +
					"       at org.jvnet.hk2.internal.ClazzCreator.resolve(ClazzCreator.java:208)\n" +
					"       at org.jvnet.hk2.internal.ClazzCreator.resolveAllDependencies(ClazzCreator.java:225)\n" +
					"       at org.jvnet.hk2.internal.ClazzCreator.create(ClazzCreator.java:329)\n" +
					"       at org.jvnet.hk2.internal.SystemDescriptor.create(SystemDescriptor.java:456)\n" +
					"       at org.glassfish.jersey.process.internal.RequestScope.findOrCreate(RequestScope.java:158)\n" +
					"       at org.jvnet.hk2.internal.Utilities.createService(Utilities.java:2350)\n" +
					"       at org.jvnet.hk2.internal.ServiceLocatorImpl.getService(ServiceLocatorImpl.java:612)\n\n";

	@Test
	public void testPlugin() throws Exception {
		HashMap<String, Class<?>> map =new HashMap<String, Class<?>>();
        assertNotNull(PluginUtils.getAllPlugins().get(GroupByProp.class));
	}

	@Test
	public void testInitObject()  throws Exception{
		Object[] intObjArray = new Object[]{1};
		Object obj = PluginUtils.initObj(TrimTop.class, new Class[]{int.class},intObjArray);
		assertEquals(TrimTop.class ,obj.getClass());

		Object[] strObjArray = new Object[]{new String("cc")};
		obj = PluginUtils.initObj(StackFrameContains.class, new Class[]{String.class},strObjArray);
		assertEquals(StackFrameContains.class ,obj.getClass());
		
	}

	@Test
	public void testSynopsis() throws Exception{
		assertTrue(MJStack.getSynopsisString().contains("Synopsis"));
		assertTrue(MJStack.getSynopsisString().contains(ListProps.class.getAnnotation(Plugin.class).description()));

	}
	
	@Test
	public void testGetDtaSourcePlugins()  throws Exception{
		HashMap<String, StepInfo> repo = new HashMap<String, StepInfo>();
		Reflections reflections = new Reflections("com.performizeit");
		Set<Class<?>> annotatedPlugin = reflections.getTypesAnnotatedWith(Plugin.class);
		for(Class cla: annotatedPlugin){
			Plugin pluginAnnotation = (Plugin) cla.getAnnotation(Plugin.class);
			StepInfo stepInit = new StepInfo(cla, pluginAnnotation.params(),pluginAnnotation.description());
			repo.put(pluginAnnotation.name(), stepInit);
		}
	}
}

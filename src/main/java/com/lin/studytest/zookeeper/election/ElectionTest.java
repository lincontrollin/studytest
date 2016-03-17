package com.lin.studytest.zookeeper.election;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ElectionTest {
	
	final static String connectString = 
			"192.168.56.101:2181,192.168.56.101:2180,192.168.56.101:2182";
	
	static String parantNodePath = "/election";
	
	static ZooKeeper zk ;
	
	static int sleepTime = 1000 * 500; 
	
	public static void main(String[] args) throws Exception{
		
//		Integer isconnection = 0;
		zk = new ZooKeeper(connectString, 5000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if(event.getState().equals(KeeperState.SyncConnected)){
					MyElectionNode node = new MyElectionNode(zk, parantNodePath);
					try{
						node.init();
					}catch(Exception e){
						System.out.println(e);
					}
				}
				
			}
		});
		
		Thread.sleep(sleepTime);
	}

}

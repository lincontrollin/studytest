package com.lin.studytest.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooKeeperClient implements Runnable{
	
	protected String host = "192.168.56.101:2181";
	
	protected ZooKeeper zk ;
	
	public void run(){
		try{
			zk = new ZooKeeper(host, 2000, new Watcher(){
				@Override
				public void process(WatchedEvent event) {
					System.out.println("event:::"+event);
				}
			});
			String znode = zk.create("/currentMaster", "masters".getBytes(), 
					Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println("create:"+znode);
		}catch(Exception e){
		}
	}
	
	public static void main(String[] args)throws Exception {
		
		Thread [] threads = new Thread[3];
		for(int i=0;i<3;i++){
			threads[i] = new Thread(new ZooKeeperClient());
			threads[i].start();
		}
		Thread.sleep(3000);
		Thread.sleep(1000000);
	}

}

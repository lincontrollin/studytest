package com.lin.studytest.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperClient {
	
	protected String host = "192.168.56.101:2181";
	
	protected ZooKeeper zk ;
	
	public void run()throws Exception{
		zk = new ZooKeeper(host, 2000, new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event);
			}
		});
		zk.ex
	}
	
	public static void main(String[] args) {
		
		
		
	}

}

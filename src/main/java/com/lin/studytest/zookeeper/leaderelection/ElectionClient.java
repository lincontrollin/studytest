package com.lin.studytest.zookeeper.leaderelection;

import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ElectionClient {
	
	public String host = "192.168.56.101:2181,192.168.56.101:2180,192.168.56.101:2182";
	
	protected ZooKeeper zk;
	
	public int cilentId;
	
	public String nodeName;
	
	public String nodePre = "n_";
	
	public String nodeParentName = "/election";
	
	ElectionDelWatcher edw = null;
	
	public ElectionClient(int id) {
		this.cilentId = id;
	}
	
	public void run(){
		try{
			final ElectionClient _this = this;
			zk = new ZooKeeper(host, 5000, new Watcher(){
				@Override
				public void process(WatchedEvent event) {
					System.out.println("event::::::::"+event);
					if(event.getState().equals(KeeperState.SyncConnected)){
//						zk.create("/election/"+nodePre, "who".getBytes(), 
//							Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,new StringCallback() {
//								@Override
//								public void processResult(int rc, String path, Object ctx, String name) {
//									System.out.println("create===>{code="+rc+",name="+name+",path="+path);
//									try{
//										switch (rc) {
//										case Code.Ok:
//											nodeName = name.substring((nodeParentName+"/").length()-1);
//											edw = new ElectionDelWatcher(_this);
//											break;
//										default:
//											break;
//										}
//									}catch(Exception e){
//										System.out.println(e);
//									}
//								}
//							},null);
						
					}
				}
			
			});
			String nodeName = zk.create("/election/"+nodePre, "who".getBytes(), 
							Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(nodeName);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
//	public String createNode(String nodeStr , byte[] bytes)throws Exception{
//		
//	}
	
	public static void main(String[] args) throws Exception{
		
		new ElectionClient(1).run();
		Thread.sleep(1000000);
	}

}

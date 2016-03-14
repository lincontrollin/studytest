package com.lin.studytest.zookeeper.leaderelection;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class ElectionDelWatcher implements Watcher{
	
	private ElectionClient client;
	
	public ElectionDelWatcher(ElectionClient client) {
		this.client = client;
		try{
			List<String> childPaths = this.client.zk.getChildren(
					this.client.nodeParentName, false);
			
			getMinAndWatch(childPaths);
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	private void getMinAndWatch(List<String> children)throws Exception{
		for(String path:children){
			this.client.zk.exists(this.client.nodeParentName+"/"+path, this);
		}
	}
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("ElectionDelWatcher====>{event:"+event);
		try{
			if(event.getType().equals(EventType.NodeDeleted)){
				List<String> children = this.client.zk.getChildren(this.client.nodeParentName, false);
				String nodePre = this.client.nodePre;
				int lostSeq = NumberUtils.toInt(event.getPath().substring(nodePre.length()-1));
				int mySeq = NumberUtils.toInt(this.client.nodeName.substring(nodePre.length()-1));
				boolean shoulbeme = true;
				for(String path:children){
					int seq = NumberUtils.toInt(path.substring(nodePre.length()-1));
					if(seq == lostSeq){
						continue;
					}
					if(mySeq>seq){
						shoulbeme = false;
						break;
					}
				}
				// if it is me 
				if (shoulbeme) {
					this.client.zk.setData(this.client.nodeName, (""+mySeq).getBytes(), 1);
					System.out.println("i am leader");
				}
				
			}
		}catch(Exception e){
			
		}
		
		
	}
	

}

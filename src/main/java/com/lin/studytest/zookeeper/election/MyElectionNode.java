package com.lin.studytest.zookeeper.election;

import org.apache.zookeeper.ZooKeeper;

public class MyElectionNode extends AbstractElectionNode {
	
	public MyElectionNode(ZooKeeper zooKeeper,String parantNodePath) {
		super(zooKeeper, parantNodePath);
	}
	
	@Override
	protected void electLeader() throws Exception {
		System.out.println("i am leader,node="+this.nodeName);
	}
	
	

}

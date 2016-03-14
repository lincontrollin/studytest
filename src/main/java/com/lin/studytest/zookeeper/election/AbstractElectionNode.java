package com.lin.studytest.zookeeper.election;

import org.apache.zookeeper.ZooKeeper;

public class AbstractElectionNode implements ElectionNode{
	
	/**
	 * 节点名称
	 */
	protected String nodeName;
	
	/**
	 * 节点路径
	 */
	protected String nodePath;
	
	/**
	 * 父节点路径
	 */
	protected String parentNodePath;
	
	/**
	 * 节点顺序号
	 */
	protected int seqNum;
	
	/**
	 * 选举策略（默认最小seq算法）
	 */
	protected ElectionStrategy electionStrategy = ElectionStrategy.DEFUALT_ELECTIONSTRATEGY;
	
	protected ZooKeeper zookeeper;
	
	
	@Override
	public void elect() {
		
	}
	
	
	

}

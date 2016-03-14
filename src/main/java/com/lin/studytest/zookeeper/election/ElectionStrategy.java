package com.lin.studytest.zookeeper.election;

import com.lin.studytest.zookeeper.exception.EmptyChildrenException;

public interface ElectionStrategy {
	
	ElectionStrategy DEFUALT_ELECTIONSTRATEGY = new ElectionMinStrategy();
	
	/**
	 * 根据算法选举出几点
	 * @param node 当前节点
	 * @return 节点名称
	 */
	boolean electAndResult(AbstractElectionNode node)throws EmptyChildrenException;

}

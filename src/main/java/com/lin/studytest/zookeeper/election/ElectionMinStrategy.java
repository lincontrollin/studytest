package com.lin.studytest.zookeeper.election;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.KeeperException;

import com.lin.studytest.zookeeper.exception.EmptyChildrenException;

/**
 * @author lin
 * @date   2016年3月14日
 * @version 1.0
 * 选取seq最小的节点
 */
public class ElectionMinStrategy implements ElectionStrategy {
	
	@Override
	public boolean electAndResult(AbstractElectionNode node)
			throws EmptyChildrenException,InterruptedException,KeeperException{
		List<String> children = node.getChildren();
		if(CollectionUtils.isEmpty(children)){
			throw new EmptyChildrenException();
		}
		for(String child:children){
			if(node.leaderNodeName!=null && child.equals(node.leaderNodeName))
				continue;
			if(ElectionUtil.fetchSeq(child)<node.seqNum)
				return false;
		}
		return true;
	}

}

package com.lin.studytest.zookeeper.election;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.lin.studytest.zookeeper.exception.EmptyChildrenException;

/**
 * @author lin
 * @date   2016年3月14日
 * @version 1.0
 * 选取seq最小的节点
 */
public class ElectionMinStrategy implements ElectionStrategy {
	
	@Override
	public boolean electAndResult(AbstractElectionNode node) throws EmptyChildrenException{
//		if(CollectionUtils.isEmpty(children)){
//			throw new EmptyChildrenException();
//		}
//		int mySeqNum = 
//		for(String child:children){
//			if(child.equals(leader))
//				continue;
//			
//		}
		return false;
	}

}

package com.sadasen.finance.modules.record.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.record.dto.RecordPara;
import com.sadasen.finance.modules.record.entity.Record;
import com.sadasen.finance.modules.record.vo.RecordInfo;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface RecordDao extends BaseMapper<Record> {
	
	public List<RecordInfo> selectInfoListByCondition(RecordPara para);
	
	public List<String> selectAfter30(RecordPara para);
	
	public int selectSum(@Param("userId") long userId, @Param("type") int type);

}

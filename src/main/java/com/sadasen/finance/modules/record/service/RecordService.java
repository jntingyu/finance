package com.sadasen.finance.modules.record.service;

import java.util.List;

import com.sadasen.finance.modules.record.dto.RecordPara;
import com.sadasen.finance.modules.record.entity.Record;
import com.sadasen.finance.modules.record.vo.RecordInfo;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface RecordService {
	
	public Record save(Record record);
	
	public List<RecordInfo> findInfoListByCondition(RecordPara para);

}

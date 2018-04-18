package com.sadasen.finance.modules.record.service.impl;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.record.dao.RecordDao;
import com.sadasen.finance.modules.record.dto.RecordPara;
import com.sadasen.finance.modules.record.entity.Record;
import com.sadasen.finance.modules.record.service.RecordService;
import com.sadasen.finance.modules.record.vo.RecordInfo;
import com.sadasen.util.CollectionUtil;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private RecordDao recordDao;

	@Override
	public Record save(Record record) {
		int r = sqlManager.insertTemplate(record, true);
		if(1==r) {
			return record;
		}
		return null;
	}

	@Override
	public List<RecordInfo> findInfoListByCondition(RecordPara para) {
		List<String> dateList = recordDao.selectAfter30(para);
		if(!CollectionUtil.isEmpty(dateList)) {
			para.setStartDate(dateList.get(dateList.size()-1));
		}
		return recordDao.selectInfoListByCondition(para);
	}

}

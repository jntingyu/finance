package com.sadasen.finance.modules.statistics.service.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.statistics.dao.StatisticsDao;
import com.sadasen.finance.modules.statistics.dto.StatsPara;
import com.sadasen.finance.modules.statistics.service.StatisticsService;
import com.sadasen.finance.modules.statistics.vo.StatisticsInfo;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsDao statisticsDao;

	@Override
	public long findTotalToday(long userId, int type) {
		return statisticsDao.selectTotalToday(userId, type);
	}

	@Override
	public long getTotalMonth(StatsPara para) {
		para.setStartDate(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).toString());
		para.setEndDate(LocalDate.now().toString());
		return statisticsDao.selectTotalByTimeArea(para);
	}

	@Override
	public long getAvgMonth(StatsPara para) {
		return getTotalMonth(para)/LocalDate.now().getDayOfMonth();
	}

	@Override
	public long getTotalAll(StatsPara para) {
		return statisticsDao.selectTotalAll(para);
	}

	@Override
	public long getAvgAll(StatsPara para) {
		return statisticsDao.selectAvgAll(para);
	}

	@Override
	public List<StatisticsInfo> getEveryMonth(StatsPara para) {
		return statisticsDao.selectEveryMonth(para);
	}
	
	@Override
	public List<StatisticsInfo> getConsumeTotal(StatsPara para) {
		return statisticsDao.selectConsumeTotal(para);
	}
	
	@Override
	public List<StatisticsInfo> getBaseConsumeTotal(StatsPara para) {
		return statisticsDao.selectBaseConsumeTotal(para);
	}

}

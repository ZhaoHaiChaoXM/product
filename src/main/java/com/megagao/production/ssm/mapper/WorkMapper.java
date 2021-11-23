package com.megagao.production.ssm.mapper;

import java.util.List;

import com.megagao.production.ssm.domain.Work;
import com.megagao.production.ssm.domain.WorkExample;
import com.megagao.production.ssm.domain.vo.WorkVO;
import org.apache.ibatis.annotations.Param;

public interface WorkMapper {
	
	//扩展的mapper接口方法
	int deleteBatch(String[] ids);
	
	List<Work> find();

	List<Work> searchWorkByWorkId(String workId);

	List<Work> searchWorkByWorkProduct(String workProduct);

	List<Work> searchWorkByWorkDevice(String workDevice);

	List<Work> searchWorkByWorkProcess(String workProcess);
	
	//逆向工程生成的mapper接口
    int countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(String workId);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    WorkVO selectByPrimaryKey(String workId);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}
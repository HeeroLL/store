package com.zebone.btp.transaction.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.transaction.mapper.DeptInfoMapper;
import com.zebone.btp.transaction.pojo.DeptInfo;

@Service("deptInfoService")
public class DeptInfoServiceImpl implements DeptInfoService{
	@Resource(name = "deptInfoMapper")
	private DeptInfoMapper deptMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public void add() {
		DeptInfo dept = new DeptInfo();
		dept.setDeptId("0008");
		dept.setActorId("0001");
		dept.setCreateTime(new Date());

		dept.setDelFlag((short) 1);
		dept.setDeptName("为爱冲动");
		dept.setOrganId("0001");
		int result = deptMapper.insert(dept);
		if (result > 0) {
			System.out.println("增加数据成功");
		} else {
			System.out.println("增加数据失败");
		}
	}

	public DeptInfo get(String id){
		DeptInfo dept = null;
		dept = deptMapper.findById(id);
		return dept;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update() {
		DeptInfo dept = null;
		dept = deptMapper.findById("0004");
		dept.setDeptName("更新数据");
		int result = deptMapper.updateById(dept);
		if (result > 0) {
			System.out.println("更新数据成功");
		} else {
			System.out.println("更新数据失败");
		}
	}

	public void delete(String id ){
		int result = deptMapper.deleteById(id);
		if(result > 0){
			System.out.println("删除数据成功");
		}else{
			System.out.println("删除数据失败");
		}
	}
	
	@Override
	@Transactional
	public void saveTwoDept(){
		DeptInfo dept = new DeptInfo();
		dept.setDeptId("0008");
		dept.setActorId("0001");
		dept.setCreateTime(new Date());
		dept.setDeptName("为爱冲动");
		dept.setOrganId("0001");
		dept.setDelFlag((short)1);
		deptMapper.insert(dept);
		
		DeptInfo dept2 = new DeptInfo();
		dept2.setDeptId("0008");
		dept2.setActorId("0002");
		dept2.setCreateTime(new Date());
		dept2.setDeptName("为爱冲动2");
		dept2.setOrganId("0002");
		dept2.setDelFlag((short)1);
		deptMapper.insert(dept2);
		
//		if(1<2){
//			throw new RuntimeException();
//		}
	}
	
	
}

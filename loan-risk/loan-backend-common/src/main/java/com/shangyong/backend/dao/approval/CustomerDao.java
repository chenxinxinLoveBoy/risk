package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.approval.Customer;


@Mapper
public interface CustomerDao {

	public Customer getCustomerById(String customerId);
	

}

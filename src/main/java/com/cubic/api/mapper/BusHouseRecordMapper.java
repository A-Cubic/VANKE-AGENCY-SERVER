package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseRecord;

public interface BusHouseRecordMapper extends MyMapper<BusHouseRecord> {
	
	 /**
     * 添加跟进信息
     * @param busHouseRecord
     * 
     * */
   void insertHouseRecord(BusHouseRecord busHouseRecord);
   
   /**
    * 设置跟进信息为置顶
    * @param busHouseRecord
    * 
    * */
   void updateHouseRecordIsTopOne(BusHouseRecord busHouseRecord);
   
   /**
    * 取消跟进信息置顶
    * @param busHouseRecord
    * 
    * */
   void updateHouseRecordIsTopZero(BusHouseRecord busHouseRecord);
   
   /**
    * 查询跟进信息
    * @param map
    * 
    * */
   List<BusHouseRecord> listHouseRecord(Map<String,Object> map);
}
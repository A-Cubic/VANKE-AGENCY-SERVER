package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusGuestRecord;
import com.cubic.api.model.BusGuestRecord;

public interface BusGuestRecordMapper extends MyMapper<BusGuestRecord> {
	 /**
     * 添加跟进信息
     * @param busGuestRecord
     * 
     * */
   void insertGuestRecord(BusGuestRecord busGuestRecord);
   
   /**
    * 设置跟进信息为置顶
    * @param busGuestRecord
    * 
    * */
   void updateGuestRecordIsTopOne(BusGuestRecord busGuestRecord);
   
   /**
    * 取消跟进信息置顶
    * @param busGuestRecord
    * 
    * */
   void updateGuestRecordIsTopZero(BusGuestRecord busGuestRecord);
   
   /**
    * 查询跟进信息
    * @param map
    * 
    * */
   List<BusGuestRecord> listGuestRecord(Map<String,Object> map);
}
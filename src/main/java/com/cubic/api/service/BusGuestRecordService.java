package com.cubic.api.service;

import com.cubic.api.model.BusGuestRecord;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/16
 */
public interface BusGuestRecordService extends Service<BusGuestRecord> {
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

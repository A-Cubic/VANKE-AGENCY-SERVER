package com.cubic.api.util;

/**
 * 房源、客源、用户编号生成工具
 * type:H房源,G客源,VK用户
 * @author fei.yu
 * @date 2018/07/12
 */
public class NumberUtil {
	public static String geoEquipmentNo(String type,long equipmentNo){
        String newEquipmentNo = "000000001";
        long newEquipment = equipmentNo;
        newEquipmentNo = String.format(type + "%09d", newEquipment);
        return newEquipmentNo;
    }
}

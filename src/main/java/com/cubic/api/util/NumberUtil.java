package com.cubic.api.util;

/**
 * 房源、客源、用户编号生成工具
 *
 * @author fei.yu
 * @date 2018/07/12
 */
public class NumberUtil {
	public static String geoHouseEquipmentNo(long equipmentNo){
        String newEquipmentNo = "000000001";
        long newEquipment = equipmentNo + 1;
        newEquipmentNo = String.format("H" + "%09d", newEquipment);
        return newEquipmentNo;
    }
	
	public static String geoGuestEquipmentNo(long equipmentNo){
        String newEquipmentNo = "000000001";
        long newEquipment = equipmentNo + 1;
        newEquipmentNo = String.format("G" + "%09d", newEquipment);
        return newEquipmentNo;
    }
	
	public static String geoUserEquipmentNo(long equipmentNo){
        String newEquipmentNo = "000000001";
        long newEquipment = equipmentNo + 1;
        newEquipmentNo = String.format("VK" + "%09d", newEquipment);
        return newEquipmentNo;
    }

}

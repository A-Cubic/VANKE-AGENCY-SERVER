package com.cubic.api.util;

public class NumberUtil {
	public static String geoHouseEquipmentNo(String equipmentNo){
        String newEquipmentNo = "000000001";

        if(equipmentNo != null && !equipmentNo.isEmpty()){
            int newEquipment = Integer.parseInt(equipmentNo) + 1;
            newEquipmentNo = String.format("H" + "%09d", newEquipment);
        }
        return newEquipmentNo;
    }
	
	public static String geoGuestEquipmentNo(String equipmentNo){
        String newEquipmentNo = "000000001";

        if(equipmentNo != null && !equipmentNo.isEmpty()){
            int newEquipment = Integer.parseInt(equipmentNo) + 1;
            newEquipmentNo = String.format("G" + "%09d", newEquipment);
        }
        return newEquipmentNo;
    }
	
	public static String geoUserEquipmentNo(String equipmentNo){
        String newEquipmentNo = "000000001";

        if(equipmentNo != null && !equipmentNo.isEmpty()){
            int newEquipment = Integer.parseInt(equipmentNo) + 1;
            newEquipmentNo = String.format("VK" + "%09d", newEquipment);
        }
        return newEquipmentNo;
    }

}

package com.cubic.api.component;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cubic.api.model.BusGuest;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.service.BusHouseService;

@Component
public class ScheduleTaskServer {
	@Resource
	private BusHouseService busHouseservice;
	@Resource
	private BusGuestService busGuestService;
	
	@Scheduled(fixedRate = 1000*60) //测试使用：每隔10秒执行一次
//     @Scheduled(cron = "0 0 6 * * ?") //正式使用：每天早6点执行一次
    public void doSharedPoolTask() {
		//房源共享池查询及变更
		List<BusHouse> busHouselist = busHouseservice.listRecordTime();
        if(0!=busHouselist.size()){
        	for(BusHouse busHouse:busHouselist){
        		BusHouse busHouseNew=new BusHouse();
        		if(busHouse.getCasetype().equals("1")){
        			
        		}else if(busHouse.getCasetype().equals("2")){
        			busHouseNew.setId(busHouse.getId());
        			busHouseNew.setIsshare("1");
        			busHouseservice.update(busHouseNew);
        		}
        	}
        	
        }
        
        //客源共享池查询及变更
        List<BusGuest> busGuestlist=busGuestService.listRecordTime();
        if(0!=busGuestlist.size()){
        	for(BusGuest busGuest:busGuestlist){
	        	BusGuest busGuestNew=new BusGuest();
	    		if(busGuest.getCasetype().equals("1")){
	    			
	    		}else if(busGuest.getCasetype().equals("2")){
	    			busGuestNew.setId(busGuest.getId());
	    			busGuestNew.setIsshare("1");
	    			busGuestService.update(busGuestNew);
	    		}
        	}
        }
    }
}

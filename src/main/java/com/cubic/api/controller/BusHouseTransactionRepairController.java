package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.model.BusHouseTransactionRepair;
import com.cubic.api.service.BusHouseTransactionRepairService;
import com.cubic.api.service.BusHouseTransactionService;

/**
 * @author cubic
 * @date 2018/08/02
 */
@RestController
@RequestMapping("/vanke/house/transaction/repair")
public class BusHouseTransactionRepairController {
	@Resource
	private BusHouseTransactionService busHouseTransactionService;
    @Resource
    private BusHouseTransactionRepairService busHouseTransactionRepairService;
    /**
     * 补交未缴金额
     * @param busHouseTransactionRepair
     * */
    @PreAuthorize("hasAuthority('transactionrepair:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusHouseTransactionRepair busHouseTransactionRepair) {
    	
    	
    	if(busHouseTransactionRepair.getBuyIntermediaryPayment()==null|| "".equals(busHouseTransactionRepair.getBuyIntermediaryPayment())){
    		busHouseTransactionRepair.setBuyIntermediaryPayment("0");    		
    	}
    	if(busHouseTransactionRepair.getSellIntermediaryPayment()==null||"".equals(busHouseTransactionRepair.getSellIntermediaryPayment())){
    		busHouseTransactionRepair.setSellIntermediaryPayment("0");
    	}   	
    	if(busHouseTransactionRepair.getBuyLoanPayment()==null||"".equals(busHouseTransactionRepair.getBuyLoanPayment())){   		
    		busHouseTransactionRepair.setBuyLoanPayment("0");
    	}
    	busHouseTransactionRepair.setUserName(user.getName());
    	busHouseTransactionRepairService.insertTransactionRepair(busHouseTransactionRepair);
    	//查询成交信息
    	BusHouseTransaction busHouseTransaction =busHouseTransactionService.findById(busHouseTransactionRepair.getTransactionId());
    	BusHouseTransaction busHouseTransactionNew=new BusHouseTransaction();
    	
    	busHouseTransactionNew.setId(busHouseTransaction.getId());
    
    	//本次补交金额
    	int repairBuyIntermediary=Integer.valueOf(busHouseTransactionRepair.getBuyIntermediaryPayment());
        int repairSellIntermediary=Integer.valueOf(busHouseTransactionRepair.getSellIntermediaryPayment());
    	int repairBuyLoan=Integer.valueOf(busHouseTransactionRepair.getBuyLoanPayment());

        //获取原实缴金额
    	int buyIntermediaryPayment=Integer.valueOf(busHouseTransaction.getBuyIntermediaryPayment());
        int sellIntermediaryPayment=Integer.valueOf(busHouseTransaction.getSellIntermediaryPayment());
        int buyLoanPayment=Integer.valueOf(busHouseTransaction.getBuyLoanPayment());
        //原实缴金额加上补交金额
        int payment1=buyIntermediaryPayment+repairBuyIntermediary;
        int payment2=sellIntermediaryPayment+repairSellIntermediary;
        int payment3=buyLoanPayment+repairBuyLoan;
        //变更实缴金额
        busHouseTransactionNew.setBuyIntermediaryPayment(String.valueOf(payment1));
        busHouseTransactionNew.setSellIntermediaryPayment(String.valueOf(payment2));
        busHouseTransactionNew.setBuyLoanPayment(String.valueOf(payment3));
        //未补金额
        int buyIntermediaryLack=Integer.valueOf(busHouseTransaction.getBuyIntermediaryLack());
        int sellIntermediaryLack=Integer.valueOf(busHouseTransaction.getSellIntermediaryLack());
        int buyLoanLack=Integer.valueOf(busHouseTransaction.getBuyLoanLack());
        
        //未补金额减去本次补的金额
        int lack1=buyIntermediaryLack-repairBuyIntermediary;
        int lack2=sellIntermediaryLack-repairSellIntermediary;
        int lack3=buyLoanLack-repairBuyLoan;
        //变更未补
        busHouseTransactionNew.setBuyIntermediaryLack(String.valueOf(lack1));
        busHouseTransactionNew.setSellIntermediaryLack(String.valueOf(lack2));
        busHouseTransactionNew.setBuyLoanLack(String.valueOf(lack3));
        //如何全都补齐状态改为已交清
        if(lack1 <= 0 && lack2 <= 0 && lack3 <= 0){
        	busHouseTransactionNew.setType("1");
        	
        }
        //更新成交记录
        busHouseTransactionService.update(busHouseTransactionNew);
        
    	return ResultGenerator.genOkResult("1");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseTransactionRepairService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseTransactionRepair busHouseTransactionRepair) {
    	busHouseTransactionRepairService.update(busHouseTransactionRepair);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseTransactionRepair busHouseTransactionRepair = busHouseTransactionRepairService.findById(id);
        return ResultGenerator.genOkResult(busHouseTransactionRepair);
    }
    
    /**
     * 查询补交记录
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('transactionrepair:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) throws ParseException {
    
        List<BusHouseTransactionRepair> list = busHouseTransactionRepairService.ListTransactionRepair(map);
	       for(BusHouseTransactionRepair bean:list){
	       	    //转换时间格式
	        	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	Date date = fmt.parse(bean.getCreateTime());
		   		String  sre= fmt.format(date);	   		
		   		bean.setCreateTime(sre);   	   
	       }       

        return ResultGenerator.genOkResult(list);
    }
}

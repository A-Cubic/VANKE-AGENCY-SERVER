package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.util.NumberUtil;
import com.cubic.api.util.OSSUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.assertj.core.util.Arrays;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/25
 */
@RestController
@RequestMapping("/vanke/house/transaction")
public class BusHouseTransactionController {
    @Resource
    private BusHouseTransactionService busHouseTransactionService;
    /**
     * 创建成交信息
     * @param busHouseTransaction
     * */
    @PreAuthorize("hasAuthority('transaction:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusHouseTransaction busHouseTransaction) {
    	//判断缴费状态
    	if("0".equals(busHouseTransaction.getBuyIntermediaryLack()) && "0".equals(busHouseTransaction.getSellIntermediaryLack()) && "0".equals(busHouseTransaction.getBuyLoanLack())){
    		
    		busHouseTransaction.setType("1");
    	}else{
    		busHouseTransaction.setType("0");
    	}
    	
    	//添加完成状态
    	busHouseTransaction.setState("1");
    	//录入人
    	busHouseTransaction.setCreateUserName(user.getName());
    	
    	if(busHouseTransaction.getContractImgList()!=null && busHouseTransaction.getContractImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getContractImgList()){
    			
    		
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//合同图片
    		busHouseTransaction.setContractImg(urlImg.toString());
    	}
    	
    	if(busHouseTransaction.getHouseProveImgList()!=null && busHouseTransaction.getHouseProveImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getHouseProveImgList()){
    			
    	
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//产权证图片
    		busHouseTransaction.setHouseProveImg(urlImg.toString());
    	}
    	
      	if(busHouseTransaction.getLoanContractImgList()!=null && busHouseTransaction.getLoanContractImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getLoanContractImgList()){
    			
    
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//贷款图片
    		busHouseTransaction.setLoanContractImg(urlImg.toString());
    	}
      	
      	if(busHouseTransaction.getIdentityProveImgList()!=null && busHouseTransaction.getIdentityProveImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getIdentityProveImgList()){
    			
    
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//身份证图片
    		busHouseTransaction.setIdentityProveImg(urlImg.toString());
    	}
      	
       	if(busHouseTransaction.getReceiptImgList()!=null && busHouseTransaction.getReceiptImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getReceiptImgList()){
    			
    			
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//收据图片
    		busHouseTransaction.setReceiptImg(urlImg.toString());
    	}
       	
     	if(busHouseTransaction.getAgreementImgList()!=null && busHouseTransaction.getAgreementImgList().size()!=0){
    		StringBuffer urlImg=new StringBuffer();
    		for(String url:busHouseTransaction.getAgreementImgList()){
    			
    	
    			urlImg.append(OSSUtil.uploadOSSToInputStream(url,"deal")+",");
    			
    		}
    		//补充协议图片
    		busHouseTransaction.setAgreementImg(urlImg.toString());
    	}
    	
    	busHouseTransactionService.insertTransaction(busHouseTransaction);
    	//根据添加的id生成成交编号
    	String num = NumberUtil.geoEquipmentNo("C",busHouseTransaction.getId());
    	BusHouseTransaction busHouseTransactionNew=new BusHouseTransaction();
    	busHouseTransactionNew.setId(busHouseTransaction.getId());
    	busHouseTransactionNew.setNumber(num);
    	busHouseTransactionService.update(busHouseTransactionNew);
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseTransactionService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseTransaction busHouseTransaction) {
    	busHouseTransactionService.update(busHouseTransaction);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseTransaction busHouseTransaction = busHouseTransactionService.findById(id);
        return ResultGenerator.genOkResult(busHouseTransaction);
    }
    /**
     * 查询成交记录
     * @param map
     * */
    @PreAuthorize("hasAuthority('transaction:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        map.put("username", user.getName());
        if(user.toString().indexOf("ROLE_USER")!=-1){//经济人看自己的成交记录
        	map.put("roel","2");
        	
        }
    	List<BusHouseTransaction> list = busHouseTransactionService.listTransaction(map);
        
        for(BusHouseTransaction busHouseTransaction:list){
        	
        	//合同图片
        	if(busHouseTransaction.getContractImg()!=null &&!"".equals(busHouseTransaction.getContractImg())){
        		StringBuffer urlImg=new StringBuffer();
        		urlImg.append(busHouseTransaction.getContractImg());
        		String str[]=urlImg.toString().split(",");
        		  List<String> numlist=new ArrayList<String>();
				for(int i=0;i<str.length;i++){
					numlist.add(str[i]);
				}

        	  
        		busHouseTransaction.setContractImgList(numlist);
        	}
        	//补充协议图片
         	if(busHouseTransaction.getAgreementImg()!=null &&!"".equals(busHouseTransaction.getAgreementImg())){
         		StringBuffer urlImg=new StringBuffer();
				
				urlImg.append(busHouseTransaction.getAgreementImg());
        		String str[]=urlImg.toString().split(",");
        		  List<String> numlist=new ArrayList<String>();
				for(int i=0;i<str.length;i++){
					numlist.add(str[i]);
				}
        		busHouseTransaction.setAgreementImgList(numlist);
        	}
         	//收据图片
         	if(busHouseTransaction.getReceiptImg()!=null &&!"".equals(busHouseTransaction.getReceiptImg())){
	    		   StringBuffer urlImg=new StringBuffer();				
					urlImg.append(busHouseTransaction.getReceiptImg());
	        		String str[]=urlImg.toString().split(",");
	        		  List<String> numlist=new ArrayList<String>();
					for(int i=0;i<str.length;i++){
						numlist.add(str[i]);
					}
        		busHouseTransaction.setReceiptImgList(numlist);
        	}
         	
         	//身份证图片
         	if(busHouseTransaction.getIdentityProveImg()!=null &&!"".equals(busHouseTransaction.getIdentityProveImg())){
        		
				
				   StringBuffer urlImg=new StringBuffer();				
					urlImg.append(busHouseTransaction.getIdentityProveImg());
	        		String str[]=urlImg.toString().split(",");
	        		  List<String> numlist=new ArrayList<String>();
					for(int i=0;i<str.length;i++){
						numlist.add(str[i]);
					}
        		busHouseTransaction.setIdentityProveImgList(numlist);
        	}
         	//贷款图片
          	if(busHouseTransaction.getLoanContractImg()!=null &&!"".equals(busHouseTransaction.getLoanContractImg())){
        		
				
				   StringBuffer urlImg=new StringBuffer();				
					urlImg.append(busHouseTransaction.getLoanContractImg());
	        		String str[]=urlImg.toString().split(",");
	        		  List<String> numlist=new ArrayList<String>();
					for(int i=0;i<str.length;i++){
						numlist.add(str[i]);
					}
        		busHouseTransaction.setLoanContractImgList(numlist);
        	}
          	//房产证图片
          	if(busHouseTransaction.getHouseProveImg()!=null &&!"".equals(busHouseTransaction.getHouseProveImg())){
        		
				
				   StringBuffer urlImg=new StringBuffer();				
					urlImg.append(busHouseTransaction.getHouseProveImg());
	        		String str[]=urlImg.toString().split(",");
	        		  List<String> numlist=new ArrayList<String>();
					for(int i=0;i<str.length;i++){
						numlist.add(str[i]);
					}
        		busHouseTransaction.setHouseProveImgList(numlist);
        	}
         	
        	
        }
        PageInfo<BusHouseTransaction> pageInfo = new PageInfo<BusHouseTransaction>(list);
        
        return ResultGenerator.genOkResult(pageInfo);
    }
}

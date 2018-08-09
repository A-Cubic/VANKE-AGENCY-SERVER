package com.cubic.api.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.Store;
import com.cubic.api.service.StoreService;

/**
 * @author cubic
 * @date 2018/07/10
 */
@RestController
@RequestMapping("/vanke/store")
public class StoreController {
	@Resource
	private StoreService storeService;
	/**
	 * 创建门店
	 * @param store
	 * */
	@PreAuthorize("hasAuthority('store:insert')")
	@PostMapping("/insert")
	public Result add(@RequestBody Store store) {
		//验证是否存在
		Store storeNew=storeService.storeIF(store);
		if(storeNew!=null){
			
			return ResultGenerator.genOkResult("0");
		}
		storeService.insertBaseStore(store);
		if(store.getStreetId()!=null&&store.getStreetId().size()!=0){
			
		
			//添加门店范围
			for(String id:store.getStreetId()){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("storeId", store.getId());
				map.put("streetId", id);
				storeService.insertStoreRange(map);
			}
		}
		return ResultGenerator.genOkResult("1");
	}

//	@DeleteMapping("/{id}")
//	public Result delete(@PathVariable Long id) {
//		storeService.deleteById(id);
//		return ResultGenerator.genOkResult();
//	}
	
	/**
	 * 门店信息修改
	 * */
	@PreAuthorize("hasAuthority('store:update')")
	@PostMapping("/update")
	public Result update(@RequestBody Store store) {
//		storeService.updateStore(store);
	
		if(store.getId()!=null){	
			
			if(store.getStreetId()!=null&&store.getStreetId().size()!=0){
				//修改范围前删除原有范围
				storeService.deleteStoreRange(String.valueOf(store.getId()));
				
				//添加门店范围
				for(String id:store.getStreetId()){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("storeId", store.getId());
					map.put("streetId", id);
					storeService.insertStoreRange(map);
				}
			}
		}
		return ResultGenerator.genOkResult();
	}
	/**
	 * 查询门店详情(带范围)
	 * */
	@PreAuthorize("hasAuthority('store:detail')")
	@PostMapping("/detail")
	public Result detail(@RequestBody  Map<String,Object> map) {
		Store store = storeService.detailStoreInFo(map);
		return ResultGenerator.genOkResult(store);
	}
	/**
	 * 查询全部
	 * */
	@PreAuthorize("hasAuthority('store:list')")
	@PostMapping("/list")
	public Result list(Principal user) {
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("username", user.getName());
		
	  	  if(user.toString().indexOf("ROLE_LEADER")!=-1){ //经理
	  		map.put("role", "5");
		   	}else if(user.toString().indexOf("ROLE_SEC")!=-1){//助理
		   		map.put("role", "3");
		   	}else if (user.toString().indexOf("ROLE_ADMIN")!=-1){
		   		map.put("role", "1");
		   	}
		List<Store> list = storeService.ListBaseStore(map);
		return ResultGenerator.genOkResult(list);
	}
	
	
	/**
	 * 查询全部
	 * */
	@PostMapping("/listStore")
	public Result listStore(Principal user) {
		List<Store> list = storeService.findAll();
		return ResultGenerator.genOkResult(list);
	}
	/**
	 * 查询全部(门店列表显示)系统管理员可看
	 * */
	@PreAuthorize("hasAuthority('store:listStoreAll')")
	@PostMapping("/listStoreAll")
	public Result listStoreAll(Principal user){
		
		List<Store> list = storeService.findAll();
		return ResultGenerator.genOkResult(list);
	}
}

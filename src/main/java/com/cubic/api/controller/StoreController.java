package com.cubic.api.controller;

import java.security.Principal;
import java.util.HashMap;
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

	@PostMapping
	public Result add(@RequestBody Store store) {
		storeService.save(store);
		return ResultGenerator.genOkResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long id) {
		storeService.deleteById(id);
		return ResultGenerator.genOkResult();
	}

	@PutMapping
	public Result update(@RequestBody Store store) {
		storeService.update(store);
		return ResultGenerator.genOkResult();
	}

	@GetMapping("/{id}")
	public Result detail(@PathVariable Long id) {
		Store store = storeService.findById(id);
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
}

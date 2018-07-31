package com.cubic.api.controller;

import java.util.List;

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
	public Result list() {
		List<Store> list = storeService.findAll();
		return ResultGenerator.genOkResult(list);
	}
}

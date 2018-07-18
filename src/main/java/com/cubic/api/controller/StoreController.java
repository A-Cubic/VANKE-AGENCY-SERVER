package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.Store;
import com.cubic.api.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

	@GetMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<Store> list = storeService.findAll();
		PageInfo<Store> pageInfo = new PageInfo<Store> (list);
		return ResultGenerator.genOkResult(pageInfo);
	}
}

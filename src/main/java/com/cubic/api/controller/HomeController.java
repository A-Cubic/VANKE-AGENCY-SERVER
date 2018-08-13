package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.HomeService;
import com.cubic.api.service.UserService;
import com.cubic.api.util.OSSUtil;

/**
 * 首页
 * 
 * @author fei.yu
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/home")
public class HomeController {
	@Resource
	private HomeService service;
	@Resource
	private UserService userService;

	/**
	 * 首页用户信息
	 * 
	 */
	@PostMapping("/user/info")
	public Result currentUser(Principal users) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", users.getName());
		if(users.toString().indexOf("ROLE_ADMIN")!=-1){
			map.put("role", "1");
		} else if(users.toString().indexOf("ROLE_USER")!=-1){
			map.put("role", "2");
		} else if(users.toString().indexOf("ROLE_SEC")!=-1){
			map.put("role", "3");
		} else if(users.toString().indexOf("ROLE_MANAGER")!=-1){
			map.put("role", "4");
		}else if(users.toString().indexOf("ROLE_LEADER")!=-1){
			map.put("role", "5");
		}
		CurrentUser user = service.currentUser(map);
		List<BusHouse> list =new ArrayList<BusHouse>();
		//经济人或者助理只能看自己的,系统管理员可以看所有人的
		if(users.toString().indexOf("ROLE_ADMIN")!=-1|| users.toString().indexOf("ROLE_USER")!=-1 || users.toString().indexOf("ROLE_SEC")!=-1){			
			list = service.listLatentScore(map);
		}else{//如果为店长或者店经理的就可以看本店的
			list = service.listLatentScoreStore(map);
		}
	
		int latent = 0;
		// 计算潜在业绩
		for (BusHouse busHouse : list) {
			double percent = 0;
			if("2".equals(map.get("role")) || "3".equals(map.get("role"))){
				
			
				if (users.getName().equals(busHouse.getCreateUserName())) {
					percent = percent + 0.1;
				}
				if (users.getName().equals(busHouse.getRecordUserName())) {
					percent = percent + 0.1;
				}
				if (users.getName().equals(busHouse.getExplorationUserName())) {
					percent = percent + 0.1;
	
				}
				if (users.getName().equals(busHouse.getKeyUserName())) {
					percent = percent + 0.05;
				}
			}else if ("1".equals(map.get("role")) || "4".equals(map.get("role")) || "5".equals(map.get("role"))){
				
				if (busHouse.getCreateUserName()!=null) {
					percent = percent + 0.1;
				}
				if (busHouse.getRecordUserName()!=null) {
					percent = percent + 0.1;
				}
				if (busHouse.getExplorationUserName()!=null) {
					percent = percent + 0.1;
	
				}
				if (busHouse.getKeyUserName()!=null) {
					percent = percent + 0.05;
				}
			}
			// 计算单个房子的潜在业绩=(房源报价*百分之3)*角色占比(最大百分之35)
			int result = (int) ((int) (Integer.parseInt(busHouse.getPrice()) * 0.03) * percent);
			latent = latent + result;
		}
		// 潜在业绩
		user.setLatent_score(String.valueOf(latent));
		return ResultGenerator.genOkResult(user);
	}

	/**
	 * 个人信息
	 * 
	 * @throws ParseException
	 * 
	 */
	@PostMapping("/user/findMyinfo")
	public Result findMyinfo(final Principal user) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", user.getName());
		CurrentUser users = service.findMyinfo(map);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = fmt.parse(users.getRegister_time());
		String sre = fmt.format(date);
		users.setRegister_time(sre);
		return ResultGenerator.genOkResult(users);
	}

	@PostMapping("/user/updatePassword")
	public Result updatePassword(final Principal user, @RequestBody Map<String, Object> map) {
		map.put("username", user.getName());
		map.put("userBean", userService.findBy("username", user.getName()));
		return ResultGenerator.genOkResult(service.updatePassword(map));
	}

	/**
	 * 修改用户信息(用户姓名,用户电话)
	 */
	@PostMapping("/user/upadateInfo")
	public Result upadateInfo(final Principal user, @RequestBody User users) {
		users.setUsername(user.getName());
		service.upadateInfo(users);
		return ResultGenerator.genOkResult("修改成功");
	}

	/**
	 * 首页排行榜
	 * 
	 */
	@PostMapping("/score/list")
	public Result listScore(final Principal user, @RequestBody Map<String, Object> map) {
//		PageHelper.startPage(Integer.valueOf(map.get("page").toString()), Integer.valueOf(map.get("size").toString()));
		if(user.toString().indexOf("ROLE_ADMIN")==-1 && user.toString().indexOf("ROLE_LEADER")==-1 ){
			map.put("userName", user.getName());
			map.put("role", "2");
		}
		List<CurrentScore> list = service.listRankings(map);
		
		for(CurrentScore score:list){
			//如果没有业绩等于0
			if(score.getScore()==null || "".equals(score.getScore())){
				score.setScore_value("0");
				
			}
		}
		
//		PageInfo<CurrentScore> pageInfo = new PageInfo<CurrentScore>(list);
		return ResultGenerator.genOkResult(list);
	}

	/**
	 * 首页优质房源
	 * 
	 */
	@PostMapping("/house/list")
	public Result listHouseGood() {
//        List<BaseXiaoQu> list = service.listXiaoQu();
		return ResultGenerator.genOkResult();
	}

	/**
	 * 首页最新资讯 暂无
	 */
	@PostMapping("/news/list")
	public Result listNews() {
		return ResultGenerator.genOkResult();
	}

	/**
	 * 修改头像
	 */
	@PostMapping("/user/upload/avatar")
	public Result uploadimg(final Principal user,@RequestBody Map<String, String> map) {
		String base64 = map.get("file");
		String url = OSSUtil.uploadOSSToInputStream(base64, "user");
		service.upadateImg(url, user.getName());
		return ResultGenerator.genOkResult(url);
	}
	@PostMapping("/user/newHouseList")
	public Result newHouseList(){
		List<BusHouse> list=service.newHouseList();
		return ResultGenerator.genOkResult(list);
	}
	/**
	 * 店长看本店量化
	 * 
	 * */
	@PreAuthorize("hasAuthority('homescore:myStoreRankings')")
	@PostMapping("/score/myStoreRankings")
	public Result myStoreRankings(final Principal user,@RequestBody Map<String, Object> map){
		if(user.toString().indexOf("ROLE_MANAGER")!=-1){			
			map.put("username", user.getName());
		}
		List<CurrentScore> list=service.myStoreRankings(map);
		for(CurrentScore score:list){
			//计算带看积分
			if(score.getMyStoreLookList()!=null&&score.getMyStoreLookList().size()>0){
				int sum=0;
				int sumScoreLook=0;
				for(Map<String,Object> maplist:score.getMyStoreLookList()){
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate cr=LocalDate.now();
					//按照时间计算
					if(maplist.get("createTime")!=null&&!"".equals(maplist.get("createTime").toString())){
						LocalDate cr2=LocalDate.parse(maplist.get("createTime").toString(),fmt);
						if("1".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=7){
								int looknum=Integer.valueOf(maplist.get("countScore").toString());
								sum=sum+looknum;
								if(looknum==1){
									sumScoreLook=sumScoreLook+2;
								}else if(looknum==2){
									sumScoreLook=sumScoreLook+3;
								}else if(looknum>=3){
									sumScoreLook=sumScoreLook+4;
								}
							}
						}
						if("2".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=30){
								int looknum=Integer.valueOf(maplist.get("countScore").toString());
								sum=sum+looknum;
								if(looknum==1){
									sumScoreLook=sumScoreLook+2;
								}else if(looknum==2){
									sumScoreLook=sumScoreLook+3;
								}else if(looknum>=3){
									sumScoreLook=sumScoreLook+4;
								}
							}
						}
					}else{
						
						break;
					}
					
				}
				//把带看积分合计到总积分
				score.setLookrecordScore(String.valueOf(sum));
				String sumtext=String.valueOf(Integer.valueOf(score.getSumScore())+sumScoreLook);
				score.setSumScore(sumtext);
			}
			//计算钥匙积分
			if(score.getMyStoreKeyList()!=null&&score.getMyStoreKeyList().size()>0){
				int sumKey=0;
				for(Map<String,Object> maplist:score.getMyStoreKeyList()){
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate cr=LocalDate.now();
					//按照时间计算
					if(maplist.get("createTime")!=null&&!"".equals(maplist.get("createTime"))){
						LocalDate cr2=LocalDate.parse(maplist.get("createTime").toString(),fmt);
						if("1".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=7){
								sumKey=sumKey+Integer.valueOf(maplist.get("keyScore").toString());
							}
						}
						if("2".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=30){
								sumKey=sumKey+Integer.valueOf(maplist.get("keyScore").toString());
							}
						}
					}else{
						
						break;
					}
				}
				//合计到总积分
				score.setKeyScore(String.valueOf(sumKey));
				String sumtext=String.valueOf(Integer.valueOf(score.getSumScore())+(Integer.valueOf(score.getKeyScore())*2));
				score.setSumScore(sumtext);
				
			}
			//置空
			score.setMyStoreLookList(null);
			score.setMyStoreKeyList(null);
		}
		//重新排序
		list.sort((CurrentScore a, CurrentScore b) -> Integer.valueOf(a.getSumScore())+Integer.valueOf(b.getSumScore()));
		//重置排名
		for(int i=0;i<list.size();i++){
			list.get(i).setRank(String.valueOf(i+1));
		}
		return ResultGenerator.genOkResult(list);
	}
	
	
	/**
	 * 所有门店的量化
	 * 
	 * */
    @PreAuthorize("hasAuthority('homescore:storeAllRankings')")
	@PostMapping("/score/storeAllRankings")
	public Result storeAllRankings(final Principal user,@RequestBody Map<String, Object> map){
		if(user.toString().indexOf("ROLE_ADMIN")==-1 && user.toString().indexOf("ROLE_LEADER")==-1){			
			return ResultGenerator.genOkResult("0");
		}
		List<CurrentScore> list=service.storeAllRankings(map);
		for(CurrentScore score:list){
			//计算带看积分
			if(score.getStoreAllLookList()!=null&&score.getStoreAllLookList().size()>0){
				int sumLook=0;
				int sumScoreLook=0;
				for(Map<String,Object> maplist:score.getStoreAllLookList()){
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate cr=LocalDate.now();
					//按照时间计算
					if(maplist.get("createTime")!=null&&!"".equals(maplist.get("createTime"))){
						LocalDate cr2=LocalDate.parse(maplist.get("createTime").toString(),fmt);
						if("1".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=7){
								
								int looknum=Integer.valueOf(maplist.get("countScore").toString());
								sumLook=sumLook+looknum;
								if(looknum==1){
									sumScoreLook=sumScoreLook+2;
								}else if(looknum==2){
									sumScoreLook=sumScoreLook+3;
								}else if(looknum>=3){
									sumScoreLook=sumScoreLook+4;
								}
							}
						}
						if("2".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=30){
								
								int looknum=Integer.valueOf(maplist.get("countScore").toString());
								sumLook=sumLook+looknum;
								if(looknum==1){
									sumScoreLook=sumScoreLook+2;
								}else if(looknum==2){
									sumScoreLook=sumScoreLook+3;
								}else if(looknum>=3){
									sumScoreLook=sumScoreLook+4;
								}
							}
						}
					}else{
						
						break;
					}
					
				}
				//把带看合计到总积分
				score.setLookrecordScore(String.valueOf(sumLook));
				String sumtext=String.valueOf(Integer.valueOf(score.getSumScore())+sumScoreLook);
				score.setSumScore(sumtext);
			}
			//钥匙积分计算
			if(score.getStoreAllKeyList()!=null&&score.getStoreAllKeyList().size()>0){
				int sumKey=0;
				for(Map<String,Object> maplist:score.getStoreAllKeyList()){
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate cr=LocalDate.now();
					
					//按照时间计算
					if(maplist.get("createTime")!=null&&!"".equals(maplist.get("createTime"))){
						LocalDate cr2=LocalDate.parse(maplist.get("createTime").toString(),fmt);
						if("1".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=7){
								sumKey=sumKey+Integer.valueOf(maplist.get("keyScore").toString());
							}
						}
						if("2".equals(map.get("type").toString())){
							long num=cr.toEpochDay()-cr2.toEpochDay();
							if(num<=30){
								sumKey=sumKey+Integer.valueOf(maplist.get("keyScore").toString());
							}
						}
					}else{
						
						break;
					}
				}
				//合计到总积分
				score.setKeyScore(String.valueOf(sumKey));
				String sumtext=String.valueOf(Integer.valueOf(score.getSumScore())+(Integer.valueOf(score.getKeyScore())*2));
				score.setSumScore(sumtext);
			}
			//置空
			score.setStoreAllLookList(null);
			score.setStoreAllKeyList(null);
			
		}
		//重新排序
		list.sort((CurrentScore a, CurrentScore b) -> Integer.valueOf(a.getSumScore())+Integer.valueOf(b.getSumScore()));
		//重置排名
		for(int i=0;i<list.size();i++){
			list.get(i).setRank(String.valueOf(i+1));
		}
		return ResultGenerator.genOkResult(list);
	}

}

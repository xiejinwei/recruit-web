package com.recruit.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.recruit.entity.base.Slider;
import com.recruit.entity.company.Recommend;
import com.recruit.entity.position.Position;
import com.recruit.entity.position.Positiontype;
import com.recruit.service.business.PositionService;
import com.recruit.service.business.PositiontypeService;
import com.recruit.service.business.RecommendService;
import com.recruit.service.business.SliderService;
import com.recruit.util.Page;

/**
 * 首页控制器
 * 
 * @author xiejinwei
 * 
 * @since 2015-06-12
 * 
 */

@Controller
public class HomeController {

	@Autowired
	private PositiontypeService positiontypeService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private SliderService sliderService;
	@Autowired
	private RecommendService recommendService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		// 岗位类型
		List<Positiontype> ptypes = positiontypeService
				.findListByParams("from Positiontype pt where pt.status=1 and pt.level=0  order by pt.sort");
		model.addAttribute("ptypes", ptypes);
		List<Positiontype> types = positiontypeService
				.findListByParams("from Positiontype pt where pt.status=1 and pt.level>0 order by pt.sort");
		model.addAttribute("types", types);
		//所有岗位
		List<Position> positions = positionService.list("from Position");
		model.addAttribute("positions", positions);
		// 轮播
		List<Slider> sliders = sliderService.pageList(
				"from Slider s where s.status=1 and s.type=0 ", new Page(1, 3),
				"order by s.sort");
		model.addAttribute("sliders", sliders);
		//公司推荐
		List<Recommend> recommends = recommendService.pageList("from Recommend r", new Page(1, 6), "order by r.createtime desc");
		model.addAttribute("recommends", recommends);
		return "web/home";
	}

	/**
	 * 全局搜索
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/seach")
	public String seach(String name) {
		return "web/list";
	}
}

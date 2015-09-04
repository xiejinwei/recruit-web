package com.recruit.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.entity.base.Slider;
import com.recruit.exception.RBCException;
import com.recruit.service.business.SliderService;
import com.recruit.util.HTMLUtil;
import com.recruit.util.Page;
import com.recruit.util.StringUtil;

@Controller
@RequestMapping("/manage/slider")
public class SliderController {

	@Autowired
	private SliderService sliderService;

	@RequestMapping("/list")
	public String list(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "type", defaultValue = "-1") int type) {

		Page page = new Page(pageNo, pageSize);
		List<Slider> list = sliderService.pageList(
				"from Slider s where s.status<>-9", page, "order by s.sort",
				" and s.type=:type", type == -1 ? null : type);
		model.addAttribute("list", list);
		String url = StringUtil.getRequestGetUrl(request, "type", type);
		model.addAttribute("url", HTMLUtil.getNumberPageHTML(page, url));
		return "manage/slider/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "manage/slider/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(
			HttpServletRequest request,
			Slider slider,
			@RequestParam(value = "imgfile", required = false) MultipartFile imgfile,
			@RequestParam(value = "smimgfile", required = false) MultipartFile smimgfile) {
		if (imgfile.isEmpty())
			throw new RBCException("轮播图不能为空");
		if (smimgfile.isEmpty())
			throw new RBCException("轮播小图不能为空");
		sliderService.add(request, slider, imgfile, smimgfile);
		return "redirect:list";
	}

	@RequestMapping("/audit")
	public String audit(String id) {
		sliderService.audit(id);
		return "redirect:list";
	}

	@RequestMapping("/unaudit")
	public String unaudit(String id) {
		sliderService.unaudit(id);
		return "redirect:list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, String id) {
		Slider slider = sliderService.findById(id, Slider.class);
		if (slider == null)
			throw new RBCException("没有找到要操作的数据");
		if (slider.getStatus() != 0)
			throw new RBCException("只能对未使用的数据进行修改");
		model.addAttribute("slider", slider);
		return "manage/slider/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			HttpServletRequest request,
			Slider slider,
			@RequestParam(value = "imgfile", required = false) MultipartFile imgfile,
			@RequestParam(value = "smimgfile", required = false) MultipartFile smimgfile) {
		sliderService.updateSlider(request, slider, imgfile, smimgfile);
		return "redirect:list";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(String id) {
		sliderService.deleteSlider(id);
		return "success";
	}

}

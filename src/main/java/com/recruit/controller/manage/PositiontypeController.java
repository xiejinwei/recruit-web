package com.recruit.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruit.entity.position.Positiontype;
import com.recruit.exception.RBCException;
import com.recruit.service.business.PositiontypeService;
import com.recruit.util.HTMLUtil;
import com.recruit.util.Page;
import com.recruit.util.StringUtil;

/**
 * 职位类型控制器
 * 
 * @author xiejinwei
 * 
 */

@Controller
@RequestMapping("/manage/positiontype")
public class PositiontypeController {

	@Autowired
	private PositiontypeService positiontypeService;

	/**
	 * 获取职位类型分页列表
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 *            类型－1:全部
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(
			HttpServletRequest request,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "100") int pageSize,
			@RequestParam(value = "type", defaultValue = "-1") int type,
			String name, Model model) {
		Page p = new Page(pageNo, pageSize);
		List<Positiontype> list = positiontypeService.pageList(
				"from Positiontype pt ", p, "order by pt.sort",
				"and pt.name like :name",
				name == null ? name : "%" + name.trim() + "%",
				"and pt.status !=:status", -9);
		model.addAttribute("list", list);
		String url = StringUtil.getRequestGetUrl(request, "name", name);
		model.addAttribute("url", HTMLUtil.getNumberPageHTML(p, url));
		return "manage/positiontype/list";
	}

	// 进入添加职位类型
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addtype(Model model, String pid) {
		if (!StringUtils.isBlank(pid)) {
			Positiontype ptype = positiontypeService.findById(pid,
					Positiontype.class);
			ptype.setLevel(ptype.getLevel() + 1);
			model.addAttribute("ptype", ptype);
		}
		return "manage/positiontype/add";
	}

	// 保存
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addtype(Positiontype type) {
		if (type == null)
			throw new RBCException("参数传入错误");
		positiontypeService.add(type);
		return "redirect:list";
	}

	// 查询比当前级别高的类型
	@ResponseBody
	@RequestMapping("/gettypesbylevel")
	public List<Positiontype> getTypesByLevel(int level, Model model) {
		return positiontypeService.findListByParams(
				"from Positiontype pt where pt.level = ? order by pt.name",
				level - 1);
	}

	// 删除职位类型
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(String id) {
		positiontypeService.deletePos(id);
		return "success";
	}

	// 进入修改方法
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(String id, Model model) {
		Positiontype type = positiontypeService
				.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要修改的数据");
		model.addAttribute("t", type);
		List<Positiontype> types = positiontypeService.findListByParams(
				"from Positiontype t where t.status!=-9 and t.id!=?", id);
		model.addAttribute("types", types);
		return "manage/positiontype/update";
	}

	// 修改类型
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Positiontype type) {
		if (StringUtils.isBlank(type.getId()))
			throw new RBCException("主键参数传入错误");
		if (StringUtils.isBlank(type.getName()))
			throw new RBCException("类型名称不能为空");
		positiontypeService.updatePos(type);
		return "redirect:list";
	}

	// 审核类型
	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public String audit(String id) {
		positiontypeService.audit(id);
		return "redirect:list";
	}

	// 取消审核
	@RequestMapping(value = "/unaudit", method = RequestMethod.GET)
	public String unaudit(String id) {
		positiontypeService.unAudit(id);
		return "redirect:list";
	}
}

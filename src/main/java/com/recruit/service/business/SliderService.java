package com.recruit.service.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.dao.base.VfilesDao;
import com.recruit.dao.business.SliderDao;
import com.recruit.entity.base.Slider;
import com.recruit.entity.user.Vfiles;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.FileUtil;
import com.recruit.util.StringUtil;

@Service
public class SliderService extends BaseService<Slider> {

	@Autowired
	private SliderDao sliderDao;
	@Autowired
	private VfilesDao vfilesDao;

	public void audit(String id) {
		Slider slider = sliderDao.findById(id, Slider.class);
		if (slider == null)
			throw new RBCException("没有找到要操作的数据");
		if (slider.getStatus() != 0)
			throw new RBCException("只能操作未使用的数据");
		List<Slider> olds = sliderDao
				.findListByParams("from Slider s where s.type=0 and s.status=1 order by s.createtime asc");
		if (olds != null && olds.size() == 3) {
			Slider last = olds.get(0);
			last.setStatus(0);
			sliderDao.update(last);
		}
		slider.setStatus(1);
		sliderDao.update(slider);

	}

	public void deleteSlider(String id) {
		Slider slider = sliderDao.findById(id, Slider.class);
		if (slider == null)
			throw new RBCException("没有找到要操作的数据");
		if (slider.getStatus() != 0)
			throw new RBCException("只能操作未使用的数据");
		slider.setStatus(-9);
		sliderDao.update(slider);
	}

	public void updateSlider(HttpServletRequest request, Slider s, MultipartFile imgfile, MultipartFile smimgfile) {
		Slider slider = sliderDao.findById(s.getId(), Slider.class);
		if (slider == null)
			throw new RBCException("没有找到要操作的数据");
		if (slider.getStatus() != 0)
			throw new RBCException("只能操作未使用的数据");
		slider.setSort(s.getSort());
		slider.setAlt(s.getAlt());
		if(!imgfile.isEmpty()){
			//删除老图
			List<Vfiles> files = vfilesDao.findListByParams("from Vfiles f where f.srcid=? and f.type=? and f.issm=0", s.getId());
			if(files!=null){
				for(Vfiles f : files){
					FileUtil.deleteFile(f.getPath());
					vfilesDao.delete(f);
				}
			}
			// 大图
			String path = FileUtil.upladFile(request, imgfile);
			String url = FileUtil.getServerurl(path);
			vfilesDao.add(FileUtil.getFileName(imgfile), 0, path, slider.getId(),
					Vfiles.TYPE_HOME_SLIDER, url);
			slider.setImg(url);
		}
		if(!smimgfile.isEmpty()){
			//删除老图
			List<Vfiles> files = vfilesDao.findListByParams("from Vfiles f where f.srcid=? and f.type=? and f.issm=1", s.getId());
			if(files!=null){
				for(Vfiles f : files){
					FileUtil.deleteFile(f.getPath());
					vfilesDao.delete(f);
				}
			}
			// 小图
			String smpath = FileUtil.upladFile(request, smimgfile);
			String smurl = FileUtil.getServerurl(smpath);
			vfilesDao.add(FileUtil.getFileName(smimgfile), 1, smpath,
					slider.getId(), Vfiles.TYPE_HOME_SLIDER, smurl);
			slider.setSmimg(smurl);
		}
	}

	public void add(HttpServletRequest request, Slider slider,
			MultipartFile imgfile, MultipartFile smimgfile) {
		slider.setId(StringUtil.uuid());
		// 大图
		String path = FileUtil.upladFile(request, imgfile);
		String url = FileUtil.getServerurl(path);
		vfilesDao.add(FileUtil.getFileName(imgfile), 0, path, slider.getId(),
				Vfiles.TYPE_HOME_SLIDER, url);
		slider.setImg(url);
		// 小图
		String smpath = FileUtil.upladFile(request, smimgfile);
		String smurl = FileUtil.getServerurl(smpath);
		vfilesDao.add(FileUtil.getFileName(smimgfile), 1, smpath,
				slider.getId(), Vfiles.TYPE_HOME_SLIDER, smurl);
		slider.setSmimg(smurl);
		slider.setCreatetime(new Date());
		sliderDao.save(slider);
	}

	public void unaudit(String id) {
		Slider slider = sliderDao.findById(id, Slider.class);
		if (slider == null)
			throw new RBCException("没有找到要操作的数据");
		if (slider.getStatus() != 1)
			throw new RBCException("只能操作已经使用的数据");
		slider.setStatus(0);
		sliderDao.update(slider);
	}
}

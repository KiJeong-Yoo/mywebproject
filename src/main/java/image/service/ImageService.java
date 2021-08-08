package image.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import image.dao.ImageInter;
import image.vo.ImageVo;
import image.vo.PageBoard;

@Service
public class ImageService {
	@Autowired
	ImageInter image;

	public int insert(String title, String writeId, String img) {
		return image.insert(title, writeId, img);
	}

	public PageBoard list(int requestPage) {
		return image.list(requestPage);
	}

	public int readcountUpdate(int idx) {
		return image.readcountUpdate(idx);
		
	}

	public ImageVo select(int idx) {
		return image.select(idx);
	}

	public int update(String title, String img, int idx) {
		return image.update(title, img, idx);
	}

	public int delete(int idx) {
		return image.delete(idx);
	}

	public PageBoard searchlist(int request_page, String search) {
		return image.searchlist(request_page, search);
	}
	
	
}

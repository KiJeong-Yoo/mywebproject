package image.dao;

import java.util.List;

import image.vo.ImageVo;
import image.vo.PageBoard;

public interface ImageInter {

	int insert(String title, String writeId, String img);

	PageBoard list(int requestPage);

	int readcountUpdate(int idx);

	ImageVo select(int idx);

	int update(String title, String content, int idx);

	int delete(int idx);

}

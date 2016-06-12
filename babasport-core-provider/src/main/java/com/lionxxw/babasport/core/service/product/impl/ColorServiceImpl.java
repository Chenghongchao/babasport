package com.lionxxw.babasport.core.service.product.impl;

import com.lionxxw.babasport.core.dao.product.ColorDao;
import com.lionxxw.babasport.core.dto.product.ColorDto;
import com.lionxxw.babasport.core.entity.Color;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 颜色接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorDao colorDao;

    public ColorDto save(ColorDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        Color color = BeanUtils.createBeanByTarget(obj, Color.class);
        colorDao.insertSelective(color);
        obj.setId(color.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Color.class, "delById");
        int i = colorDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ColorDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Color.class, "update");
        Color color = BeanUtils.createBeanByTarget(obj, Color.class);
        colorDao.updateByPrimaryKeySelective(color);
    }

    public ColorDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Color.class, "getById");
        Color color = colorDao.selectByPrimaryKey(id);
        ColorDto dto = BeanUtils.createBeanByTarget(color, ColorDto.class);
        return dto;
    }

    public List<ColorDto> queryByParam(ColorDto obj) throws Exception {
        List<Color> colors = colorDao.queryByParam(obj, null);
        if (null != colors && colors.size() > 0){
            List<ColorDto> list = BeanUtils.createBeanListByTarget(colors, ColorDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ColorDto> queryByPage(ColorDto obj, PageQuery query) throws Exception {
        int total = colorDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Color> colors = colorDao.queryByParam(obj, query);
            List<ColorDto> list = BeanUtils.createBeanListByTarget(colors, ColorDto.class);
            return new PageResult<ColorDto>(query, list);
        }
        return null;
    }
}

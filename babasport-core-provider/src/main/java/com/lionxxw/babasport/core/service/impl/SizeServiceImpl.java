package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.SizeDao;
import com.lionxxw.babasport.core.dto.SizeDto;
import com.lionxxw.babasport.core.entity.Size;
import com.lionxxw.babasport.core.service.SizeService;
import com.lionxxw.babasport.core.service.SizeService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 尺寸接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeDao sizeDao;

    public SizeDto save(SizeDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        Size size = BeanUtil.createBeanByTarget(obj, Size.class);
        sizeDao.insertSelective(size);
        obj.setId(size.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Size.class, "delById");
        int i = sizeDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(SizeDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), Size.class, "update");
        Size size = BeanUtil.createBeanByTarget(obj, Size.class);
        sizeDao.updateByPrimaryKeySelective(size);
    }

    public SizeDto getById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Size.class, "getById");
        Size size = sizeDao.selectByPrimaryKey(id);
        SizeDto dto = BeanUtil.createBeanByTarget(size, SizeDto.class);
        return dto;
    }

    public List<SizeDto> queryByParam(SizeDto obj) throws Exception {
        List<Size> Sizes = sizeDao.queryByParam(obj, null);
        if (null != Sizes && Sizes.size() > 0){
            List<SizeDto> list = BeanUtil.createBeanListByTarget(Sizes, SizeDto.class);
            return list;
        }
        return null;
    }

    public PageResult<SizeDto> queryByPage(SizeDto obj, PageQuery query) throws Exception {
        int total = sizeDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Size> sizes = sizeDao.queryByParam(obj, query);
            List<SizeDto> list = BeanUtil.createBeanListByTarget(sizes, SizeDto.class);
            return new PageResult<SizeDto>(query, list);
        }
        return null;
    }
}

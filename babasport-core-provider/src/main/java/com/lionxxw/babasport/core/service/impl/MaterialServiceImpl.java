package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.MaterialDao;
import com.lionxxw.babasport.core.dto.MaterialDto;
import com.lionxxw.babasport.core.entity.Material;
import com.lionxxw.babasport.core.service.MaterialService;
import com.lionxxw.babasport.core.service.MaterialService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 商品材质接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

    public MaterialDto save(MaterialDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        Material material = BeanUtil.createBeanByTarget(obj, Material.class);
        materialDao.insertSelective(material);
        obj.setId(material.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Material.class, "delById");
        int i = materialDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(MaterialDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), Material.class, "update");
        Material material = BeanUtil.createBeanByTarget(obj, Material.class);
        materialDao.updateByPrimaryKeySelective(material);
    }

    public MaterialDto getById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Material.class, "getById");
        Material material = materialDao.selectByPrimaryKey(id);
        MaterialDto dto = BeanUtil.createBeanByTarget(material, MaterialDto.class);
        return dto;
    }

    public List<MaterialDto> queryByParam(MaterialDto obj) throws Exception {
        List<Material> materials = materialDao.queryByParam(obj, null);
        if (null != materials && materials.size() > 0){
            List<MaterialDto> list = BeanUtil.createBeanListByTarget(materials, MaterialDto.class);
            return list;
        }
        return null;
    }

    public PageResult<MaterialDto> queryByPage(MaterialDto obj, PageQuery query) throws Exception {
        int total = materialDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Material> materials = materialDao.queryByParam(obj, query);
            List<MaterialDto> list = BeanUtil.createBeanListByTarget(materials, MaterialDto.class);
            return new PageResult<MaterialDto>(query, list);
        }
        return null;
    }
}

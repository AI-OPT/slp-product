package com.ai.slp.product.service.atom;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.attach.StorageGroupAttach4List;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.vo.StoGroupPageQueryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IStorageGroupAtomSVTest {
    @Autowired
    IStorageGroupAtomSV groupAtomSV;

    @Test
    public void queryForGroupListTest(){
        StoGroupPageQueryVo queryVo = new StoGroupPageQueryVo();
        queryVo.setTenantId(CommonConstants.COMMON_TENANT_ID);
        queryVo.setPageSize(10);
        PageInfo<StorageGroupAttach4List> groupList =  groupAtomSV.queryForGroupList(queryVo);
        if (groupList==null)
            throw new BusinessException("","查询内容为空");
        System.out.println(groupList.getCount()+":"+groupList.getPageSize()+":"+groupList.getPageNo());
        int i = 1;
        for (StorageGroupAttach4List groupAttach:groupList.getResult()){
            System.out.println(i++);
            System.out.println(groupAttach.getStorageGroupId()+":"+groupAttach.getStorageGroupName()+":"
            +groupAttach.getStandedProdId()+":"+groupAttach.getStandedProductName());
        }
    }
}

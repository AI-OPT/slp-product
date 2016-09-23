package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;

/**
 * 商品受众原子操作
 * Created by jackieliu on 16/6/2.
 */
public interface IProdAudiencesAtomSV {

    /**
     * 查询某个商品下符合用户类型和用户ID的受众群,用户类型和用户ID不能均为空
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @param userId
     * @param hasDiscard
     * @return
     */
    public List<ProdAudiences> queryByUserType(String tenantId, String prodId, String userType, String userId, boolean hasDiscard);

    /**
     * 插入受众信息
     *
     * @param prodAudiences
     * @return
     */
    public int installAudiences(ProdAudiences prodAudiences);

    /**
     * 设置某用户类型不可见
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @return
     */
    public int updateNoUser(String tenantId, String prodId, String userType, Long operId);

    /**
     * 查询商品指定类型的受众信息
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @param hasDiscard
     * @return
     */
    public List<ProdAudiences> queryByUserType(String tenantId, String prodId, String userType, boolean hasDiscard);

    /**
     * 确认商品下某类型用户是否为全部可见
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @param hasDiscard
     * @return
     */
    public ProdAudiences queryAllByUserType(String tenantId, String prodId, String userType, boolean hasDiscard);

    /**
     * 查询商品的所有受众信息,包括所有类型
     * @param tenantId
     * @param prodId
     * @param hasDiscard
     * @return
     */
    public List<ProdAudiences> queryOfProductByProdId(String tenantId, String prodId,boolean hasDiscard);
}

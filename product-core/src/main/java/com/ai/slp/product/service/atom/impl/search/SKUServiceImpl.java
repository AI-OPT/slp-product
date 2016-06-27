package com.ai.slp.product.service.atom.impl.search;

import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.search.ISKUService;
import com.ai.slp.product.util.SearchSKUInfoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.ai.slp.product.constants.SearchConstants.QUERY_SQL.MAIN_SQL;

/**
 * Created by xin on 16-6-1.
 */
@Service
public class SKUServiceImpl implements ISKUService {

    private Logger logger = LogManager.getLogger(SKUServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<SKUInfo> getSKUInfoByProductId(String productId) {
        List<SKUInfo> skuInfoList = new ArrayList<SKUInfo>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(MAIN_SQL);
            ps.setString(1, productId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                SKUInfo skuInfo = SearchSKUInfoUtil.fillSKUMainInfo(resultSet);
                skuInfoList.add(skuInfo);
            }

            for (SKUInfo skuInfo : skuInfoList) {
                SearchSKUInfoUtil.fillSKUInfo(connection, skuInfo);
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error("Failed to get SKUs by productId[" + productId + "]", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                    logger.error("Failed to close connection");
                }
            }
        }

        return skuInfoList;
    }
}

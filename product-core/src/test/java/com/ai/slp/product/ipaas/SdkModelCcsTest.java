package com.ai.slp.product.ipaas;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.constants.SDKConstants;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/25.
 */
public class SdkModelCcsTest {
    private IConfigClient client;

    @Before
    public void init(){
        client = CCSClientFactory.getDefaultConfigClient();
    }

    @Test
    public void addMcsConfig() throws ConfigException {
        //缓存服务主机
        String mcsName = "MCS001";
        //缓存空间
        String cacheSnsConfig = "{\"com.ai.opt.slp.product.storage\":\""+mcsName
                +"\",\"com.ai.opt.slp.product.cat\":\""+mcsName+"\"}";

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(" \"MCS001\":{");
        sb.append("			\"mcs.host\":\"127.0.0.1:6379\",");
        sb.append("			\"mcs.maxtotal\":\"200\",");
        sb.append("			\"mcs.maxIdle\":\"10\",");
        sb.append("			\"mcs.minIdle\":\"5\",");
        sb.append("			\"mcs.testOnBorrow\":\"true\",");
        sb.append("			\"mcs.password\":\"\"");
        sb.append(" }");
        sb.append("}");

        //缓存空间配置
        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH)){
            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,cacheSnsConfig);
        }else {
            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,cacheSnsConfig);
        }

        //redis链接配置
        if (!client.exists(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH)){
            client.add(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,sb.toString());
        }else {
            client.modify(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,sb.toString());
        }
    }

    @Test
    public void addDbConfig() throws ConfigException {
        //缓存服务主机
        String mcsName = "MCS001";

        StringBuilder sb = new StringBuilder();
        sb.append("{																																																				");
        sb.append("		\"slp-product-db\":");
        sb.append("		{");
        sb.append("			\"driverClassName\":\"com.mysql.jdbc.Driver\",");
        sb.append("			\"jdbcUrl\":\"jdbc:mysql://10.1.245.7:31306/devslpprddb1?useUnicode=true&characterEncoding=UTF-8\", ");
        sb.append("			\"username\":\"devslpprdusr1\",");
        sb.append("			\"password\":\"devslpprdusr1@8899\",");
        sb.append("			\"autoCommit\":\"true\",");
        sb.append("			\"connectionTimeout\":\"30000\",");
        sb.append("			\"idleTimeout\":\"600000\",");
        sb.append("			\"maxLifetime\":\"1800000\",");
        sb.append("			\"maximumPoolSize\":\"10\"");
        sb.append("		} ");
        sb.append("}");


        //添加数据库配置信息
        if (!client.exists(SDKConstants.DB_CONF_PATH)){
            client.add(SDKConstants.DB_CONF_PATH,sb.toString());
        }else {
            client.modify(SDKConstants.DB_CONF_PATH,sb.toString());
        }
    }
}

1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test
2.生成镜像
#在本地生成带私服前缀的镜像(每次打镜像前版本号要更新)
docker build -t 10.19.13.18:5000/slp-product:v1.0 .   
#将镜像推送到私服
docker push 10.19.13.18:5000/slp-product:v1.0

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
# REST_REGISTRY_ADDR  注册中心IP和端口
# REST_PORT 服务的rest端口
# CONTEXT_PATH 服务的rest服务工程名
# SDK_MODE 是否为SDK模式,1:启用SDK模式,0:启用服务模式
# CCS_NAME 配置中心的应用名称
# ZK_ADDR  配置中心的地址
# REG_HOST dubbo服务的注册host地址,一般为宿主机的IP
3.1 默认方式
docker run -d --name slp-product -p 10882:10882 \
-e "REST_REGISTRY_ADDR=10.19.13.13:29181" -e "REST_PORT=10882" \
-e "CONTEXT_PATH=slp-product" -e "SDK_MODE=1" \
-e "CCS_NAME=aiopt-ch-product" -e "ZK_ADDR=10.19.13.13:29181"  \
10.19.13.19:5000/slp-product:v1.0
3.2 自定义host方式
docker run -d --name slp-product -p 10882:10882 \
-e "REST_REGISTRY_ADDR=10.19.13.13:29181" -e "REST_PORT=10882" \
-e "CONTEXT_PATH=slp-product" -e "SDK_MODE=0" -e "CCS_NAME=aiopt-ch-product" \
-e "ZK_ADDR=10.19.13.13:29181" -e "REG_HOST=10.1.65.96" \
10.19.13.19:5000/slp-product:v1.0 /start-dubbo-service-host.sh
#查看镜像启动日志
docker logs slp-product
#进入容器，查看镜像内部的情况
docker exec -it slp-product /bin/bash
#删除运行的容器
docker rm -fv slp-product

#=============更新日志========================#
*2016-09-23
1）初始版本
*2016-10-07
1> jenkis


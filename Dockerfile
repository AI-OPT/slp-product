# Pull base image
FROM 10.19.13.36:5000/jdk:7
MAINTAINER gucl<gucl@asiainfo.com>

# deploy user dubbo service
COPY ./build/libs /dubbo-service/libs/
COPY ./build/config /dubbo-service/config/

#mkdir logs path
RUN cd /dubbo-service && mkdir logs && cd /dubbo-service/logs

## copy start script
COPY ./script/*.sh /
RUN chmod 755 /*.sh

# Expose dubbo/rest ports.
EXPOSE 10882

# Define default command.
CMD ["./start-dubbo-service.sh"]
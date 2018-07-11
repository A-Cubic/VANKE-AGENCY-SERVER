FROM java:8-jre
ADD target/cubic-api-server.jar /opt/cubic-api-server/
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV TZ "Asia/Shanghai"
EXPOSE 8888
WORKDIR /opt/cubic-api-server/
CMD ["java", "-Xms32m", "-Xmx256m", "-Xss256k", "-XX:ParallelGCThreads=2", "-Djava.compiler=NONE", "-jar" , "cubic-api-server.jar"]
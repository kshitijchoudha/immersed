FROM java:8-alpine
MAINTAINER Kshitij Choudha
ADD target/hystrix-dashboard-0.0.1-SNAPSHOT.jar /
EXPOSE 7979
ENTRYPOINT ["java","-jar", "hystrix-dashboard-0.0.1-SNAPSHOT.jar"]

FROM tomcat
ADD target/center-1.1.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

FROM openjdk:8-jdk-alpine
ADD target/customer.war app.war
RUN apk del openjdk8="$JAVA_ALPINE_VERSION"
CMD java -jar /app.war \
	&& rm -f /app.war 
	

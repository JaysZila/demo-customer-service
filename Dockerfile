FROM openjdk:8-jdk-alpine
ADD target/customer.war app.war
CMD java -jar /app.war \
	&& rm -f /app.war \
	&& apk del openjdk8="$JAVA_ALPINE_VERSION"

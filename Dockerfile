FROM openjdk:8

# required build time arguments
ARG PORT
ARG JAR_NAME
ARG CONFIG_NAME

# set as environment variables so they can be used at runtime
ENV PORT=$PORT
ENV JAR_NAME=$JAR_NAME
ENV CONFIG_NAME=$CONFIG_NAME

EXPOSE $PORT

ADD $JAR_NAME /
ADD $CONFIG_NAME /

CMD java -jar $JAR_NAME server $CONFIG_NAME

HEALTHCHECK --interval=5m \
  CMD curl -f http://localhost:${PORT}/admin/healthcheck || exit 1

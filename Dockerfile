FROM openjdk:18

RUN mkdir -p /usr/dockertracker

COPY TaskTrackerServer/src/main/resources/local_machine_properties.yaml /usr/dockertracker

COPY out/artifacts/Dropwizard_TaskTracker_jar/Dropwizard_TaskTracker.jar /usr/dockertracker

CMD ["java", "-jar", "/usr/dockertracker/Dropwizard_TaskTracker.jar", "server","/usr/dockertracker/local_machine_properties.yaml"]
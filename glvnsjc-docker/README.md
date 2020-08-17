# glvnsjc-docker

To start this docker image at linux and store data at /var/lib/docker/volumes/glvnsjc


    docker run -d -p8080:8080 -v glvnscj:/usr/local/tomcat/glvnsjc --name glvnsjc image-id

on windows, store volume at a known location

    docker run -d -p8080:8080 -v c:/views/glvnscj:/usr/local/tomcat/glvnsjc --name glvnsjc image-id
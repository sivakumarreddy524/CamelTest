# testRepo


mvn package -Dmaven.test.skip=true -Pprod jib:dockerBuild

docker-compose -f src/main/docker/app.yml up
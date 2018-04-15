##api微服务template

###description
- 用spring-boot + mysql实现的rest api web服务
- 将服务docker化，build成相应image(DockerFile)
- 可以用docker compose启动web服务以及它所依赖的mysql服务

###useage
- dev环境下用 `gradle fC fM` 初始化数据库
- 修改代码后，

    `gradle clean build` 打包代码 -> 
    
    `docker build 镜像名` build 新的docker镜像 ->
    
    `docker-compose up` 启动服务(docker-compose.yml文件中，web服务的镜像名要修改成build镜像时指定的镜像名)


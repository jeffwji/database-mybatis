使用方法：

1.pom.xml 中引用本项目

2.(可选)在项目的properties文件中指定数据源名称 mybatis.datasource.name 默认为 dataSource

3.在项目的Configure类的开头添加 @MapperScan 例如：

    //basePackages 指向Dao存放的package路径
    @MapperScan(basePackages = { "com.wang.meetup.dao.mybatis" })
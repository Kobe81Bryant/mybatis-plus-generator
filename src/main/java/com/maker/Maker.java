package com.maker;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * mybatis-plus 自动生成代码
 *
 * @author Kobe
 * @version 1.0
 * @date 2018-05-16 09:35
 */
public class Maker {
    @Test
    public void generateCode() throws IOException {
    	System.out.println("开始生成...");
        //指定包名
        String packageName = "com";
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        //指定生成的表名
        String[] tableNames = new String[]{"user"};
        generateByTables(serviceNameStartWithI, packageName, tableNames);
        System.out.println("生成完毕...");
    }
    
    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Kobe
     */
    private DataSourceConfig getDataSourceConfig() {
    	DataSourceConfig dataSourceConfig = new DataSourceConfig().setDbType(DbType.MYSQL)
        .setUrl("jdbc:mysql://192.168.133.168:3306/test?useSSL=false")
        .setUsername("root")
        .setPassword("root")
        .setDriverName("com.mysql.jdbc.Driver");
        return dataSourceConfig;
    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param packageName           包名
     * @param tableNames            表名
     * @author Kobe
     */
    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) throws IOException {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }
    
    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     * @author Kobe
     */
    private void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     * @author Kobe
     */
    private PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setXml("mapper")
                .setMapper("mapper")
                .setController("controller")
                .setEntity("entity");
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Kobe
     */
    private GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) throws IOException {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setEnableCache(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setAuthor("Kobe")
                //设置输出路径
                .setOutputDir(getOutputDir())
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     * @return 项目路径
     * @author Kobe
     */
    private String getOutputDir() throws IOException {
        String path = new File("").getCanonicalPath()+"/src/main/java/";
        return path;
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Kobe
     */
    private StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                // 表名、字段名、是否使用下划线命名（默认 false）
                .setDbColumnUnderline(true)
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames);
    }

    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     * @author Kobe
     */
    @SuppressWarnings("unused")
    private void generateByTables(String packageName, String... tableNames) throws IOException {
        generateByTables(true, packageName, tableNames);
    }
}
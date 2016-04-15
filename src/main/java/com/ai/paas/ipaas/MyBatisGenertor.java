package com.ai.paas.ipaas;

import com.ai.paas.ipaas.util.StringUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by astraea on 2015/4/16.
 */
public class MyBatisGenertor {
    private static final Logger log = LogManager
            .getLogger(MyBatisGenertor.class.getName());

    @SuppressWarnings("unused")
	private static final String APP_CONTEXT = "classpath:context/applicationContext-dubbo-service-provider.xml";
    private static final String GENERATOR_CONFIG = "classpath:context/jdbc.properties";

    private static String basePackage = "com.ai.paas.ipaas.";
    private static String tablename = null;
    private static String moduleName = null;
    private static String basemapperPath = "mybatis.mapper.";
    private static String baseProjectPath = System.getProperty("user.dir");
    private static final String targetCodeProject = baseProjectPath + File.separator + "src" + File.separator + "main" + File.separator + "java";
    private static final String targetResourcesProject = baseProjectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources";

    private static void generatorDao() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        log.debug("[basePackage]" + basePackage);
        log.debug("[tablename]" + tablename);
        log.debug("[baseProjectPath]" + baseProjectPath);

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        Context context = new Context(null);
        context.setId("DB2Tables");
        context.setTargetRuntime("MyBatis3");

        appendSqlMapGeneratorConfiguration(context);
        appendJavaModelGeneratorConfiguration(context);
        appendJavaClientGeneratorConfiguration(context);
        appendJDBCConnectionConfiguration(context);
        appendCommentGeneratorConfiguration(context);
        appendPluginConfiguration(context);
        spprnfMyDSQLPaginationPlugin(context);
        appendJavaTypeResolverConfiguration(context);

        config.addContext(context);
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tablename);
        tableConfiguration.setCountByExampleStatementEnabled(true);
        tableConfiguration.setUpdateByExampleStatementEnabled(true);
        tableConfiguration.setDeleteByExampleStatementEnabled(true);
        tableConfiguration.setSelectByExampleStatementEnabled(true);
        context.addTableConfiguration(tableConfiguration);

        try {

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            log.error(e);

        }
    }

    private static void appendJavaTypeResolverConfiguration(Context context) {
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.setConfigurationType("org.mybatis.generator.internal.types.JavaTypeResolver4MvneImpl");
        javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
    }

    private static void spprnfMyDSQLPaginationPlugin(Context context) {
        PluginConfiguration mySQLPaginationPlugin = new PluginConfiguration();
        mySQLPaginationPlugin.setConfigurationType("org.mybatis.generator.plugins.MySQLPaginationPlugin");
        context.addPluginConfiguration(mySQLPaginationPlugin);
    }

    private static void appendPluginConfiguration(Context context) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.RenameExampleClassPlugin");
        pluginConfiguration.addProperty("searchString", "Example$");
        pluginConfiguration.addProperty("replaceString", "Criteria");
        context.addPluginConfiguration(pluginConfiguration);
    }

    private static void appendCommentGeneratorConfiguration(Context context) {
        CommentGeneratorConfiguration commentGenerator = new CommentGeneratorConfiguration();
        commentGenerator.addProperty("suppressDate", "true");
        commentGenerator.addProperty("suppressAllComments", "true");
        context.setCommentGeneratorConfiguration(commentGenerator);
    }

    private static void appendJDBCConnectionConfiguration(Context context) throws IOException {
        @SuppressWarnings("resource")
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{});
        Resource resource = ctx.getResource(GENERATOR_CONFIG);
        Properties config = new Properties();
        config.load(resource.getInputStream());
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(config.getProperty("jdbc.driverClassName"));
        jdbcConnectionConfiguration.setConnectionURL(config.getProperty("jdbc.url"));
        jdbcConnectionConfiguration.setUserId(config.getProperty("jdbc.username"));
        jdbcConnectionConfiguration.setPassword(config.getProperty("jdbc.password"));
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
    }

    private static void appendJavaClientGeneratorConfiguration(Context context) {
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
        javaClientGeneratorConfiguration.setTargetPackage(basePackage + moduleName  + ".dao.interfaces");
        javaClientGeneratorConfiguration.setTargetProject(targetCodeProject);
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
    }

    private static void appendJavaModelGeneratorConfiguration(Context context) {
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
        javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
        javaModelGeneratorConfiguration.setTargetPackage(basePackage + moduleName  + ".dao.mapper.bo");
        javaModelGeneratorConfiguration.setTargetProject(targetCodeProject);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
    }

    private static void appendSqlMapGeneratorConfiguration(Context context) {
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
        sqlMapGeneratorConfiguration.setTargetPackage(basemapperPath + moduleName);
        sqlMapGeneratorConfiguration.setTargetProject(targetResourcesProject);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
    }

    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException {
        String tablenameParam = System.getProperty("tableName");
        String moduleParam = System.getProperty("moduleName");

        if (StringUtil.isBlank(tablenameParam) || StringUtil.isBlank(moduleParam)) {
            System.out.println("MyBatisGenertor -DtableName=xxx -DmoduleName=config");
            System.exit(-1);
        }

        MyBatisGenertor.tablename = tablenameParam;
        MyBatisGenertor.moduleName = moduleParam;

        generatorDao();
    }
}

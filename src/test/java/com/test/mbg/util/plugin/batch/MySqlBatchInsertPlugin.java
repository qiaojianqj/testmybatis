package com.test.mbg.util.plugin.batch;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
*
 */
public class MySqlBatchInsertPlugin extends PluginAdapter {
    public static String BATCH_INSERT_SELECTIVE_PROVIDER_METHOD_NAME = "batchInsert";
    /**
     *  Here should check if current database is MySql.
     * @param warnings warnings
     * @return if true returned, the plugin will work .
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * add selectOneByExample method.
     *
     * @param interfaze     mapper interface file.
     * @param topLevelClass  topLevelClass
     * @param introspectedTable current table configurations.
     * @return boolean  result
     */
    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        AnnotatedBatchInsertSelectiveMethodGenerator annotatedBatchInsertSelectiveMethodGenerator
                = new AnnotatedBatchInsertSelectiveMethodGenerator();

        annotatedBatchInsertSelectiveMethodGenerator.setContext(context);
        annotatedBatchInsertSelectiveMethodGenerator.setIntrospectedTable(introspectedTable);
        annotatedBatchInsertSelectiveMethodGenerator.setMethodName(getMethodName(this.getProperties()));

        annotatedBatchInsertSelectiveMethodGenerator.addInterfaceElements(interfaze);
        return true;
    }

    /**
     *  Get configured method name.
     *
     * @param properties configuration in xml file.
     * @return the method name.
     */
    private static String getMethodName (Properties properties){
        String methodNameProp = properties.getProperty("methodName");
        if(StringUtils.isBlank(methodNameProp)){
            return BATCH_INSERT_SELECTIVE_PROVIDER_METHOD_NAME;
        }
        return methodNameProp.trim();
    }

}

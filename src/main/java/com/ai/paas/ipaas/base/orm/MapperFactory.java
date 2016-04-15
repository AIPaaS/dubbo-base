package com.ai.paas.ipaas.base.orm;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by astraea on 2015/4/20.
 */
@Component
@Lazy
public class MapperFactory {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    public <T> T getMapper(Class<T> tclass) {
        return sqlSessionTemplate.getMapper(tclass);
    }

}

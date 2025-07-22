package io.github.xfcycc.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 注入处理器
 *
 */
@Slf4j
public class InjectionMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入填充方法
     *
     * @param metaObject 元对象，用于获取原始对象并进行填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {

    }

    /**
     * 更新填充方法
     *
     * @param metaObject 元对象，用于获取原始对象并进行填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }



}

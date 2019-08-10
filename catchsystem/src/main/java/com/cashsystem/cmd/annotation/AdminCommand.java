package com.cashsystem.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//元注解.表示注解可以保留到程序运行的时候，加载到JVM中，
@Target(ElementType.TYPE)   // 可以给一个类型进行注解，比如类、接口、枚举
public @interface AdminCommand { //管理员客户命令

}

package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype") //默认是单例，若想修改则加此注解  若不是单例则就不是启动时初始化，而是调用这个bean时初始化
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;
    public String find(){
       return alphaDao.select();
    }

    public AlphaService(){
        System.out.println("实例化");
    }
    @PostConstruct //在构造器之后调用
    public void init(){
        System.out.println("初始化");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }
}

package com.yuyu.tree.config;

import com.alibaba.fastjson.JSON;
import com.yuyu.common.annotation.SysLog;
import com.yuyu.tree.po.TimsLog;
import com.yuyu.tree.service.LogService;
import com.yuyu.tree.vo.TreeVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;


    @Pointcut("@annotation(com.yuyu.common.annotation.SysLog)")
    public void LogPoinCut() { }

    @AfterReturning(value = "LogPoinCut()", returning = "treeId")
    public void saveOperLog(JoinPoint joinPoint, Object treeId) {
        TimsLog timsLog = new TimsLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            SysLog sysLog = method.getAnnotation(SysLog.class);
            int action = sysLog.value().getCode();
            timsLog.setAction(action);

            Object[] args = joinPoint.getArgs();
            TreeVo treeVo = (TreeVo) args[0];
            String jsonString = JSON.toJSONString(treeVo);
            timsLog.setParams(jsonString);
            timsLog.setCreator((String) args[1]);
            timsLog.setCreateTime(new Date());

            switch (action) {
                case 0: {
                    timsLog.setTreeId(treeVo.getId());
                }
                case 1: {
                    timsLog.setTreeId((Long) treeId);
                }
            }

            logService.saveLog(timsLog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

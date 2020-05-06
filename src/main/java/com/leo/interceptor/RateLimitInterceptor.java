package com.leo.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Component
@Slf4j
public class RateLimitInterceptor extends HandlerInterceptorAdapter {
    //private static final RateLimiter rateLimiter = RateLimiter.create ( 5 );
    Map<String, RateLimiter> limiterMap = new ConcurrentHashMap<>();
    private long limitNum = 20;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //接口频次
        String deviceId = request.getParameter("s_device_id");
        RateLimiter limiter = limiterMap.get(deviceId);
        if(limiter == null){
            limiter = RateLimiter.create(limitNum);
            limiterMap.put(deviceId, limiter);
        }

        if(!limiter.tryAcquire(1, TimeUnit.SECONDS)){
            log.warn("接口超频, thread: {} limiter: {} ip:{} path:{} id:{} "
                    , Thread.currentThread ().getName ()
                    , limiter.toString ()
                    , request.getRemoteAddr()
                    , request.getRequestURI()
                    , deviceId
                    , limiter.acquire());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        if(limiterMap.size() > Short.MAX_VALUE){
            limiterMap.clear();
        }

        //if (!rateLimiter.tryAcquire ()) {
        //    System.out.println ( "限流中..." );
        //    response.getWriter ().write ( "限流中" );
        //    return false;
        //}
        //System.out.println ( "请求成功" );
        return true;
    }
}

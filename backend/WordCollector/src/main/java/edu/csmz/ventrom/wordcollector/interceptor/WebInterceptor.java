package edu.csmz.ventrom.wordcollector.interceptor;

import edu.csmz.ventrom.wordcollector.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
//    Interceptor handle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Get session and cookie
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            log.info("User cookie is null!!");
            return false;
        }
//        Check session and cookie has storaged
        for(Cookie cookie :cookies){
            if ("username".equals(cookie.getName())){
                String username = cookie.getValue();
                log.info(username);
                Object user = session.getAttribute(username);
                if (user instanceof User){
                    log.info(user.toString());
                    return true;
                }else{
                    log.info("User session is null");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

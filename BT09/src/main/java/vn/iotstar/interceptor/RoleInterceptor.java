package vn.iotstar.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        String role = (String) session.getAttribute("ROLE");
        String uri = request.getRequestURI();

        if (uri.startsWith("/admin") && !"ADMIN".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        if (uri.startsWith("/user") && !"USER".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true; // cho phép truy cập
    }
}
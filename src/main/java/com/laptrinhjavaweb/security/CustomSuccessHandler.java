package com.laptrinhjavaweb.security;

import com.laptrinhjavaweb.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";
        List<String> roles = SecurityUtils.getAuthorities();
        // roles sẽ có tất cả các roles của user đó và put vào đây.
        if (isAdmin(roles)) {
            url = "/quan-tri/trang-chu";
        }
        if (isTrainingStaff(roles)) {
            url = "/training-staff/trang-chu";
        }
        if (isTrainer(roles)) {
            url = "/trainer/trang-chu";
        }
        if (isTrainee(roles)) {
            url = "/trainee/trang-chu";
        }
        return url;
        // trả url về biến customSuccessHandler trong form-login của file security.xml
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ADMIN");
    }

    private boolean isTrainingStaff(List<String> roles) {
        return roles.contains("TRAINING-STAFF");
    }

    private boolean isTrainer(List<String> roles) {
        return roles.contains("TRAINER");
    }

    private boolean isTrainee(List<String> roles) {
        return roles.contains("TRAINEE");
    }
}

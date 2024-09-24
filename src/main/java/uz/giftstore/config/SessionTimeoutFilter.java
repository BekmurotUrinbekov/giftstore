package uz.giftstore.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Duration;

@Component
public class SessionTimeoutFilter extends HttpFilter {

    private static final int SESSION_TIMEOUT_SECONDS = 1800; // 30 minutes
    private static final int ACTIVITY_THRESHOLD_MINUTES = 10; // Extend session if activity is within last 10 minutes

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && isAuthenticated()) {
            LocalDateTime lastActivityTime = (LocalDateTime) session.getAttribute("lastActivityTime");
            LocalDateTime currentTime = LocalDateTime.now();

            if (lastActivityTime != null) {
                Duration duration = Duration.between(lastActivityTime, currentTime);
                if (duration.toMinutes() < ACTIVITY_THRESHOLD_MINUTES) {
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);
                }
            }

            // Update the last activity time
            session.setAttribute("lastActivityTime", currentTime);
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName());
    }
}

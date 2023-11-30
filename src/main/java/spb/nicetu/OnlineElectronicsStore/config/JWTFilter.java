package spb.nicetu.OnlineElectronicsStore.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spb.nicetu.OnlineElectronicsStore.security.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (token != null) {
            try {
                String email = jwtUtil.retrieveClaim(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                                userDetails.getAuthorities());

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // убрать "Bearer " из заголовка
        }
        return null;
    }
}

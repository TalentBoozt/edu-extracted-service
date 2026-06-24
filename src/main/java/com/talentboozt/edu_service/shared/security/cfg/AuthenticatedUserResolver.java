package com.talentboozt.edu_service.shared.security.cfg;

import com.talentboozt.edu_service.domains.edu.service.EduJwtService;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthenticatedUserResolver implements HandlerMethodArgumentResolver {

    private final EduJwtService jwtService;

    public AuthenticatedUserResolver(EduJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUser.class) ||
               (parameter.getParameterType().equals(String.class) && 
                (parameter.getParameterName().equals("userId") || 
                 parameter.getParameterName().equals("creatorId")));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = jwtService.extractTokenFromHeaderOrCookie(request);

        if (token != null && jwtService.validateToken(token)) {
            String userId = jwtService.extractUserId(token);
            if (userId != null && !userId.isEmpty() && !"n/a".equals(userId)) {
                return userId;
            }
        }

        throw new org.springframework.web.server.ResponseStatusException(
            org.springframework.http.HttpStatus.UNAUTHORIZED,
            "Authentication Required: No valid JWT found in cookies or headers for " + parameter.getParameterName() + ". Please check your login status."
        );
    }
}

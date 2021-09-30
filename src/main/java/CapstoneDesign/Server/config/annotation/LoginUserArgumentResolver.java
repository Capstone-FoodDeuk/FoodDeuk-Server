package CapstoneDesign.Server.config.annotation;

import CapstoneDesign.Server.config.auth.JwtTokenProvider;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.exception.UserNotFoundException;
import CapstoneDesign.Server.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final static String TOKEN_HEADER = "X-AUTH-TOKEN";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasTokenUser = parameter.hasParameterAnnotation(TokenUser.class);
        boolean hasUserType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasTokenUser && hasUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) webRequest;
        String token = httpRequest.getHeader(TOKEN_HEADER);
        String loginId = jwtTokenProvider.getLoginId(token);

        User findUser = userRepository.findUserByLoginId(loginId);
        if (findUser == null) {
            throw new UserNotFoundException("해당 유저가 존재하지 않습니다");
        }
        return findUser;
    }
}

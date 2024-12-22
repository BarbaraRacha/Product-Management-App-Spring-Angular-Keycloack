package enset.ma.orderservice.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        /*System.out.println("****************************************");
        System.out.println(authentication.getClass().getName());
        System.out.println("****************************************");
     */
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
        requestTemplate.header("Authorization","Bearer "+jwtAccessToken);
        //        DefaultOidcUser oidcUser = (DefaultOidcUser) oauth2AuthenticationToken.getPrincipal();
        //        String jwtTokenValue = oidcUser.getIdToken().getTokenValue();
    }
}

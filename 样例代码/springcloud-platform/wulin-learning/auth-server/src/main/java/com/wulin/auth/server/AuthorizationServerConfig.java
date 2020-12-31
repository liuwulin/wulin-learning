//package com.wulin.auth.server;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
//import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
////@Configuration
////@EnableAuthorizationServer
////@AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    /**
//     * 注入authenticationManager 来支持 password grant type
//     */
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired(required = false)
//    private JwtAccessTokenConverter jwtAccessTokenConverter;
//
//    @Autowired(required = false)
//    private TokenEnhancer tokenEnhancer;
//
//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;
//
//
//    @Autowired
//    private RandomValueAuthorizationCodeServices authorizationCodeServices;
//
//    private static final int AAAA = 7200;
//    private static final int BBBB = 7200;
//
//    /**
//     * 配置应用名称 应用id
//     * 配置OAuth2的客户端相关信息
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
//                .redirectUris("www.baidu.com").authorizedGrantTypes("authorization_code").scopes("all")
//                .accessTokenValiditySeconds(AAAA).refreshTokenValiditySeconds(BBBB);
//
//    }
//
//    /**
//     * 设置token类型
//     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
//     * @param endpoints
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        if (jwtAccessTokenConverter != null) {
//            if (tokenEnhancer != null) {
//                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//                tokenEnhancerChain.setTokenEnhancers(
//                        Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
//                endpoints.tokenEnhancer(tokenEnhancerChain);
//            } else {
//                endpoints.accessTokenConverter(jwtAccessTokenConverter);
//            }
//        }
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService)
//                .authorizationCodeServices(authorizationCodeServices)
//                .exceptionTranslator(webResponseExceptionTranslator);
//    }
//
//
//
//    /**
//     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
//     * @param security
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//        security
//                .tokenKeyAccess("isAuthenticated()")
//                .checkTokenAccess("permitAll()")
//                //让/oauth/token支持client_id以及client_secret作登录认证
//                .allowFormAuthenticationForClients();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }
//
//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("user_1").password(passwordEncoder().encode("123456"))
//        .authorities("ROLE_USER").build());
//        return userDetailsService;
//    }
//
//
//    public static void main(String[] args) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123456"));
//    }
//
//}

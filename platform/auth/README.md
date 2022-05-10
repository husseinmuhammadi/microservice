
[Spring Security: Authentication and Authorization In-Depth](https://www.marcobehler.com/guides/spring-security)

If you turn on debug logging for _org.springframework.security.web.FilterChainProxy_ you will see, for each request, every filter that it passes through.

For example (I am also using Spring Security OAuth).

```
11:18:39.123 FilterChainProxy - /user/login at position  1 of 17 in additional filter chain; firing Filter: 'BasicUserApprovalFilter'
11:18:39.123 FilterChainProxy - /user/login at position  2 of 17 in additional filter chain; firing Filter: 'SecurityContextPersistenceFilter'
11:18:39.124 FilterChainProxy - /user/login at position  3 of 17 in additional filter chain; firing Filter: 'LogoutFilter'
11:18:39.124 FilterChainProxy - /user/login at position  4 of 17 in additional filter chain; firing Filter: 'UsernamePasswordAuthenticationFilter'
11:18:39.124 FilterChainProxy - /user/login at position  5 of 17 in additional filter chain; firing Filter: 'BasicAuthenticationFilter'
11:18:39.124 FilterChainProxy - /user/login at position  6 of 17 in additional filter chain; firing Filter: 'RequestCacheAwareFilter'
11:18:39.124 FilterChainProxy - /user/login at position  7 of 17 in additional filter chain; firing Filter: 'SecurityContextHolderAwareRequestFilter'
11:18:39.124 FilterChainProxy - /user/login at position  8 of 17 in additional filter chain; firing Filter: 'RememberMeAuthenticationFilter'
11:18:39.125 FilterChainProxy - /user/login at position  9 of 17 in additional filter chain; firing Filter: 'ForgotPasswordAuthenticationFilter'
11:18:39.125 FilterChainProxy - /user/login at position 10 of 17 in additional filter chain; firing Filter: 'AnonymousAuthenticationFilter'
11:18:39.125 FilterChainProxy - /user/login at position 11 of 17 in additional filter chain; firing Filter: 'SessionManagementFilter'
11:18:39.125 FilterChainProxy - /user/login at position 12 of 17 in additional filter chain; firing Filter: 'ExceptionTranslationFilter'
11:18:39.125 FilterChainProxy - /user/login at position 13 of 17 in additional filter chain; firing Filter: 'OAuth2ExceptionHandlerFilter'
11:18:39.125 FilterChainProxy - /user/login at position 14 of 17 in additional filter chain; firing Filter: 'VerificationCodeFilter'
11:18:39.125 FilterChainProxy - /user/login at position 15 of 17 in additional filter chain; firing Filter: 'OAuth2AuthorizationFilter'
11:18:39.125 FilterChainProxy - /user/login at position 16 of 17 in additional filter chain; firing Filter: 'OAuth2ProtectedResourceFilter'
11:18:39.125 FilterChainProxy - /user/login at position 17 of 17 in additional filter chain; firing Filter: 'FilterSecurityInterceptor'
```

If you want to get the filters programmatically you can inject the _FilterChainProxy_ and get the _filterChainMap_'s values.

For example:

```
@Autowired var filterChainProxy: FilterChainProxy = _
//...
val filterChains = filterChainProxy.getFilterChainMap.values
```

If you only want to see the filters that _&lt;http&gt;_ adds then you should look at the source for _HttpSecurityBeanDefinitionParser_.





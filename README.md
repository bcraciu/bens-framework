A placeholder is an object able to retrieve the values of defined properties directly in Spring's application context

@Configuration Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

@Component Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

@Configuration is also a @Component as it is meta-annotated with @Component, so @Configuration classes are eligible for component scanning.

Spring @PropertySource annotation is used to provide properties file to Spring Environment. This annotation is used with @Configuration classes. Spring PropertySource annotation is repeatable, means you can have multiple PropertySource on a Configuration class.

Spring @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context. Spring Bean annotation is usually declared in Configuration classes methods. In this case, bean methods may reference other @Bean methods in the same class by calling them directly.
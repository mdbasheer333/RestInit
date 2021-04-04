# RestInit

API testing using Rest Assured + Sping boot

Spring boot:

@Component: Added to class to get identified by sprint (used for @Autowired)
@Autowired: Creates an object automatically by spring; dependency injection
	Object level
	Method level
	Field level
@Value: To get the value from application.properties file
	@Value("${keyname}") 	private List<String> fruits;
	@Value("${keyname:defaultvalue}") private String emp;
	@Value("${keyname:<defaultvalue>}")  can also set to method
@Configuration and @Bean: to autowire external classes, configure manually
@PostConstruct and @PreDestroy: part of bean life cycle, it is like before and after
@Lazy: by default sprint boot creates all objects of autowired, if we want on demand only then use this annotation
Unique bean issue: If more than 1 bean implementation identified for same autowired object, then sprint boot throws an error. Below are the solutions
@Primary: To avoid 1 way is mark any 1 bean as primary.
@ConditionalOnProperty(name = "browser", havingValue = "firefox"): This is other way to handle the unique bean issue, we need to add a property called browser in application properties file and we can run from command line as well like “mvn clean test –Dbrowser=chrome”; Value from the command line takes the precedence over the value present in property file
@ConditionalOnMissingBean: This is other way to handle the unique bean issue. If we pass incorrect value like “mvn clean test –Dbrowser=abcd”;  bean with this annotation acts like a default bean. Note: this should be the bean placed after all bean, otherwise it throws an error
@ConditionalOnExpression: This is to add some express based creation of beans
@Qualifier: Creating bean based on name of it. We need to use same name at autowire annotation
Spring profiles: We crate any number of “application-<<env>>.properties” and choose specific profile while running “mvn clean test –Dspring.profiles.active=env –Dbrowser=chrome”
@Profile(“nameOfProfile”): We can choose specific profile while running like “mvn clean test -Dspring.profiles.active=remote,dev -Dbrowser=firefox”


Creating custom annotations: We can create our own custom annotation if a class or field has many spring boot annotation associated with it.
Bean scope: Bean has 3 scopes, one is Singleton, Prototype & Custom scope
Singleton:
	1 instance of bean
	Shared with all objects and threads
	Good for reporting and logging
Prototype:
	Fresh instance for all
	Note: @PreDestroy does not work
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
Custom scope: We can create our own custom scope bean. We need to override SimpleThreadScope class and implement BeanFactoryPostProcessor interface.
ApplicationContext: getBean(class) method gives you required bean from IOC container on demand
Handling files: 
	Resource: to work with files
	ResourceLoader: to get resources at run time
	@PropertySource(“classpath:filepath”): to read properties file and inject them into class
	PropertiesLoaderUtils: to load N number of files at run time
	@TestPropertySource: to set test specific properties


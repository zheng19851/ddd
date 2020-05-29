[TOC]

### 架构风格
结合了整洁架构风格、CQRS风格以及分层架构风格，分4层，infrastructure、interface-adapter、application、domain
##### 整洁架构：https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

### 使用方式
##### 方式1、如果你的应用没有使用springboot
```
<dependency>
    <groupId>com.runssnail.ddd</groupId>
    <artifactId>ddd-spring</artifactId>
    <version>${ddd.version}</version>
</dependency>
```

增加spring配置，加入配置后，默认自动从spring上下文收集CommandHandler和EventHandler（需要在类上增加spring注解@Service、@Component等）
```
<bean id="commandBus" class="com.runssnail.ddd.spring.CommandBusFactoryBean"/>
<bean id="eventBus" class="com.runssnail.ddd.spring.EventBusFactoryBean"/>
    
```


##### 方式2、如果你的应用使用springboot
```
<dependency>
    <groupId>com.runssnail.ddd</groupId>
    <artifactId>ddd-spring-boot-starter</artifactId>
    <version>${ddd.version}</version>
</dependency>
```

### 重要组件介绍

##### 1、Command
一个Command对象对应一个用例的请求数据
```
@Data
public class CreateProductCommand extends AbstractCommand<Result> {

    private String name;

    private String description;

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

```

##### 2、Event
表示一个领域事件，用例完成后，发布一个领域事件

```
@Getter
public class ProductCreatedEvent extends AbstractEvent {

    private String productId;

    public ProductCreatedEvent(String productId) {
        this.productId = productId;
    }
}
```

##### 3、CommandBus
分发Command到对应的CommandHandler里去处理业务

```
@Component
public class ProductApplicationService {

    @Autowired
    private CommandBus commandBus;

    /**
     * 创建商品
     *
     * @param command
     * @return
     */
    @Transactional
    public Result<Product> createProduct(CreateProductCommand command) {
        return commandBus.dispatch(command);
    }
}

```

##### 4、EventBus
发布一个领域事件

```
  @Component
  public class CreateProductCommandHandler extends BaseCommandHandler<CreateProductCommand, Result> {
  
      // 这里省略...

      @Autowired
      private EventBus eventBus;
  
      @Override
      public Class<CreateProductCommand> supportCommand() {
          return CreateProductCommand.class;
      }
  
      @Override
      public Result<String> doHandle(CreateProductCommand command) {
  
          // 转换领域对象
          Product product = this.productDomainService.createProduct(command);
  
          // 保存数据
          productRepository.save(product);
  
          // 发布领域事件
          eventBus.publish(new ProductCreatedEvent(product.getProductId()));
  
          return Result.success(product.getProductId());
      }
  }

```

##### 5、CommandHandler
用来实现用例，一个用例对应一个CommandHandler，跟Command对应

```
  @Component
    public class CreateProductCommandHandler extends BaseCommandHandler<CreateProductCommand, Result> {
    
        // 这里省略...
    
        @Override
        public Class<CreateProductCommand> supportCommand() {
            return CreateProductCommand.class;
        }
    
        @Override
        public Result<String> doHandle(CreateProductCommand command) {
    
            // do bussniess
         
            return Result.success(product.getProductId());
        }
    }
```
##### 6、CommandInterceptor
用来拦截Command，支持多个CommandInterceptor处理同一个Command
```
@Component
@Order(1)
public class CreateProductInterceptor implements CommandInterceptor<CreateProductCommand, Result> {

    private static final Logger log = LoggerFactory.getLogger(CreateProductInterceptor.class);

    @Override
    public Class<CreateProductCommand> supportCommandType() {
        return CreateProductCommand.class;
    }

    @Override
    public void beforeHandle(CreateProductCommand command) {
        log.info("CreateProductInterceptor.preHandle");
    }

    @Override
    public void afterHandle(CreateProductCommand command, Result result) {
        log.info("CreateProductInterceptor.postHandle");
    }
}

```
##### 7、CommandValidator
用来验证Command参数或者业务前置校验
```
@Component
public class CreateProductCommandValidator implements CommandValidator<CreateProductCommand> {

    @Override
    public Class<CreateProductCommand> supportType() {
        return CreateProductCommand.class;
    }

    @Override
    public void validate(CreateProductCommand createProductCommand) throws IllegalArgumentException, BizException {
        Validate.notNull(createProductCommand);
        Validate.notNull(createProductCommand.getName());
        Validate.isTrue(createProductCommand.getName().length() <= 10);
    }
}
```
##### 8、Assembler
用来组装查询请求数据，将领域实体对象转换成DTO后返回给外部使用，将领域对象封装在内部
```
@Component
public class CreateProductAssembler implements Assembler<Product, ProductDTO> {

    @Override
    public ProductDTO assemble(Product product) {

        ProductDTO target = new ProductDTO();
        BeanUtils.copyProperties(product, target);
        return target;
    }
}

```

##### 9、Converter
实现领域实体对象和数据对象之间的转换
```
@Component
public class ProductConverter implements Converter<Product, ProductDO> {

    @Override
    public ProductDO serialize(Product product) {

        // todo 领域对象转换成数据对象
        ProductDO productDO = new ProductDO();
        productDO.setId(product.getProductId());
        return productDO;
    }

    @Override
    public Product deserialize(ProductDO productDO) {

        // todo 数据对象转换成领域对象
        return new Product(productDO.getId());
    }
}

```


##### 10、ConcurrencyConflicts
用来判断修改数据时，是否产生了并发问题。
```
 @Override
    public void remove(Product product) throws ConcurrencyConflictException {
        int count = this.productDOMapper.deleteById(product.getId(), product.getOperator(), product.getConcurrencyVersion());
        ConcurrencyConflicts.check(count, "remove Product, id={}, concurrencyVersion={}", product.getId(), product.getConcurrencyVersion());
    }
```


### 参考
https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
https://github.com/alibaba/COLA
https://github.com/AxonFramework/AxonFramework
### 架构风格
采用整洁架构风格和CQRS风格，2者结合，分4层，infrastructure、interface-adapter、application、domain
##### 整洁架构：https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

### 接入方式
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
一个Command对象对应一个用例

##### 2、Event
表示一个领域事件，用例完成后，发布一个领域事件

##### 3、CommandBus
分发Command

```
@Component
public class PolicyApplicationService {

    @Autowired
    private CommandBus commandBus;

    /**
     * 创建策略
     *
     * @param command
     * @return
     */
    public CreatePolicyResult createPolicy(CreatePolicyCommand command) {
        return commandBus.dispatch(command);
    }
}

```

##### 4、EventBus
发布一个领域事件

```
  @Component
  public class RemoveRuleTemplateCommandHandler extends BaseCommandHandler<RemoveRuleTemplateCommand, SingleResult> {
  
       // 这里省略。。。
  
      @Autowired
      private EventBus eventBus;
  
      @Override
      protected SingleResult<RuleTemplateDTO> doHandle(RemoveRuleTemplateCommand command) {
  
          // 这里省略。。。
  
          RuleTemplateDTO ruleTemplateDTO = null;
  
          this.eventBus.publish(new RuleTemplateRemovedEvent(ruleTemplateDTO));
  
          return SingleResult.create(ruleTemplateDTO);
      }
  
      @Override
      public Class<RemoveRuleTemplateCommand> supportCommand() {
          return RemoveRuleTemplateCommand.class;
      }
  }
```

##### 5、CommandHandler
用来实现用例，一个用例对应一个CommandHandler，跟Command对应

```
  @Component
  public class RemoveRuleTemplateCommandHandler extends BaseCommandHandler<RemoveRuleTemplateCommand, SingleResult> {
  
       // 这里省略。。。
  
      @Override
      protected SingleResult<RuleTemplateDTO> doHandle(RemoveRuleTemplateCommand command) {
  
          // 这里省略。。。
  
          return SingleResult.create();
      }
  
      @Override
      public Class<RemoveRuleTemplateCommand> supportCommand() {
          return RemoveRuleTemplateCommand.class;
      }
  }
```
##### 6、CommandInterceptor
用来拦截Command，子类实现CommandInterceptor接口
```
@Component
@Order(1)
@Slf4j
public class CreatePolicyCommandInterceptor implements CommandInterceptor<CreatePolicyDefinitionCommand, SingleResult> {

    @Override
    public Class<CreatePolicyDefinitionCommand> supportCommandType() {
        return CreatePolicyDefinitionCommand.class;
    }

    @Override
    public void beforeHandle(CreatePolicyDefinitionCommand command) {
        log.info("CreatePolicyInterceptor.preHandle");
    }

    @Override
    public void afterHandle(CreatePolicyDefinitionCommand command, SingleResult result) {
        log.info("CreatePolicyInterceptor.postHandle");
    }
}
```
##### 7、Validator
用来验证Command，子类实现Validator接口
```
@Component
public class CreateSubPolicyCommandValidator implements Validator<CreateSubPolicyCommand> {

    @Override
    public Class<CreateSubPolicyCommand> supportType() {
        return CreateSubPolicyCommand.class;
    }

    @Override
    public void validate(CreateSubPolicyCommand command) throws IllegalArgumentException, BizException {
        Validate.notNull(command);
        Validate.notNull(command.getName());
        Validate.isTrue(command.getName().length() <= 50, "name cannot over length 50");
    }
}
```
##### 8、Assembler
用来组装查询请求数据，将领域实体对象转换成DTO后返回给上层使用
```
@Component
public class RuleTemplate2DTOAssembler implements Assembler<RuleTemplate, RuleTemplateDTO> {
    @Override
    public RuleTemplateDTO assemble(RuleTemplate ruleTemplate) {

        RuleTemplateDTO dto = new RuleTemplateDTO();
        BeanUtils.copyProperties(ruleTemplate, dto);

        return dto;
    }
}


```

##### 9、Converter
实现领域实体对象和数据对象之间的转换
```
@Component
public class RuleTemplateConverter implements Converter<RuleTemplate, RuleTemplateDO> {
    @Override
    public RuleTemplateDO serialize(RuleTemplate domainObject) {
        RuleTemplateDO dataObject = new RuleTemplateDO();
        BeanUtils.copyProperties(domainObject, dataObject);
        return dataObject;
    }

    @Override
    public RuleTemplate deserialize(RuleTemplateDO dataObject) {
        RuleTemplate domainObject = new RuleTemplate();
        BeanUtils.copyProperties(dataObject, domainObject);
        return domainObject;
    }
}

```
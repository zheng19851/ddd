### 一、maven依赖
##### 1、如果你的应用没有使用springboot
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


##### 2、如果你的应用使用springboot
```
<dependency>
    <groupId>com.runssnail.ddd</groupId>
    <artifactId>ddd-spring-boot-starter</artifactId>
    <version>${ddd.version}</version>
</dependency>
```

### 二、重要组件介绍

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

##### 6、CommandInterceptor
用来拦截Command，子类实现CommandInterceptor接口

##### 7、Validator
用来验证Command，子类实现Validator接口

##### 8、Assembler
用来组装查询请求数据，将领域实体对象转换成DTO后返回给上层使用

##### 9、Converter
实现领域实体对象和数据对象之间的转换

# discard-try
消除以springframework为基础的项目中的try-catch异常处理，并统一响应格式

## 效果

将相应格式包装成如下格式

```json
{
    "code":200,
    "message":"请求成功",
    "data":{
        // 真实业务数据
    }
    
}
```



## 使用场景

满足以下场景时才可以使用

1. 请求响应的是数据而非页面

2. 请求头中的`ACCEPT`应当为`application/json`、`application/json;charset=UTF-8`、`text/xml`(同时需要使用的`HttpMessageConverter`支持)

   

## 用法

### Spring 配置

#### depoly

先将相关jar部署到maven本地库中或者先depoly到私有maven库，然后在pom中引入

#### spring mvc引入

```xml
<dependency>
	<groupId>io.github.benxincaomu</groupId>
	<artifactId>discard-try-mvc</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### spring-webflux引入
```xml
<dependency>
	<groupId>io.github.benxincaomu</groupId>
	<artifactId>discard-try-flux</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 配置

建议使用注解是spring扫描`io.github.benxincaomu.notry`

```java
@Configuration
@ComponentScan("io.github.benxincaomu.notry")
public class Config{
    
}
```



### 正常流程

业务接口只需要正常返回需要的结果即可，无需关注响应格式，由框架进行统一处理。



### 业务异常

使用`io.github.benxincaomu.notry.utils.Asserts`工具类中提供的方法进行简单判断并抛出异常，框架捕获异常，并使用传入的响应码包装为固定格式响应给调用方。

## 嵌入示例

见demo/try-demo
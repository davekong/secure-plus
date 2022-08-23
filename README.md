# secure-ext-spring-boot-starter

## 1、简介
重视安全领域，实现内容脱敏展示，灵活配置，灵活启用，内置丰富插件，支持手机号、邮箱、身份证号、住址、中文名、座机号、银行卡、自定义等多种类型的脱敏配置。


## 2、优点

**易集成：** 只需引入starter包，添加{sensitive.enable}配置即可；

**灵活：** 具体到方法级；

**内置丰富：** 内置多种默认类型，且可根据自身需求，支持自定义脱敏规则

## 3、最新版本
[![Maven Central](https://img.shields.io/maven-metadata/v.svg?label=maven-central&metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fio%2Fgitee%2Fchemors%2Fsecure-ext-spring-boot-starter%2Fmaven-metadata.xml)](https://search.maven.org/artifact/io.gitee.chemors/secure-ext-spring-boot-starter)
## 4、使用步骤
### 4.1 引入依赖
```xml
<dependency>
  <groupId>io.gitee.chemors</groupId>
  <artifactId>secure-ext-spring-boot-starter</artifactId>
  <version>Lastest Version</version>
</dependency>
```
### 4.2 开启脱敏注解
```yaml
sensitive:
  enable: true
```
### 4.3 添加注解
**注意：** 
* 方法注解表示该方法的返回值需要脱敏
* 属性注解标识具体的脱敏规则
#### 4.3.1 添加方法注解
```java
@Desensitization
public Obj test(){
    // 业务逻辑，构建返回对象Obj
    return Obj;
}
```
#### 4.3.2 添加属性注解
```java
 @DesensitizationProp(SensitiveTypeEnum.MOBILE_PHONE)
 private String mobile;
```

## 5、 默认类型说明
* CHINESE_NAME //中文名
* ID_CARD // 身份证号
* FIXED_PHONE // 电话
* MOBILE_PHONE // 手机
* ADDRESS //地址
* EMAIL  //邮箱
* BANK_CARD //银行卡号
* PASSWORD // 密码
* CUSTOM //自定义 （配合 DesensitizationProp 中preLength和sufLength 进行个性化定义）

## 6、demo
* [secure-ext-demo](https://gitee.com/chemors/secure-ext-demo)

## 7、示例

### 7.1 后台数据

```java
       @Desensitization
        public SensitiveEntity simpleData() {
            SensitiveEntity sensitiveEntity = new SensitiveEntity();
            sensitiveEntity.setId("1");
            sensitiveEntity.setName("樱木花道");
            sensitiveEntity.setMobile("15699996666");
            sensitiveEntity.setIdCard("101010200001010001");
            return sensitiveEntity;
        }
```
### 7.2 返回结果
```json
{
  "id": "1",
  "name": "樱***",
  "mobile": "156****6666",
  "idCard": "101010********0001"
}
```


## 8、其他开源项目

* [基于springboot开发的脚手架，旨在迅速搭建开发平台--eboot](https://gitee.com/QuanZhanZhiLu/eboot)
## 6、版权
[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)
## 7、捐赠

## 8、关注我
![](D:\my_doc\陌与尘埃公众号.jpg)


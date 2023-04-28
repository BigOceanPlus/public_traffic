# 1.项目结构
## 1.1 com.example.dachuang
###本项目采用2层解耦
### 1.1.1 common
#### Result --统一封装的实体类
### 1.1.2 config（存放配置文件）
#### ExceptionConfig（全局异常配置）
### 1.1.3 controller（二层）
#### ModelController（控制模型处理相关）
#### RoadController（目前已经弃用）
#### UserController（控制用户登录相关）
### 1.1.4 dao（一层）
#### ModelDao（获取模型数据）
#### RoadDao（目前已经弃用）
#### UserDao（获取用户数据）
#### VertexDao（目前已经弃用）
### 1.1.5 entity（实体类）
#### ModelInput（模型输入类）
#### ModelOutput（模型输出类）
#### Point（坐标点）
#### RangeRequest（目前已经弃用）
#### Road（目前已经弃用）
#### User（用户类）
#### Vertex（目前已经弃用）
### 1.1.6 utils（工具类）
#### SpringContextUtil（获取Bean的工具类）
## 1.2 resources
### 1.2.1 mappers（数据持久层）
#### ModelMapper.xml（模型操作映射）
#### RoadDao.xml（目前已经弃用）
#### UserMapper.xml（用户操作映射）
#### VertexMapper.xml（目前已经弃用）
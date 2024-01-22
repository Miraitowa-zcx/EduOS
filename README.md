### 教育系统安卓开发

<br>

**项目结构**

```
EduOS
├─app     App 安卓客户端
│    ├─ src  源码
│    │    ├─ main  主模块
│    │    │    ├─ java  Java 代码
│    │    │    │    ├─ com.eduos  包名
│    │    │    │    │    ├─ activity  Activity 代码
│    │    │    │    │    │    └─ MainActivity  主界面
│    │    │    │    │    └─ LoginActivity  登录界面
│    │    │    │    └─ LoginActivity  登录界面
│    │    │    └─ fragment  Fragment 代码
│    │    │    │    │    └─ HomeFragment  首页界面
│    │    │    │    └─ LoginFragment  登录界面
│    │    └─ res  资源文件
│    │        ├─ drawable  图片资源
│    │        │    └─ ic_launcher  图标
│    │        └─ mipmap  图标资源
│    │            └─ ic_launcher_round  图标
│    └─ gradle  编译文件
│        └─ build.gradle  编译文件
├─server  服务器端
│    ├─ src  源码
│    │    ├─ main  主模块
│    │    │    ├─ java  Java 代码
│    │    │    │    └─ com.eduos  包名
│    │    │    │    │    └─ Server  服务器端
│    │    │    └─ Server  服务器端
│    │    └─ resources  资源文件
│    │        └─ application.properties  服务器配置文件
│    └─ pom.xml  编译文件
│        └─ server.iml  服务器模块
├─README.md  项目说明文件
```

<br>

# mp-control

一个专为公众号运营者打造的菜单配置与管理工具，前后端分离的综合项目。前端使用Vue 3 + Vite构建，后端基于Spring Boot框架，帮助您高效管理微信公众号菜单与自动回复功能。

---

## 🎯 解决痛点

在运营公众号时，你是否遇到过这些痛点？

👉 每次微调菜单都得钻进微信公众平台后台，操作复杂，效率低下？  
👉 想实现复杂点的公众号菜单效果，却被“开发者”、“API接口”劝退？  
👉 管理各种菜单事件手忙脚乱？关注欢迎语、跳转链接、进入小程序…？

今天推荐一个公众号菜单配置神器，**无需一行代码，3分钟轻松搞定**自定义菜单配置 + 自动回复管理！

---

## ✨ 核心功能

🔥 **为什么说它是“神器”？核心功能直击痛点：**

- 📩 **智能消息推送**：用户点击菜单项？立刻自动回复你预设的图文消息！互动体验瞬间拉满。
- 🔗 **无缝页面跳转**：想引导用户看H5页面？一键设置，点击菜单直接精准跳转，路径清晰。
- 🛠️ **小程序直达快车道**：推广小程序？设置菜单直达指定小程序页面，转化路径极速缩短。
- ⏱️ **关注欢迎语定制**：第一印象决定留存！轻松设置关注后自动回复的消息，定时二次回复。
- ⚡ **全场景事件对接**：以上所有点击动作，均严格遵循标准微信事件机制触发，稳定可靠。

---

## 🚀 三步完成配置

✨ **超简单！三步完成配置（运行效率飙升）：**

1. 访问网站 [http://mp.roper.com.cn](http://mp.roper.com.cn)，简单注册/登录。
2. 跟随操作教程，按要求填写公众号基础信息。
3. 填写菜单类型以及回复内容（文字/网页/小程序）  
   ✅ 点击确认，配置完成！菜单即刻生效到你的公众号。

---

## 🖼 界面预览

### 后台管理界面
![后台界面](https://pic.pxerp.cc/20250209/wechat_2025-09-06_115130_726.png)

### 前台操作界面
![前台界面](https://pic.pxerp.cc/20250209/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20250904120419_722_20.jpg)

### 動圖
![Git Repo](https://pic.pxerp.cc/20250209/1d4f7048dcaaa99e531c2db57cab5046%2000_00_00-00_00_30.gif)

---

## 🎉 限时免费！

双核配置，效率翻倍！  
>> 马上点击 [https://mp.roper.com.cn](https://mp.roper.com.cn)，开启3分钟高效配置时代！ <<

---

## 👥 联系我们

如有问题或建议，欢迎扫码加入我们的技术交流群或发送邮件至：

![微信群二维码](https://pic.pxerp.cc/20250209/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20250906120515_46_38.jpg)  
邮箱：[mrcroper@gmail.com](mailto:mrcroper@gmail.com)

---

## 前端 (mp-cms)

### 推荐的IDE设置

- [VSCode](https://code.visualstudio.com/) 
- [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (请禁用Vetur)
- [TypeScript Vue 插件 (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin)

### 项目设置

```sh
npm install
### 项目设置

```sh
npm install
```

### 编译和热加载用于开发

```sh
npm run dev
```

### 类型检查、编译和最小化用于生产

```sh
npm run build
```

## 后端 (wcSassMpControl)

后端是使用Spring Boot构建的Java应用，包含了多个模块，如用户管理、产品管理、应用管理等。该项目使用了MyBatis作为ORM框架，并集成了JWT用于身份验证。

### 主要特性

- 用户认证与授权
- 应用管理
- 产品管理
- 组织架构管理
- 文件上传与处理
- 微信接口集成

### 技术栈

- Spring Boot
- MyBatis
- JWT
- Redis
- MySQL

### 配置

配置文件位于`src/main/resources`目录下，包括开发环境(`application-dev.yml`)、生产环境(`application-prod.yml`)等。

### 启动项目

确保已安装Java环境，然后运行：

```sh
mvn spring-boot:run
```

## 许可证

该项目遵循MIT许可证。详情请查看LICENSE文件。
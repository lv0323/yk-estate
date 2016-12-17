# ESTATE

本系统仅供律云及律云授权的用户使用


> **系统运行环境要求：**

> - **JDK 1.8**, 基于JDK 1.8.0_101进行开发
> - **Gradle 3.0或更新版本**，基于Gradle 3.2.1进行开发，最低要求2.6版本
> - **Intellij IDEA**

将代码clone至本地之后，使用IDEA将项目导入，导入的时候，请选择Gradle项目

初次导入过程中需要下载大量插件及依赖，请耐心等待

运行和Debug该项目十分简单，请在Gradle完整导入项目之后，在IDEA界面中的菜单项选择

> - Run -> Edit Configurations... -> 弹出的窗口中左上角的加号选择Gradle新建一个启动项
> - 在右侧的Name中填入可以让你记住的任务名，在Gradle Project中选择项目的根目录，在下方的Tasks填入:estate-rest:bootRun
> - 添加job的启动方式，:estate-job:bootRun
> - 添加mgt的启动方式，:estate-mgt:bootRun
> - 保存之后即可在右侧的Gradle任务栏中启动项目，直接双击或者右键选择Run，则以正常方式启动；右键选择Debug启动，则以Debug模式启动，IDEA会自动Attach到该项目中，十分方便，并且FreeMarker也可以实时编译
### AppViewScreen全埋点方案

###### 1、关键技术Application.ActivityLifecycleCallbacks

> - 使用技术的缺点：注册Application.ActivityLifecycleCallbacks回调要求API 14+
>
>   ​
>
>   ps：其实还可以通过自定义BaseActivity让其他的activity继承这个activity来实现页面浏览事件监测，这种方案理论可行，但是不是最优，一些第三方的sdk内的activity我们不能让他也继承我们定义的activity。

###### 2、Application.ActivityLifecycleCallbacks方式解决方案

>1、在用户自定义的Application类中初始化埋点SDK，传入当前Application对象。
>
>2、埋点sdk拿到Application对象后注册activity生命周期回调。
>
>3、生命周期的回调方法中获得activity的相应生命周期方法回调
>
>4、调用sdk的相关接口触发页面浏览事件

###### 3、简单实现

> 1、在ActivityLifecycleCallbacks的onResume 方法回调时采集页面基本信息，主要为包名+类名。
>
> 2、解决权限申请带来的困扰（设计接口，提供跳过、添加指定activity的功能）
>
> 3、扩展采集功能，收取更多的activity信息（页面标题）

### 使用方式：

###### 1、引入

> 使用前首先需要导入module或者aar，这里就简介下aar的使用：
>
> [1、下载aar](https://github.com/sunnnydaydev/BuryingPoint/tree/master/autotrackappviewscreensdk/aar)
>
> [2、aar使用参考](https://blog.csdn.net/qq_38350635/article/details/100937972)

###### 2、基本使用

> 这个简单的sdk只是采集应用中的每个activity信息，每当用户打开一个activity时，日志就会打印（通过包名+activity名字过滤）

```java
 private void initAutoTrackViewScreenSdk(Application application) {
        SensorsDataAPI.init(application);
    }
// log 栗子：
com.sunnyday.administrator.buryingpoint I/SensorsDataAPI: {
    	"event":"$AppViewScreen",
    	"device_id":"779a1c98b906d7ac",
    	"properties":{
    		"$lib":"android",
    		"$os_version":"9",
    		"$app_name":"BuryingPoint",
    		"$lib_version":"1.0.0",
    		"$model":"vivo Z1",
    		"$os":"Android",
    		"$screen_width":1080,
    		"$screen_height":2075,
    		"$manufacturer":"vivo",
    		"$app_version":"1.0",
    		"$activity":"com.sunnyday.administrator.buryingpoint.MainActivity",
    		"$title":"BuryingPoint"
    	},
    	"time":1582536705835
    }
```


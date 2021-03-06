### AppStart & AppEnd全埋点方案

### 一、安卓的五种进程补充

> Android中有五种进程：前台进程，可见进程，服务进程，后台进程，空进程。
>
> 1、前台进程：目前正在屏幕上显示的进程和一些系统进程，也就是和用户正在交互的进程。（app start我们认为app开启啦）
>
> 2、可见进程：进程可见但是不可交互。例如ActivityA弹出对话框B，这时半透明的对话框遮住了A，但是A可见。但是A不能与我们交互啦（我们需要退出B才能再次和A交互）。
>
> 3、服务进程：服务进程是通过 startService() 方法启动的进程，但不属于前台进程和可见进程。例如，在后台播放音乐或者在后台下载就是服务进程。想让服务进程变前台进程，在服务中开个通知栏即可。
>
> 4、后台进程：后台进程指的是目前对用户不可见的进程。例如我正在使用qq和别人聊天，这个时候qq是前台进程，但是当我点击Home键让qq界面消失的时候，这个时候它就转换成了后台进程。当内存不够的时候，可能会将后台进程回收。（app end，我们认为app退出啦）
>
> 5、空进程：空进程指的是在这些进程内部，没有任何东西在运行。保留这种进程的的唯一目的是用作缓存，以缩短该应用下次在其中运行组件所需的启动时间。
>

### 二、判断应用进程是否在前台

###### [1、github流行的几种方案](https://github.com/sunnnydaydev/AppIsForground)，但是这些方案都不能解决如下两问题：

> 1、应用程序如果有多个进程该如何判断？
>
> > 1、转换为跨进程通信的问题
> >
> > 2、思考安卓跨进程的常见方式，及其利弊
> >
> > 3、选取ContentProvider，因为他有回调监听ContentObserver。
> >
> > 4、一般来说采取ContentProvider+SQLite3来解决跨进程共享数据。（SQLite3根据需求可以使用sp代替）
>
> 2、应用程序如果发生崩溃、被强杀该如何判断应用是处于前台还是后台？
>
> > 使用session概念来解决

### 三、多进程下判断app是否处于前台的原理

###### 1、基本原理

> ContentProvider+SharedPreferences+Session概念

###### 2、原理图解

![原理图](https://github.com/sunnnydaydev/BuryingPoint/blob/master/photos/pageout.png)

###### [项目module地址](https://github.com/sunnnydaydev/BuryingPoint/tree/master/autotrackappstartappendsdk)
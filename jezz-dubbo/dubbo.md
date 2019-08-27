1.dubbospi的优势在于用的时候再去调用
2.dubbo 通过SPI来进行Bean的管理与引用
那dubbo想在程序运行的时候调用SPI中管理类和方法,再通过运行参数来决定调用哪个实现类,就要靠自适应扩展机制,
而这个时候SPI中的管理的类和方法还没有被加载,所以这个时候只能通过代理类代理,在代理类的方法中去进行判断需要调用哪个实现类,再去加载这个实现类的目标方法。
dubbo是通过@Adaptive注解来标识类和方法的,加在类上不会生成代理类，加在接口方法上会生成相应的代理逻辑。
dubbo首先会检测该接口方法的方法是否添加了@Adaptive注解，如果一个都没有就会抛出异常，通过过了@Adaptive注解检测之后会生成方法代码逻辑，而没有被@Adaptive修饰的方法
就只会在方法里面抛出一个异常代码。代码生成逻辑的一个重要的任务是从方法的参数列表或者其他参数中获取 URL 数据，@Adaptive注解也会有注解值。
dubbo根据SPI和@Adaptive注解值生成"获取扩展名逻辑"，同时也受invocation类型参数的影响。 生成代码逻辑之后，dubbo默认使用javassit编译这段代码加载进JVM得到class对象
再利用反射生成代理类。代理类生成后，从URL对象中获取参数找到最终调用的实现类。


3.服务导出
Dubbo 服务导出过程始于 Spring 容器发布刷新事件，Dubbo 在接收到事件后，会立即执行服务导出逻辑。
第一部分是前置工作，主要用于检查参数，组装URL
(检测并处理泛化服务和普通服务类 检测 <dubbo:service> 标签的 interface 属性合法性，不合法则抛出异常，检测本地存根配置
对providerConfig applicationConfig核心配置类对象进行检测，为空就从其他配置类获取响应的实例，再检测applicationConfig和RegisterConfig，为空则尝试创建，若无法创建抛异常)
第二部分是导出服务，包含导出服务到本地jvm，导出服务到远程
（ 首先是 export 配置，这个配置决定了是否导出服务。有时候我们只是想本地启动服务进行一些调试工作，我们并不希望把本地启动的服务暴露出去给别人调用。
 此时，我们可通过配置 export 禁止服务导出  <dubbo:provider export="false" /> ）
第三部分是向注册中心注册服务，用于服务发现

4.

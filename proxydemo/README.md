1、JDK和CGLIB动态代理的区别

JDK代理使用的是反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。

CGLIB代理使用字节码处理框架asm，对代理对象类的class文件加载进来，通过修改字节码生成子类。

JDK创建代理对象效率较高，执行效率较低；

CGLIB创建代理对象效率较低，执行效率高。

JDK动态代理机制是委托机制，只能对实现接口的类生成代理，通过反射动态实现接口类；

CGLIB则使用的继承机制，针对类实现代理，被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的，因为是继承机制，不能代理final修饰的类。

JDK代理是不需要依赖第三方的库，只要JDK环境就可以进行代理，需要满足以下要求：
+ 1.实现InvocationHandler接口，重写invoke()
+ 2.使用Proxy.newProxyInstance()产生代理对象
+ 3.被代理的对象必须要实现接口

CGLib 必须依赖于CGLib的类库,需要满足以下要求：
+ 1.实现MethodInterceptor接口，重写intercept()
+ 2.使用Enhancer对象.create()产生代理对象

2、使用JDK还是CGLIB

1)如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP，可以强制使用CGLIB实现AOP

2)如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
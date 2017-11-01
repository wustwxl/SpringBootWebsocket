**_SpringBoot + SpringMVC + Mybatis + Swagger2 + Websocket_**


`统一异常处理`
工具包utils中的MyException为统一异常处理类
返回类型主要包括两个属性 String code, String msg。
code 为异常类型,
msg 为异常信息
  
`统一返回类型`
工具包utils中的MessageVo为统一返回类型处理类
返回类型主要包括三个属性 String code, String info, Object data。
code 为处理结果对应编码, 
info 为处理结果对应信息, 
data 为接口返回数据。

`Websocket`
Websocket实现了浏览器与服务器全双工(full-duplex)通信——允许服务器主动发送信息给客户端。
测试Websocket:
http://localhost:63342/demo/templates/socket.html

`使用HttpClient发送请求、接收响应`
一般需要如下几步即可。
1. 创建HttpClient对象。
2. 创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
3. 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HetpParams params)方法来添加请求参数；对于HttpPost对象而言，也可调用setEntity(HttpEntity entity)方法来设置请求参数。
4. 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse。
5. 调用HttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
6. 释放连接。无论执行方法是否成功，都必须释放连接

`Banner`
启动Spring Boot项目的时候，控制台会默认输出启动图案
1.在src/main/resources下新建一个banner.txt文档 
2.通过http://patorjk.com/software/taag网站生成需要的字符，将字符拷贝到步骤1所创建的txt文档中
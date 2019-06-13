# java文件操作getAbsolutePath和getCanonicalPath的区别
## getCanonicalPath函数主要是将路径进行了无歧义的处理，即将文件路径中的相对路径符号去掉了，这样的好处是可以防止一些注入攻击
例如：/User/chan/DownLoads/chan/settings.xml
测试程序代码如下：
```bash
String filepath = "/User/chan/DownLoads/chan/../chan/settings.xml";
File f = new File(filepath);
System.out.println(f.getAbsolutePath());
System.out.println(f.getCanonicalPath());
```

输出结果为：

```bash
/User/chan/DownLoads/chan/../chan/settings.xml

<pre name="code" class="java">/User/chan/DownLoads/chan/settings.xml
```

也就是说，getCanonicalPath函数会将路径归一化之后进行无歧义的比较，可以防止一些安全注入威胁
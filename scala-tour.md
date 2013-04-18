# Scala 指南

## 基本

### 表达式和值

在Scala中，几乎所有的语言元素都是表达式。println("hello wolrd")是一个表达式， "hello"+" world"
也是一个表达式。

可以通过val定义一个常量，亦可以通过var定义一个变量。推荐多使用常量。

```
var helloWorld = "hello" + " world" 
println(helloWorld)

val again = " again" 
helloWorld = helloWorld + again
println(helloWorld)
```

### 函数是一等公民

可以使用def来定义一个函数。函数体是一个表达式。

使用Block表达式的时候，默认最后一行的返回是返回值，无需显示指定。

函数还可以像值一样，赋值给var或val。因此他也可以作为参数传给另一个函数。

```
def square(a: Int) = a * a

def squareWithBlock(a: Int) = {
  a * a
}

val squareVal = (a: Int) => a * a

def addOne(f: Int => Int, arg: Int) = f(arg) + 1

println("square(2):" + square(2))
println("squareWithBlock(2):" + squareWithBlock(2))
println("squareVal(2):" + squareVal(2))
println("addOne(squareVal,2):" + addOne(squareVal, 2))
```



### 借贷模式

由于函数可以像值一样作为参数传递，所以可以方便的实现借贷模式。

这个例子是从/proc/self/stat文件中读取当前进程的pid。

withScanner封装了try-finally块，所以调用者不用再close。

注：当表达式没有返回值时，默认返回Unit。

```
  import scala.reflect.io.File
  import java.util.Scanner

  def withScanner(f: File, op: Scanner => Unit) = {
    val scanner = new Scanner(f.bufferedReader)
    try {
      op(scanner)
    } finally {
      scanner.close()
    }
  }

  withScanner(File("/proc/self/stat"),
    scanner => println("pid is " + scanner.next()))
```


### 按名称传递参数

这个例子演示了按名称传递参数，最末有1/0这个明显会产生异常的计算，运行该程序会产生异常。

试着将def log(msg: String)修改为def log(msg: => String)。由按值传递修改为按名称传递后将不会产生异常。


因为log函数的参数是按名称传递，参数会等到真正访问的时候才会计算，由于logEnable = false，所以被跳过。


按名称传递参数可以减少不必要的计算和异常。


```
  val logEnable = false

  def log(msg: String) =
    if (logEnable) println(msg)

  val MSG = "programing is running"

  log(MSG + 1 / 0)
```

### 类定义

可以用class关键字来定义类。并通过new来创建类。
在定义类时可以定义字段，如firstName，lastName。这样做还可以自动生成构造函数。
可以在类中通过def定义函数。var和val定义字段。
函数名是任何字符如+,-,*,/。
例子中obama.age_=(51)的函数调用，可以简化为obama.age = 51 。
obama.age()的函数调用，可以省略小括号，简化为obama.age。

```
  class Persion(val firstName: String, val lastName: String) {

    private var _age = 0
    def age = _age
    def age_=(newAge: Int) = _age = newAge

    def fullName() = firstName + " " + lastName

    override def toString() = fullName()
  }

  val obama: Persion = new Persion("Barack", "Obama")

  println("Persion: " + obama)
  println("firstName: " + obama.firstName)
  println("lastName: " + obama.lastName)
  obama.age_=(51)
  println("age: " + obama.age())
```


### 鸭子类型

走起来像鸭子，叫起来像鸭子，就是鸭子。
这个例子中使用{ def close(): Unit }作为参数类型。因为任何含有close()的函数的类都可以作为参数。
不必使用继承这种不够灵活的特性。

```
  def withClose(closeAble: { def close(): Unit }, op: { def close(): Unit } => Unit) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    def close() = println("close Connection")
  }

  val conn: Connection = new Connection()
  withClose(conn, conn =>
    println("do something with Connection"))
```


### 柯里化 

这个例子和上面的功能相同。不同的是使用了柯里化（Currying)的技术。
def add(x:Int, y:Int) = x + y 是普通的函数
def add(x:Int) = (y:Int) => x + y 是柯里化后的函数，相当于返回一个匿名函数表达式。
def add(x:Int)(y:Int) = x + y 是上面的简化写法
柯里化可以让我们构造出更像原生语言提供的功能的代码
例子中的withclose(...)(...)换成withclose(...){...}
是否和java中的synchronized关键字用法很像？

```
  def withClose(closeAble: { def close(): Unit })(op: { def close(): Unit } => Unit) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    def close() = println("close Connection")
  }

  val conn: Connection = new Connection()
  withClose(conn)(conn =>
    println("do something with Connection"))
```


### 范型

上面的例子可以使用泛型变得更简洁更灵活。
试着将val msg = "123456"修改为val msg = 123456。
虽然msg由String类型变为Int类型，但是由于使用了泛型，代码依旧可以正常运行。

```
  def withClose[A <: { def close(): Unit }, B](closeAble: A)(op: A => B) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    val msg = "123456"
    def close() = println("close Connection")
  }

  val conn: Connection = new Connection()
  val msg = withClose(conn) { conn =>
    {
      println("do something with Connection")
      conn.msg
    }
  }
  
  println(msg)
```

### Traits

Traits就像是有函数体的Interface。使用with关键字来混入。
这个例子是给java.util.ArrayList添加了foreach的功能。
试着再在后面加上with JsonAble，给list添加toJson的能力

```
  trait ForEachAble[A] {
    def iterator: java.util.Iterator[A]
    def foreach(f: A => Unit) = {
      val iter = iterator
      while (iter.hasNext)
        f(iter.next)
    }
  }

  trait JsonAble {
    override def toString() =
      scala.util.parsing.json.JSONFormat.defaultFormatter(this)
  }

  val list = new java.util.ArrayList[Int]() with ForEachAble[Int]
  list.add(1); list.add(2)

  list.foreach(x => println(x))
  println("Json: " + list.toString())
```

### Pattern Matching

## 进阶

## 并发

## 实践

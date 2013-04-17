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

### 类

专著与Getter 和 Setter 的省略

鸭子

#### Objects


#### Traits



#### Packages

### Pattern Matching

## 进阶

## 并发

## 实践

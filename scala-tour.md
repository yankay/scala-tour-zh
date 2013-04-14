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



### 柯里化


### 按名称传递




### 类

#### Traits

#### Objects

#### Packages

### Pattern Matching

## 进阶

## 并发

## 实践

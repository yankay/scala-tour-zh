def square(a: Int) = a * a

def squareWithBlock(a: Int) = {
  a * a
}

val squareVal = (a: Int) => a * a

val addOne(f:Int => Int,arg:Int) = f(arg) + 1


println("square(2):" + square(2))
println("squareWithBlock(2):" + squareWithBlock(2))
println("squareVal(2):" + squareVal(2))
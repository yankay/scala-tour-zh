
import java.util.Date
import scala.runtime.RichInt
import scala.runtime.RichInt

object test {
  def main(args: Array[String]) {
  }
  import org.specs2.mutable._

  class FactorialSpec extends Specification {
    args.report(color = false)

    def factorial(n: Int) = (1 to n).reduce(_ * _)

    "The 'Hello world' string" should {
      "factorial 3 must be 6" in {
        factorial(3) mustEqual 6
      }
      "factorial 4 must be 6" in {
        factorial(4) must greaterThan(6)
      } 
    }
  }
  specs2.run(new FactorialSpec)

}




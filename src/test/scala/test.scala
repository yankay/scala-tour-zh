

object test {
  def main(args: Array[String]) {
    import akka.actor.{ Actor, ActorSystem, Props }

    val system = ActorSystem()

    class EchoServer extends Actor {
      def receive = {
        case msg: String => println("echo " + msg)
      }
    }

    val echoServer = system.actorOf(Props[EchoServer])
    echoServer ! "hi"

    system.shutdown

  }

}




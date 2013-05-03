

object test {
  def main(args: Array[String]) {

  }
  import akka.actor.{ Actor, ActorSystem, Props }

  implicit val system = akka.actor.ActorSystem()

  class EchoServer extends Actor {
    def receive = {
      case msg: String => println("echo " + msg)
    }
  }

  val server = system.actorOf(Props[EchoServer], name = "echoServer")

  val echoClient = system
    .actorFor("akka://RemoteSystem@127.0.0.1:2552/user/echoServer")
  echoClient ! "Hi Remote"

  system.shutdown

}




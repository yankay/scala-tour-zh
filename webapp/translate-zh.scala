object translate {
  val translate = scala.collection.mutable.Map[String, String]()
  translate += (" Tour" -> " 指南")
  translate += ("Glance" -> "概览")
  translate += ("About" -> "关于")

  def main(args: Array[String]) {
    val t = if (args.isEmpty) translate else translate.map(_.swap)
    scala.io.Source.fromInputStream(System.in, "UTF-8").getLines.map(
      t.keys.foldLeft(_)((b, a) => b.replace(a, t.get(a).get))).foreach(println _)
  }
}


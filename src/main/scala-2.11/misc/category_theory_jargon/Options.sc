
val foo: Option[String] = Some("Hello World")

val bar: Option[String] = None

val fooResult = foo match {
  case Some(s) => s
  case None    => "Nix"
}

val barResult = bar match {
  case Some(s) => s
  case None    => "Nix"
}

val fooResult2 = foo map { _.toString } getOrElse "Nix gefunden"

val barResult2 = bar map { _.toString } getOrElse "Nix gefunden"

val fooResult3 = foo.fold("Nothing")( _.toString )

val barResult3 = bar.fold("Nothing")( _.toString )



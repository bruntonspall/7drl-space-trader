package gen

import scala.util.Random

/** Randomly generated names for star systems */
object SystemName {
  def next: String = {
    val prefixes = Seq(
      "An",
      "Aqua",
      "Arc",
      "Bel",
      "Borg",
      "Cap",
      "Gem",
      "Ere",
      "Herc",
      "Klen",
      "Kul",
      "Lup",
      "Pisc",
      "Proc",
      "Pup",
      "Serp",
      "Vir",
      "Xy",
      "Zen"
    )

    val middles = Seq(
      "dath",
      "deth",
      "drom",
      "ittar",
      "pen",
      "r",
      "s",
      "t",
      "taur",
      "tul",
      "tur",
      "ron",
      "vil",
      "vus"
    )

    val suffixes = Seq(
      "eda",
      "eno",
      "ens",
      "i",
      "o",
      "a",
      "le",
      "um",
      "ium",
      "ius",
      "io",
      "ia"
    )

    Random.branch(
      Random.oneOf(prefixes) + Random.oneOf(middles) + Random.oneOf(suffixes),
      Random.oneOf(prefixes) + Random.oneOf(suffixes)
    )
  }
}

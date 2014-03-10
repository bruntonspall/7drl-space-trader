package gen

import scala.util.Random

object Stars {
  /** Randomise these a bit so we sometimes get denser star fields in games? */
  val Octaves = 30
  val Frequency = 1
  val GlobalDensity = 0.1
  val MaxBrightness = 255

  def generate(width: Int, height: Int) = {
    /** Use Perlin noise so that stars cluster together */
    def localDensity(x: Int, y: Int) = Perlin.perlinNoise2d(x, y, Octaves, Frequency)

    for {
      x <- 0 until width
    } yield for {
      y <- 0 until height
    } yield {
      val lD = localDensity(x, y)

      if (Random.nextFloat() < lD * GlobalDensity) {
        val brightness = Random.nextFloat() * lD * MaxBrightness

        /** We can use brightness to determine what glyph to use */
        Some(brightness)
      } else {
        None
      }
    }
  }
}

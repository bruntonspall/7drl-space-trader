package gen

import scala.util.Random
import model.{Glyph, Position, System}

object Stars {
  /** Randomise these a bit so we sometimes get denser star fields in games? */
  val Octaves = 30
  val Frequency = 1
  val GlobalDensity = 0.05
  val MaxBrightness = 255

  /** Use Perlin noise so that stars cluster together */
  def localDensity(x: Int, y: Int) = Perlin.perlinNoise2d(x, y, Octaves, Frequency)

  /** I used this function for testing (and am keeping it here for that purpose). See below for useful version.) */
  def generate(width: Int, height: Int) = {
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

  def generateSystems(width: Int, height: Int) = {
    for {
      x <- 0 until width
      y <- 0 until height
      lD = localDensity(x, y)
      if Random.nextFloat() < lD * GlobalDensity
    } yield {
      val brightness = Random.nextFloat() * lD * MaxBrightness

      val glyph = if (brightness > 250) {
        /** Very bright */
        'X'
      } else if (brightness > 100) {
        /** Pretty bright */
        '*'
      } else {
        '.'
      }

      System(SystemName.next, Glyph(glyph, bold = false, 0), Position(x, y))
    }
  }
}

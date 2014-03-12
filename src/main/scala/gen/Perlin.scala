package gen

/** Perlin noise generation, see http://freespace.virgin.net/hugo.elias/models/m_perlin.htm */
object Perlin {
  def noise(x: Int, y: Int): Double = {
    val m = x + y * 57
    val n = (m << 13) ^ x
    1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0
  }

  def smoothedNoise(x: Int, y: Int) = {
    val corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16
    val sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8
    val center = noise(x, y) / 4
    corners + sides + center
  }

  /** Using cosine interpolation here */
  def interpolate(a: Double, b: Double, x: Float) = {
    val ft = x * 3.1415927
    val f = (1 - Math.cos(ft)) * 0.5
    a * (1 - f) + b * f
  }

  def interpolatedNoise(x: Float, y: Float) = {
    val intX = x.toInt
    val intY = y.toInt

    val fracX = x - intX
    val fracY = x - intY

    val v1 = smoothedNoise(intX, intY)
    val v2 = smoothedNoise(intX + 1, intY)
    val v3 = smoothedNoise(intX, intY + 1)
    val v4 = smoothedNoise(intX + 1, intY + 1)

    val i1 = interpolate(v1, v2, fracX)
    val i2 = interpolate(v3, v4, fracX)

    interpolate(i1, i2, fracY)
  }

  def perlinNoise2d(x: Int, y: Int, numberOfOctaves: Int, persistence: Int) = {
    (0 until numberOfOctaves).foldLeft(0d) {
      case (total, i) =>
        val freq = Math.pow(2, i).toFloat
        val amp = Math.pow(persistence, i).toFloat

        val newTotal = total + interpolatedNoise(x * freq, y * freq) * amp

        newTotal
    }
  }
}


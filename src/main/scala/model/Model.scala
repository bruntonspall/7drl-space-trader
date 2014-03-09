package model

class Glyph(val chr: Char, val bold: Boolean, val colour: Integer) {

}

/** Not going for immutability, we can think if there's a way to approach immutability later */
class Position(var x: Int, var y: Int)

class Renderable(val glyph: Glyph, val position: Position)

case class System(name: String, override val glyph: Glyph, override val position: Position) extends Renderable(glyph, position)
case class Ship(id: String, override val glyph: Glyph, override val position: Position) extends Renderable(glyph, position)

class World {
  def systems: Map[Position, System] = ???
  def ships: Map[Position, Ship] = ???
}

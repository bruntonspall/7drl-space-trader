package model

case class Glyph(chr: Char, bold: Boolean, colour: Integer)

/** Not going for immutability, we can think if there's a way to approach immutability later */
class Position(var x: Int, var y: Int)
object Position {
  def apply(x: Int, y: Int) = new Position(x,y)
}

class Renderable(val glyph: Glyph, val position: Position)

case class System(name: String, override val glyph: Glyph, override val position: Position) extends Renderable(glyph, position)
case class Ship(id: String, override val glyph: Glyph, override val position: Position) extends Renderable(glyph, position)

class World {

  var systems: Map[Position, System] = Map(
    Position(2,2) -> System("Earth", Glyph('O', true, 0), Position(2,2)),
    Position(21,17) -> System("Earth", Glyph('x', true, 0), Position(21,17))
  )
  var ships: Map[Position, Ship] = Map.empty[Position,Ship]

  def addShip(ship: Ship) = ships = ships + (ship.position -> ship)
}

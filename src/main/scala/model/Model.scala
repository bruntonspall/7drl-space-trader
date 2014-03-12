package model

case class Glyph(chr: Char, bold: Boolean, colour: Integer)

/** Not going for immutability, we can think if there's a way to approach immutability later */
case class Position(var x: Int, var y: Int)

case class Viewport(var sx: Int, var sy: Int, width: Int, height: Int) {
  def translate(p: Position): Position = Position(p.x-sx, p.y-sy)
  def visible(p: Position): Boolean = p.x >= sx && p.x < sx+width && p.y >= sy && p.y < sy+height
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

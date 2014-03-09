import model._
import model.Glyph
import model.Ship
import ncurses4j.NCurses
object Main {
  val world = new World()
  val screen = NCurses.initscr()
  val maxx = NCurses.getmaxx(screen)
  val maxy = NCurses.getmaxy(screen)
  val player = Ship("Player 1", Glyph('@', true, 0), Position(10,10))
  world.addShip(player)

  def renderScreen() {
    NCurses.erase()
    world.systems.values.foreach(render)
    world.ships.values.foreach(render)
    NCurses.mvprintw(maxy,0,"[q]uit, [hjkl] move")
    NCurses.refresh()
  }

  def renderShips() {
  }

  def render(r: Renderable) {
    NCurses.mvprintw(r.position.y, r.position.x, r.glyph.chr.toString)
  }

  def main(args: Array[String]) {
    var stop = false;
    while (!stop) {
      renderScreen()

      val i = NCurses.getch()
      i match {
        case 10 => stop = true // Enter
        case 27 => stop = true // Escape
        case 113 => stop = true // q
        case 104 => player.position.x -= 1// h
        case 106 => player.position.y += 1// j
        case 107 => player.position.y -= 1// k
        case 108 => player.position.x += 1// l
        case _ =>
      }
    }
    NCurses.endwin()
  }
}

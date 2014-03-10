import model._
import ncurses4j.NCurses
object Main {
  val world = new World

  world.systems = gen.Stars.generateSystems(100, 100).groupBy(_.position).mapValues(_.head)

  val screen = NCurses.initscr()
  val maxx = NCurses.getmaxx(screen)
  val maxy = NCurses.getmaxy(screen)
  val player = Ship("Player 1", Glyph('@', true, 0), Position(10,10))
  world.addShip(player)
  var view = Viewport(0,0,60,20)

  def renderGui() {
    for(x <- 0 to 60) {
      NCurses.mvprintw(0,x,"-")
      NCurses.mvprintw(20,x,"-")
    }
    for (y <- 0 to 20) {
      NCurses.mvprintw(y,0,"|")
      NCurses.mvprintw(y,60,"|")
    }
    val (px,py,sx,sy) = (player.position.x,player.position.y,view.sx,view.sy)
    NCurses.mvprintw(maxy-1,0,s"Player: $px,$py Screen: $sx,$sy")
  }

  def renderScreen() {
    NCurses.erase()
    world.systems.values.filter(s => view.visible(s.position)).foreach(render)
    world.ships.values.filter(s => view.visible(s.position)).foreach(render)
    NCurses.mvprintw(maxy,0,"[q]uit, [hjkl] move")
    renderGui()
    NCurses.refresh()
  }

  def renderShips() {
  }

  def render(r: Renderable) {
    val p = view.translate(r.position)
    NCurses.mvprintw(p.y, p.x, r.glyph.chr.toString)
  }

  def main(args: Array[String]) {
    var stop = false
    while (!stop) {
      renderScreen()

      val i = NCurses.getch()
      i match {
        case 10 => stop = true // Enter
        case 27 => stop = true // Escape
        case 113 => stop = true // q
        case 104 => {
          player.position.x -= 1
          view.sx -= 1
        }// h
        case 106 => {
          player.position.y += 1
          view.sy += 1
        }// j
        case 107 => {
          player.position.y -= 1
          view.sy -= 1
        }// k
        case 108 => {
          player.position.x += 1
          view.sx += 1
        }// l
        case 72 => view.sx -= 1 // H
        case 74 => view.sy += 1 // H
        case 75 => view.sy -= 1 // H
        case 76 => view.sx += 1 // H
        case _ =>
      }
    }
    NCurses.endwin()
  }
}

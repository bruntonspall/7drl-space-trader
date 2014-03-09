/**
 * Created by michaelbruntonspall on 09/03/2014.
 */
import ncurses4j.NCurses
object Main {
  def main(args: Array[String]) {
    val screen = NCurses.initscr()
    val maxx = NCurses.getmaxx(screen)
    val maxy = NCurses.getmaxy(screen)
    val msg = "Testing curses display"
    val startx = maxx/2 - msg.length/2
    NCurses.erase()
    NCurses.mvprintw(maxy/2, startx, msg)
    NCurses.refresh()
    NCurses.getch()
    NCurses.endwin()
  }
}

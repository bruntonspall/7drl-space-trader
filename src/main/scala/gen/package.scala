import scala.util.Random

/** Useful random generation utility functions */
package object gen {
  implicit class RichRandomCompanion(companion: Random.type) {
    /** Given a sequence of As, returns a random A */
    def oneOf[A](as: Seq[A]): A = as(Random.nextInt(as.length))

    /** Given two functions that return an A, randomly evaluates one */
    def branch[A](left: => A, right: => A): A = {
      if (Random.nextBoolean()) {
        left
      } else {
        right
      }
    }

    /** Random number between low (inclusive) and high (exclusive) */
    def choose(low: Int, high: Int): Int = low + Random.nextInt(high - low)
  }
}

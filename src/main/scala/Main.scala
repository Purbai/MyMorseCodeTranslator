import scala.util.{Failure, Success, Try}
import scala.io.StdIn.readLine
import scala.util.chaining.scalaUtilChainingOps
import scala.util.control.Breaks.{break, breakable}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
object myTranslatorApp {
  val morseCode: Map[Char, String] = Map(
    'A' -> ".-", 'B' -> "-...", 'C' -> "-.-.", 'D' -> "-..",
    'E' -> ".", 'F' -> "..-.", 'G' -> "--.", 'H' -> "....",
    'I' -> "..", 'J' -> ".---", 'K' -> "-.-", 'L' -> ".-..",
    'M' -> "--", 'N' -> "-.", 'O' -> "---", 'P' -> ".--.",
    'Q' -> "--.-", 'R' -> ".-.", 'S' -> "...", 'T' -> "-",
    'U' -> "..-", 'V' -> "...-", 'W' -> ".--", 'X' -> "-..-",
    'Y' -> "-.--", 'Z' -> "--..", ' ' -> "/", '.' -> ".-.-.-",
    ',' -> "--..--", '?' -> "..--..", '!' -> "-.-.--", ':' -> "---...",
    ';' -> "-.-.-.", '\'' -> ".--.-.", '"' -> ".-..-..-"
  )

  def main(args: Array[String]): Unit = {
    replLoop()
  }

  private def replLoop(): Unit = {
    val input = scala.io.StdIn.readLine("Enter morse code or english char/string to translate (enter exit to quit) :")
    if (input.trim.equalsIgnoreCase("exit")) {
      println("Thank you for using the translator - Goodbye!")
    } else {
      val translatedStr = translate(input, morseCode)
      println(s"Your translation is: $translatedStr")
      replLoop()
    }
  }

  def translate(myString: String, code: Map[Char, String]): String = {
    // translate the input - split the string by each space encountered
    val isMorseCode = myString.matches(".*[A-Za-z].*")
    if (!isMorseCode) {
      myString
        .split("\\s+")
        .map(morseToEnglish(_, code))
        .mkString
    }
    else {
      myString
        .map(c => englishToMorse(c.toString, code))
        .mkString(" ") // add a space between each letter translation
    }
  }

  def englishToMorse(stringToTranslate: String, code: Map[Char, String]): String = {
    code.getOrElse(stringToTranslate.toUpperCase.charAt(0), stringToTranslate)
  }

  def morseToEnglish(stringToTranslate: String, code: Map[Char, String]): String = {
    code.find(_._2.equalsIgnoreCase(stringToTranslate)).map(_._1.toString).getOrElse(stringToTranslate)
  }

}

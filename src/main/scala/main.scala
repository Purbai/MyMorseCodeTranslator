import scala.util.{Failure, Success, Try}
import scala.io.StdIn.readLine
import scala.util.chaining.scalaUtilChainingOps
import scala.util.control.Breaks.{break, breakable}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
object myTranslatorApp {
  def main(args: Array[String]): Unit = {
    breakable {
      while (true) {
        // get input
        OutputHandler("Enter text/code to be translated or enter exit to quit : ")
        val myTransString = InputHandler().toUpperCase
        if (myTransString.toUpperCase == "EXIT") {
          OutputHandler("Thank you for using the translator - Good bye")
          break
        }
        OutputHandler("Your translated is: "+TransformStringForTranslator(myTransString))
      }
    }
  }

  def InputHandler(): String = {
        scala.io.StdIn.readLine()
  }

  def OutputHandler(transtring: String): Unit = {
    println(transtring)
  }

  def TransformStringForTranslator(myString: String): String = {
    // translate the input - split the string by each space encountered
    val isMorseCode = myString.matches(".*[A-Za-z].*")
    if (!isMorseCode) {
      myString
        .split("\\s+")
        .map(c => Translator(c, isMorseCode))
        .mkString
    }
    else {
      myString
        .map(c => Translator(c.toString, isMorseCode))
        .mkString(" ") // add a space between each letter translation
    }
  }

  def Translator(stringToTranslate: String, isMorseCode: Boolean): String = {
    val codeMap = MorseCode()
    if (isMorseCode) {
      // there is only one char since we are looping round each letter in the string
      codeMap.getOrElse(stringToTranslate.toUpperCase.charAt(0), stringToTranslate)
    } else {
      codeMap.find(_._2.equalsIgnoreCase(stringToTranslate)).map(_._1.toString).getOrElse(stringToTranslate)
    }
  }

  def MorseCode(): Map[Char, String] = {
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
    morseCode
  }
}

import scala.util.{Try, Success, Failure}
import scala.io.StdIn.readLine
import scala.util.chaining.scalaUtilChainingOps

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
object myTranslatorApp {
  def main(args: Array[String]): Unit = {
    // get input
    val myTransString = InputHandler().toUpperCase
    OutputHandler(TransformStringForTranslator(myTransString))

  }

  def InputHandler(): String = {
    println("Enter text to be translated - please enter exit at the end input:")
    scala.io.StdIn.readLine()
  }

  def OutputHandler(transtring: String): Unit = {
    println("Your string is translated to: " + transtring)
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
      'Y' -> "-.--", 'Z' -> "--..", ' ' -> "/",'.' -> ".-.-.-",
      ',' -> "--..--", '?' -> "..--..", '!' -> "-.-.--", ':' -> "---...",
      ';' -> "-.-.-.", '\'' -> ".--.-.", '"' -> ".-..-..-"
    )
    morseCode
  }
}

import scala.util.{Try, Success, Failure}
import scala.io.StdIn.readLine
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
object myTranslatorApp {
  def main(args: Array[String]): Unit = {

    val myTransString = InputHandler()
    //    val myTransString = "my translated string"
    //    val myString = "ABC"

    val myNewString = myTransString.split("\\s+").map(Translator).mkString(" ")

    OutputHandler(myNewString)
    //println("my new string :"+myNewString)


  }

  def InputHandler(): String = {
    println("Enter text to translate into - please enter exit at the end input:")
    scala.io.StdIn.readLine()
    //println("The following string will be translated: " + stringToTranslate)

  }

  def OutputHandler(transtring: String): Unit = {
    println("Your string is translated to: "+ transtring)
  }

  def Translator(stringToTranslate: String): String = {

    //TestCode().getOrElse(stringToTranslate.toUpper, "?")
    val codeMap = MorseCode()
    if (stringToTranslate.toString.matches("[A-Za-z]")) {
      codeMap.getOrElse(stringToTranslate.toUpperCase.charAt(0), stringToTranslate)
    } else {

      codeMap.find(_._2.equalsIgnoreCase(stringToTranslate)).map(_._1.toString).getOrElse(" ")
    }



  }


  def MorseCode(): Map[Char,String] = {
    val morseCode: Map[Char, String] = Map(
      'A' -> ".-", 'B' -> "-...", 'C' -> "-.-.", 'D' -> "-..",
      'E' -> ".", 'F' -> "..-.", 'G' -> "--.", 'H' -> "....",
      'I' -> "..", 'J' -> ".---", 'K' -> "-.-", 'L' -> ".-..",
      'M' -> "--", 'N' -> "-.", 'O' -> "---", 'P' -> ".--.",
      'Q' -> "--.-", 'R' -> ".-.", 'S' -> "...", 'T' -> "-",
      'U' -> "..-", 'V' -> "...-", 'W' -> ".--", 'X' -> "-..-",
      'Y' -> "-.--", 'Z' -> "--..", ' ' -> "/"
    )
    morseCode
  }

}



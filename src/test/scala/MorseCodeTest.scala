import org.scalatest.funsuite.AnyFunSuite
import myTranslatorApp.*
import org.scalatest.TryValues

class morseCodeTest extends AnyFunSuite with TryValues:
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

  // Test: input lower case letter [a-z] should translation to a morse code
  test("valid lower case letter should return valid morse code") {
    val results = englishToMorse("c", morseCode)
    assert(results == "-.-.")
  }
  // Test: input upper case letter [A-Z] should translation to a morse code
  test("valid upper case letter should return valid morse code") {
    val results = englishToMorse("C", morseCode )
    assert(results == "-.-.")
  }
  test("valid single morse code should return valid upper case letter") {
    val results = morseToEnglish("-.-.", morseCode)
    assert(results == "C")
  }
  // Test: input char not in [A-Z] or punctuation, and it should return the same char without changing
  test("valid non valid letter should return the same letter back") {
    val results = morseToEnglish("=", morseCode)
    assert(results == "=")
  }
  // Test: input morse code words, it should translate to a letter/words [A-Z] & punctuations
  test("valid morse code should return valid upper case letter") {
    val results = translate("-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.--", morseCode)
    assert(results == "CATS ARE COOL!")
  }

  // Test: input multiple words and punctuation and it should return valid morse code
  test("valid multiple letters should return valid morse code") {
    val results = translate("CATS are cool! ", morseCode)
    assert(results == "-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.-- /")
  }
  // Test: input multiple words and multiple punctuations,and it should return valid morse code
  test("valid multiple letters with some punctuations should return valid morse code") {
    val results = translate("Cats aren't big animals!", morseCode)
    assert(results == "-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--")
  }

  // Test: input morse code and it should return valid words & punctuations
  test("valid morse code containing words & punctuations should return valid letters & punctuations") {
    val results = translate("-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--", morseCode)
    assert(results == "CATS AREN'T BIG ANIMALS!")
  }
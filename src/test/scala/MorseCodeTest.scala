import org.scalatest.funsuite.AnyFunSuite
import myTranslatorApp.*
import org.scalatest.TryValues

class MorseCodeTest extends AnyFunSuite with TryValues:

  // Test: input letter [a-z] should translation to a morse code
  test("valid lower case letter should return valid morse code") {
    val results = Translator("c", true)
    assert(results == "-.-.")
  }
  // Test: input letter [A-Z] should translation to a morse code
  test("valid upper case letter should return valid morse code") {
    val results = Translator("C", true)
    assert(results == "-.-.")
  }
  test("valid single morse code should return valid upper case letter") {
    val results = Translator("-.-.", false)
    assert(results == "C")
  }
  // Test: input letter not in [A-Z] should return the same letter without changing
  test("valid non valid letter should return the same letter back") {
    val results = Translator("=", true)
    assert(results == "=")
  }
  // Test: input morse code [] should translation to a Letter [A-Z]
  test("valid morse code should return valid upper case letter") {
    val results = TransformStringForTranslator("-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.--")
    assert(results == "CATS ARE COOL!")
  }

  // Test: input morse code [] should translation to a Letter [A-Z]
  test("valid multiple letters should return valid morse code") {
    val results = TransformStringForTranslator("CATS are cool! ")
    assert(results == "-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.-- /")
  }
  // Test: input morse code [] should translation to a Letter [A-Z]
  test("valid multiple letters with some punctuations should return valid morse code") {
    val results = TransformStringForTranslator("Cats aren't big animals!")
    assert(results == "-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--")
  }

  // Test: input morse code [] with letters / punctuation should translation to a Letter [A-Z] & punctuation
  test("valid morse code containing punctuations should return valid letters and punctuations") {
    val results = TransformStringForTranslator("-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--")
    assert(results == "CATS AREN'T BIG ANIMALS!")
  }
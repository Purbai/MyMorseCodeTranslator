import org.scalatest.funsuite.AnyFunSuite
import myTranslatorApp.*
import org.scalatest.TryValues

class MorseCodeTest extends AnyFunSuite with TryValues:

  // Test: input lower case letter [a-z] should translation to a morse code
  test("valid lower case letter should return valid morse code") {
    val results = Translator("c", true)
    assert(results == "-.-.")
  }
  // Test: input upper case letter [A-Z] should translation to a morse code
  test("valid upper case letter should return valid morse code") {
    val results = Translator("C", true)
    assert(results == "-.-.")
  }
  test("valid single morse code should return valid upper case letter") {
    val results = Translator("-.-.", false)
    assert(results == "C")
  }
  // Test: input char not in [A-Z] or punctuation, and it should return the same char without changing
  test("valid non valid letter should return the same letter back") {
    val results = Translator("=", true)
    assert(results == "=")
  }
  // Test: input morse code words, it should translate to a letter/words [A-Z] & punctuations
  test("valid morse code should return valid upper case letter") {
    val results = TransformStringForTranslator("-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.--")
    assert(results == "CATS ARE COOL!")
  }

  // Test: input multiple words and punctuation and it should return valid morse code
  test("valid multiple letters should return valid morse code") {
    val results = TransformStringForTranslator("CATS are cool! ")
    assert(results == "-.-. .- - ... / .- .-. . / -.-. --- --- .-.. -.-.-- /")
  }
  // Test: input multiple words and multiple punctuations,and it should return valid morse code
  test("valid multiple letters with some punctuations should return valid morse code") {
    val results = TransformStringForTranslator("Cats aren't big animals!")
    assert(results == "-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--")
  }

  // Test: input morse code and it should return valid words & punctuations
  test("valid morse code containing words & punctuations should return valid letters & punctuations") {
    val results = TransformStringForTranslator("-.-. .- - ... / .- .-. . -. .--.-. - / -... .. --. / .- -. .. -- .- .-.. ... -.-.--")
    assert(results == "CATS AREN'T BIG ANIMALS!")
  }
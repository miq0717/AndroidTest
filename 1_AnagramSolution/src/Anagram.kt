fun main(args: Array<String>) {

    println("Enter first String")
    val firstString = readLine()!!
    println("Enter second String")
    val secondString = readLine()!!

    if (anagramCheck(firstString, secondString)) {
        println("This is an anagram")
    } else {
        println("This is not an anagram")
    }
}

//checks whether the two strings are anagram
fun anagramCheck(firstString: String, secondString: String): Boolean {
    val secondStringFrequencyMap = frequencyMap(secondString)
    var isAnagram: Boolean
    for (i in 0..(firstString.length - secondString.length)) {
        isAnagram = anagram(firstString.substring(i, i + secondString.length), secondStringFrequencyMap)
        if (isAnagram)
            return true
    }
    return false
}

//They're anagrams if both produce the same 'frequency map'
fun anagram(substring: String, secondStringFrequencyMap: Map<Char, Int>): Boolean {
    return frequencyMap(substring) == secondStringFrequencyMap
}

fun frequencyMap(string: String): Map<Char, Int> {
    val map = HashMap<Char, Int>()
    for (c in string.toLowerCase().toCharArray()) {
        val frequency = map[c]
        map[c] = if (frequency == null) 1 else frequency + 1
    }
    return map
}


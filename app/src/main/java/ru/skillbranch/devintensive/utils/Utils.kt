package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        // todo: fix
        if(fullName?.trim().isNullOrEmpty()) {
            return null to null
        }

        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun replaceLetter(letter: Char): String {
        return when(letter) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е', 'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и', 'й'-> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ', 'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            else -> ""
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var transFirstName = ""
        var transLastName = ""
        var (firstName, lastName) = parseFullName(payload)
        firstName = firstName?.toLowerCase()
        lastName = lastName?.toLowerCase()
        //println("first = ${firstName} last = ${lastName}")
        for(i in firstName?.indices!!) {
            var ru = firstName[i]
            transFirstName += replaceLetter(ru)
        }
        transFirstName = transFirstName.capitalize()
        for(i in lastName?.indices!!) {
            var ru = lastName[i]
            transLastName += replaceLetter(ru)
        }
        transLastName = transLastName.capitalize()

        //println("first = ${transFirstName} last = ${transLastName}")

        return "${transFirstName}${divider}${transLastName}"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "${firstName?.get(0)?.toUpperCase()}${lastName?.get(0)?.toUpperCase()}"
    }
}
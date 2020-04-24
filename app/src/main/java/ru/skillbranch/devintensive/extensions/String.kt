package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val str: String = this.subSequence(0, count).toString().trim()

    return "${if(str.length <= count) "${str}" else "${str}..."}"
}

fun String.stripHtml(): String {
    val regex = """<\w+(\s+\w+(\-\w+)*\s*\=\s*[\"\']\w+(\s+\w+)*[\"\'])*>|</\w+>""".toRegex()
    var str = regex.replace(this, "")
    str = str.replace("&", "")
    val regex2 = """\s{2,}|\p{C}|\&""".toRegex()
    return regex2.replace(str, " ")
}
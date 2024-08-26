class ID {
    fun getListId(length: Int): List<String> {
        var idLastChar = 0
        val idArray = Array(length.toString().length) { 0 }
        val idNull = idArray.fold("") { a, b -> "$a$b" }.toString()
        var id = ""
        val idList = mutableListOf<String>()
        for (i in 0..<length) {
            idLastChar++
            id = idNull.substring(0, idNull.length - idLastChar.toString().length) + idLastChar
            idList.add(id)
        }
        return idList
    }
}
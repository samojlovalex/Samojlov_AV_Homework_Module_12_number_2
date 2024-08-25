class Password {

    private fun createPassword(length: Int): String {
        //Функция генерации пароля из чисел и букв заданной длины
        val charSet = ('0'..'9') + ('a'..'z')
        val passwordInner = (0..length).map { charSet.random() }
        val passwordIntermediate = mutableListOf<String>()
        for (i in passwordInner.indices) {
            if (i % 2 == 0) {
                when (passwordInner[i]) {
                    in ('a'..'z') -> passwordIntermediate.add(passwordInner[i].uppercase())
                    else -> passwordIntermediate.add(passwordInner[i].toString())
                }
            } else passwordIntermediate.add(passwordInner[i].toString())
        }
        val passwordOut = passwordIntermediate.fold("") { a, b -> "$a$b" }.toString()
        return passwordOut
    }

    fun getListOfPassword(input: String, length: Int, lengthPassword: Int): List<String> {
        //Функция формирования списка паролей с заданным первым символом, заданной длиной
        // списка и длиной паролей
        fun passwordFilter(): String {
            var passwordOut: String
            do {
                passwordOut = createPassword(lengthPassword)
            } while (input.uppercase() != passwordOut.first().toString())
            return passwordOut
        }

        val passwordList = mutableListOf<String>()
        for (i in 0..<length) {
            passwordList.add(i, passwordFilter())
        }
        return passwordList.toList()
    }
}
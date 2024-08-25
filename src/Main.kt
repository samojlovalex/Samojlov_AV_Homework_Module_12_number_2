@file:OptIn(DelicateCoroutinesApi::class)

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() {
    println("Программа создания паролей и присвоения их пользователям по идентификационному номеру\n")

    val passwordLength = 6
    val idList = mutableListOf<String>()
    val passwordList = mutableListOf<String>()
    val idPasswordMap = mutableMapOf<String, String>()

    println("Укажите количество пользователей, для которым необходимо присвоить пароль и ID")
    val length = readln()
    println("Укажите символ, с которого должен начинаться сгенерированный пароль пользователей")
    val input = readln()

    val time = measureTimeMillis {
        withContext(newSingleThreadContext("id_password_context")) {
            launch {
                getIdFlow(length.toInt()).collect { i ->
                    idList.add(i)
                }
            }
            launch {
                getPasswordFlow(input, length.toInt(), passwordLength).collect { i ->
                    passwordList.add(i)
                }
            }
        }
        for (i in 0..<length.toInt()) {
            idPasswordMap[idList[i]] = passwordList[i]
        }
    }
    idPasswordMap.forEach { (key, value) -> println("ID: $key PASSWORD: $value") }

    println("затраченное время на создание $length паролей к такому же количеству id: $time мс")
}

val id = ID()
val password = Password()

fun getIdFlow(length: Int) = id.getListId(length).asFlow()

fun getPasswordFlow(input: String, length: Int, lengthPassword: Int) =
    password.getListOfPassword(input, length, lengthPassword).asFlow()
package oop

open class Employee(name: String, lastName: String)

data class Manager(val name: String, val lastName: String, val age: Int) : Employee(name, lastName)

data class Company(val name: String, val phone: String, val addresses: List<Address>, val managers: List<Manager>)

data class Address(val street: String = "", val city: String = "", val country: String = "")

fun main() {
    val addresses = ArrayList<Address>().apply {
        add(Address("Valle 45", "CDMX", "México"))
        add(Address("Guanajuato 45", "Cali", "Colombia"))
    }

    val managers = ArrayList<Manager>().apply {
        add(Manager("Juan", "Torres", 45))
        add(Manager("Pedro", "Gonzales", 35))
    }

    val company = Company("DevHack", "234565432", addresses, managers)

    println(company)
}
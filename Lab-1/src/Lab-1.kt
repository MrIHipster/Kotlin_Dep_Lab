abstract class Employee (
    var firstname: String,
    var secondname: String,
    var exp: Int,
    var base_sal : Double
){
    open fun giveSalary(): Double {
        if (exp > 2) return base_sal + 200.0
        if (exp > 5) return base_sal * 1.2 + 500.0
        else return base_sal
    }
}

class Manager(
    firstname: String,
    secondname: String,
    exp: Int,
    base_sal: Double,
    var Team: MutableList<Employee> = mutableListOf()
): Employee(firstname,secondname, exp, base_sal) {
    override fun giveSalary(): Double {
        var TeamCount = Team.filterIsInstance<Developer>().count() + Team.filterIsInstance<Designer>().count()
        if (TeamCount > 5) return super.giveSalary() + 200.0
        if (TeamCount > 10) return super.giveSalary() + 300.0
        if (TeamCount/2 < Team.filterIsInstance<Designer>().count()) return super.giveSalary() * 1.1
        else return super.giveSalary()
    }
}

class Designer (
    firstname: String,
    secondname: String,
    exp: Int,
    base_sal : Double,
    var coef: Double
) : Employee(firstname,secondname, exp, base_sal){
    override fun giveSalary() : Double{
        return super.giveSalary() * coef
    }
}

class Developer (
    firstname: String,
    secondname: String,
    exp: Int,
    base_sal: Double
) : Employee(firstname,secondname,exp,base_sal)

class Department(
    var managers: MutableList<Manager> = mutableListOf()
){
    fun giveSallaryAll(){
        managers.forEach{manager -> manager.giveSalary()
        manager.Team.forEach{employee -> employee.giveSalary()}}
    }
}

fun main() {
    //----------------- 1st-Team -----------------------//
    val dev1 = Developer("Saral", "Rahija", 2, 20.0)
    val dev2 = Developer("Ivan", "Petrov", 35,40.0)
    val dev3 = Developer("Nikita", "Kolesnikov", 20, 30.0)
    val dev4 = Developer("Artem", "Jirkov", 5, 25.0)
    val des1 = Designer("Mikel", "Andrada", 3, 120.0, 0.7)
    val manager1 = Manager("Andrii", "Slyvka", 5, 300.0)
    manager1.Team.add(dev1)
    manager1.Team.add(dev2)
    manager1.Team.add(dev3)
    manager1.Team.add(dev4)
    manager1.Team.add(des1)

    //----------------- 2nd-Team -----------------------//
    val dev5 = Developer("Shahyar","Marwat", 4,22.5)
    val dev6 = Developer("Haldun","Lakhani", 45, 55.0)
    val dev7 = Developer("Ain", "Uz", 1, 10.0)
    val des2 = Designer("Aki", "Karlal", 3, 120.0, 0.45)
    val des3 = Designer("Husain", "Karlal", 3, 120.0, 0.6)
    val des4 = Designer("Masoud", "Karlal", 3, 120.0, 0.35)
    val manager2 = Manager("Wahab", "Sultan", 5, 350.0)
    manager1.Team.add(dev5)
    manager1.Team.add(dev6)
    manager1.Team.add(dev7)
    manager2.Team.add(des4)
    manager2.Team.add(des3)
    manager2.Team.add(des2)

    //----------------- giveSallary -------------------------//
    val department = Department()
    department.giveSallaryAll()
    println(dev1.firstname + " " + dev1.secondname + " got salary: " + dev1.giveSalary())
    println(dev2.firstname + " " + dev2.secondname + " got salary: " + dev2.giveSalary())
    println(des1.firstname + " " + des1.secondname + " got salary: " + des1.giveSalary())
    println(des4.firstname + " " + des4.secondname + " got salary: " + des4.giveSalary())
    println(manager1.firstname + " " + manager1.secondname + " got salary: " + manager1.giveSalary())
    println(manager2.firstname + " " + manager2.secondname + " got salary: " + manager2.giveSalary())
}
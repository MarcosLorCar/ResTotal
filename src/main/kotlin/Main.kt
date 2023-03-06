fun main() {
    var exit = false
    var new: Boolean
    var mainBranch: Branch
    var currBranch: Branch

    while (!exit) {
        new = false
        mainBranch = Branch(null)
        currBranch = mainBranch
        while (!exit && !new) {
            println("""
            1-Value
            2-Branch
            3-${
                if (currBranch.parent==null) "Calculate total" else {
                    if (currBranch.parent!!.branches.indexOf(currBranch)+1 == currBranch.parent!!.branches.size)
                        "Finish element"
                    else
                        "Next branch"
                }
            }
            4-Exit
            """.trimIndent())
            when (readln().toIntOrNull()?:-1) {
                4 -> exit = true
                1 -> {
                    print("Value: ")
                    val value = readln().toDoubleOrNull()
                    value?.run {
                        currBranch.add(this)
                    } ?: {
                        println("Invalid value")
                    }
                }
                2 -> {
                    print("Num of branches: ")
                    val branchNum = readln().toIntOrNull()
                    branchNum?.run {
                        currBranch.add(branchNum)
                            .also { currBranch = it }
                    } ?: {
                        print("Invalid number of branches")
                    }
                }
                3 -> if (currBranch.parent!=null) {
                    currBranch.parent?.run {
                        currBranch = if (
                            this.branches.indexOf(currBranch)==this.branches.size-1
                        ) {
                            currBranch.parent!!.parent
                        } else {
                            this.branches[
                                this.branches.indexOf(currBranch)+1
                            ]
                        }
                    } ?: println("Already in main branch")
                }
                else {
                    println("Total resistance: ${mainBranch.value} Ω")
                    println("-----------\n" +
                            "New circuit\n" +
                            "-----------")
                    new = true
                }
                else -> println("Invalid option")
            }
        }
    }
}
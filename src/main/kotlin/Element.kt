class Element(val parent: Branch, value: Double = 0.0, branchNum: Int = 0) {
    var branches: MutableList<Branch> = MutableList(branchNum) { Branch(this) }
    var value: Double = value
        get() = if (field>0) field else calcValue()

    private fun calcValue(): Double {
        val totals = mutableListOf<Double>()
        branches.forEach { totals.add(it.value) }
        var total = -1.0
        totals.forEach {
            total = if (total<0) it else ((it*total)/(it+total))
        }
        return total
    }
}
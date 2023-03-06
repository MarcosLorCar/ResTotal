
class Branch(val parent: Element?) {

    fun add(value: Double) {
        Element(this,value)
            .run {
                elements.add(this)
            }
    }

    fun add(branchNum: Int): Branch {
        val newElement = Element(this, branchNum = branchNum)
            .also {
                elements.add(it)
            }
        return newElement.branches[0]
    }

    private val elements = mutableListOf<Element>()
    val value: Double
        get() {
            var total = 0.0
            elements.forEach { total+=it.value }
            return total
        }
}
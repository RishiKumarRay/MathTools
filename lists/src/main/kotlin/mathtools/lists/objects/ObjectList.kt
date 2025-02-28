package mathtools.lists.objects

/** Sorting related Group operations
 * @author DK96-OS : 2022 */
object ObjectList {

    /** Remove and return the items in the list that match the selector.
      *  @param list A mutable list which will be modified if any items match
      *  @param presorted If the list is sorted according to selector, ie. the matches are all in one consecutive index range in the list.
      *  @param selector The condition to test for determining a match
      *  @return A new MutableList containing matching items or null if no matches were found */
    fun <T> extractFrom(
        list: MutableList<T>,
        presorted: Boolean = false,
        selector: (T) -> Boolean
    ) : MutableList<T>? {
        var i = list.indexOfFirst { selector(it) }
        if (i < 0) return null
        val groupList: MutableList<T> = arrayListOf(list.removeAt(i))
        if (presorted) 
            while (i < list.size && selector(list[i]))
                groupList.add(list.removeAt(i))
        else
            while (i < list.size) {
                if (selector(list[i])) groupList.add(list.removeAt(i))
                else i++
            }
        return groupList
    }

}
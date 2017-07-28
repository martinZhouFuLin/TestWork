package zltd.com.testwork.bean

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by q on 2017/6/27.
 */


@DatabaseTable
class Person {
    @DatabaseField(id = true)
    var name: String? = null
    @DatabaseField
    var age: String? = null
    @DatabaseField
    var weight: String? = null
}

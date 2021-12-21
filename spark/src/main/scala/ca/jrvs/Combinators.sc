import scala.io.Source

/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * head : returns the first element of this collection.
 * last : returns the last element in this collection.
 * size : returns how many elements this collection has
 * take : creates a collection by keeping the first i elements of this collection and discarding the rest
 */
val ls = List.range(0,10)

val ls_head = ls.head
val ls_last = ls.last
val ls_take = ls.take(5)
val ls_tails = ls.takeRight(5)




/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * flatten : creates a collection by using the elements of this collection’s subcollections
 *
 * flatMap : creates a collection by applying the function f to each element
 *            and using the resulting subelements as elements of the new collection
 */
val numList = List(List(1,2), List(3));

val numList_flatten = numList.flatten.map(x => x)
val numList_flattenMap = numList.flatten.map(x => x * 2)

val numList_flat = numList.flatMap(x => x.map(x => x))
val numList_flatMap = numList.flatMap(x => x.map(x => x * 2))




/**
 * Sum List.range(1,11) in three ways
 *
 * sum : computes the sum of the elements of this collection
 * reduce : applies the binary operator op to pairs of elements in this collection until the final result is calculated
 * foldLeft : applies, going from left to right, the binary operator op to each element and the previous op result.
 *            The first time op is applied it’s fed with the initial value z
 *
 */
//write you solution here
val sumList = List.range(1,11)

val sumList_sum = sumList.sum
val sumList_reduce = sumList.reduce((x, y) => x + y)
val sumList_foldLeft = sumList.foldLeft(0)((x, y) => x + y)




/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 *
 *  .get : returns the contained element if it exists
 *
 *  getOrElse : returns the value associated to the key k in this Map
 *    If this Map doesn’t contain the key k then this function returns the result of the computation v
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
countryMap.get("Amy")
countryMap.get("edward")
countryMap.getOrElse("edward", "n/a")


/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")

val names_tuples = names.map(x => (x, countryMap.getOrElse(x, "n/a")))


/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 *
 */
names_tuples.map(v=> v._2).groupBy(identity).view.mapValues(_.size).toMap




/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 *
 * zipWithIndex + case {} : zipwithindex always starts 0, case for +1
 * zip from 1 : starts stream index values from 1
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")

names2.zipWithIndex.map { case (k, v) => (v + 1, k) }
names2.view.zip(Stream from 1).map(_.swap).toList

val names2map = names2.view.zip(Stream from 1).map(_.swap).toMap
names2map.toList




/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 *
 */
val fileName = "C:\\Users\\Gino\\Documents\\David\\spark\\src\\main\\resources\\employees.csv"
val bs =  Source.fromFile(fileName);
val lines = bs.getLines.toList
bs.close()

//Convert lines to a list of employees
//e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
case class Employee(id:Int, name:String, city:String, age:Int)
val mapToEmp = (line:String) => {
  val tokens = line.split(",")
  Employee(tokens(0).trim.toInt, tokens(1).trim, tokens(2).trim, tokens(3).trim.toInt )
}
val noHeader = lines.drop(1)
val employees = noHeader.map(mapToEmp)

/**
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
val upperCity = employees.map(emp => Employee(emp.id,emp.name,emp.city.toUpperCase(),emp.age))

/**
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
upperCity.filter(_.city.equals("TORONTO"))


/**
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
val cityNum = upperCity
  .groupBy(_.city)
  .map({case(city, ls) => (city, ls.length)})

/**
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
upperCity.groupBy(emp => (emp.city, emp.age))
  .map({case(city, ls) => (city, ls.length)})
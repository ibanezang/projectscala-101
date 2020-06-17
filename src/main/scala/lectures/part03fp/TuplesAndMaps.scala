package lectures.part03fp

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = (2, "Hello scala") // => Tuple[Int, String] = (Int, String)
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodby java"))
  println(aTuple.swap)

  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 123), "Anang" → 123).withDefaultValue(-1)
  println(phoneBook)
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  // add new pairing
  val newPairing = "Mary" → 90909090
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functional on maps
  // map flatmap filter
  println(newPhoneBook.map(pair ⇒ pair._1.toLowerCase → pair._2))
  println(newPhoneBook.filter(pair ⇒ pair._1.startsWith("J")))
  println(newPhoneBook.view.mapValues(n ⇒ s"+66-$n").toMap)

  // conversion to other collections
  println(newPhoneBook.toList)
  println(List(("Anang", 848484)).toMap)
  val listOfNames = List(
    "Anang",
    "Jessica",
    "Mika",
    "Emily",
    "Murningsih",
    "Puput",
    "Martina",
    "Angela",
    "Jim"
  )
  println(listOfNames.groupBy(name ⇒ name.charAt(0)))

  val newPhoneBook2 = newPhoneBook + ("ANANG" → 9999)
  println(newPhoneBook2.map(pair ⇒ pair._1.toLowerCase → pair._2))

  def add(network: Map[String, Set[String]],
          person: String): Map[String, Set[String]] = {
    network + (person → Set())
  }

  def friend(network: Map[String, Set[String]],
             personA: String,
             personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA → (friendsA + personB)) + (personB → (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]],
               personA: String,
               personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA → (friendsA - personB)) + (personB → (friendsB - personA))
  }

  def remove(network: Map[String, Set[String]],
             person: String): Map[String, Set[String]] = {
    def removeAux(
      friends: Set[String],
      networkAcc: Map[String, Set[String]]
    ): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  var network = add(add(empty, "Bob"), "Mary")
  network = friend(network, "Bob", "Mary")
  println(network)
  println(unfriend(network, "Mary", "Bob"))
  println(remove(network, "Mary"))

  val people =
    add(
      add(add(add(add(add(empty, "Bob"), "Mary"), "Jim"), "Emily"), "Jessica"),
      "Anang"
    )
  val jimBob = friend(people, "Jim", "Bob")
  val jessicaEmily = friend(jimBob, "Jessica", "Emily")
  val jessicaBob = friend(jessicaEmily, "Bob", "Jessica")
  val testNet = friend(jessicaBob, "Bob", "Mary")
  println(testNet)

  def nFriend(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriend(testNet, "Bob"))
  println(nFriend(testNet, "Mary"))
  println(nFriend(testNet, "Mika"))

  def mostFriend(network: Map[String, Set[String]]): (String, Set[String]) =
    network.maxBy(pair ⇒ pair._2.size)

  println(mostFriend(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.view.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]],
                       a: String,
                       b: String): Boolean = {
    def bfs(target: String,
            consideredPeople: Set[String],
            discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person))
          bfs(target, consideredPeople, discoveredPeople.tail)
        else
          bfs(
            target,
            consideredPeople + person,
            discoveredPeople.tail ++ network(person)
          )
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Emily", "Bob"))
  println(socialConnection(testNet, "Mary", "Jessica"))
  println(socialConnection(testNet, "Anang", "Jessica"))
  println(socialConnection(network, "Mary", "Bob"))
}

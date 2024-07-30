def getStudentInfo: (String, Int, Int, Double, Char) = {
    val (name, marks, totalMarks) = getStudentInfoWithRetry

    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = percentage match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }

    (name, marks, totalMarks, percentage, grade)
}

def printStudentRecord(record: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = record
    println(s"Student Name: $name")
    println(s"Marks Obtained: $marks")
    println(s"Total Possible Marks: $totalMarks")
    println(s"Percentage: $percentage")
    println(s"Grade: $grade")
}

def validateInput(name: String, marks: Int, totalMarks: Int): (Boolean, Option[String]) = {
    if (name.isEmpty) {
      (false, Some("Name cannot be empty."))
    } else if (marks < 0 || marks > totalMarks) {
      (false, Some("Marks must be a positive integer not exceeding the total possible marks."))
    } else if (totalMarks <= 0) {
      (false, Some("Total possible marks must be a positive integer."))
    } else {
      (true, None)
    }
}

def getStudentInfoWithRetry: (String, Int, Int) = {
    var valid = false
    var name = ""
    var marks = 0
    var totalMarks = 0

    while (!valid) {
      println("Enter student name: ")
      name = scala.io.StdIn.readLine()

      println("Enter marks obtained: ")
      marks = scala.io.StdIn.readInt()

      println("Enter total possible marks: ")
      totalMarks = scala.io.StdIn.readInt()

      val (isValid, errorMessage) = validateInput(name, marks, totalMarks)
      if (isValid) {
        valid = true
      } else {
        println(s"Invalid input: ${errorMessage.getOrElse("")}")
      }
    }

    (name, marks, totalMarks)
}

def main(args: Array[String]): Unit = {
    val studentInfo = getStudentInfo
    printStudentRecord(studentInfo)
}

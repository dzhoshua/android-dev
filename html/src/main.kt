import java.io.File

fun main() {
    data class Message(val address: String?, val topic: String?, val message:String?, val date:String?) {
        fun rowToHTML(row: String?, name: String): String{
            if (row == null) {
                return ""
            }
            return  row.let { "<tr>" +
                    "<td style=\"border: 1px solid black; background-color: #f77c9f; padding: 5px;\">" +
                    "<b>$name</b></td>" +
                    "<td style=\"border: 1px solid black; background-color: #fddde6; padding: 5px;\">$it</td></tr>" }

        }

        fun toHTML(): String {
            var template = "<table style=\"font-family: Arial; " +
                    "font-size: 24px; " +
                    "color: #9e0934; " +
                    "padding: 10px; " +
                    "border: 1px solid black;\">"
            template += rowToHTML(address, "address")
            template += rowToHTML(topic, "topic")
            template += rowToHTML(message, "message")
            template += rowToHTML(date, "date")
            template += "</table>"
            return template
        }
    }

    val m = Message("askbill@microsoft.com", "test","hiiiiiiiiiiiiiiiiii", null)
    println(m.toHTML())
    val text = m.toHTML()
    File("file.html").writeText(text)
}

package shubham1556

/**
 * Created by shubham on 1/28/17.
 */
import net.liftweb.json._

object SimpleJSONParser {

  implicit val formats = DefaultFormats
  val jsonDoc =
    """{
      |  "appSecret": "e83f8ee542",
      |  "start_date": "01/12/2016",
      |  "end_date": "31/01/2017",
      |  "cards": [
      |    {
      |      "deck_id": "123",
      |      "card_id": "tota_install",
      |      "granuality": "cummulative",
      |      "metric": "count",
      |      "filters": [
      |        {
      |          "name": "Location",
      |          "value": [
      |            "Bombay",
      |            "Amsterdam"
      |          ]
      |        },
      |        {
      |          "name": "Manufacturer",
      |          "value": [
      |            "Micromax",
      |            "Google"
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "deck_id": "123",
      |      "card_id": "new_users",
      |      "granuality": "hourly",
      |      "metric": "count",
      |      "filters": [
      |        {
      |          "name": "Location",
      |          "value": [
      |            "Agra",
      |            "London"
      |          ]
      |        },
      |        {
      |          "name": "Manufacturer",
      |          "value": [
      |            "Samsung",
      |            "Nokia"
      |          ]
      |        }
      |      ]
      |    }
      |  ]
      |}
    """.stripMargin

  def main(args: Array[String]) {

    val json: JValue = parse(jsonDoc)

    val appSecret_json: String = (json \ "appSecret").extract[String]
    val start_d: String = (json \ "start_date").extract[String]
    val end_d: String = (json \ "end_date").extract[String]
    val cards_json: List[JsonAST.JValue] = (json \ "cards").children

    println(s"The json has AppSecret - $appSecret_json, Start to End Date - $start_d to $end_d, And cards are: ")

    for (c <- cards_json) {
      val card_map = c.asInstanceOf[JObject].values

      val deckId: String = card_map.get("deck_id").get.asInstanceOf[String]
      val cardId: String = card_map.get("card_id").get.asInstanceOf[String]
      val metric: String = card_map.get("metric").get.asInstanceOf[String]
      val gran: String = card_map.get("granuality").get.asInstanceOf[String]

      val filters = card_map.get("filters")

      val k = filters.get.asInstanceOf[List[Map[String, List[String]]]]
      val fm = k.map {
        x => {
          val key: String = x.get("name").get.asInstanceOf[String]
          val v: List[String] = x.get("value").get
          (key -> v)
        }
      }.toMap

      println("******")
      println(s"One card has Deck ID - $deckId, Card ID - $cardId, Granuality - $gran.")
      println(s"Filters on this card are: $fm")
    }
  }

}

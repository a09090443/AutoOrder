package com.zipe

import org.jsoup.Connection
import org.jsoup.Jsoup

fun main() {
//
//    val pchomeProductionUrl = resource.getString("pchome.production.prefix.url")
//
    val conn = Jsoup.connect("https://ecssl.pchome.com.tw/sys/cflow/fsindex/BigCar/BIGCAR/ItemList").apply {
        this.header("Accept", "text/html, application/xhtml+xml, */*")
        this.header("Content-Type", "application/x-www-form-urlencoded")
        this.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))")
//        it.header("Cookie", "text/html, application/xhtml+xml, */*")
    }
//
//    val doc = conn.get()

    val jsonContent = """
        {"partner":"pchome24h","source":"web","insider_id":"16311919680686bc970e8b1.25423a6b","events":[{"event_name":"item_added_to_cart","timestamp":1633166397,"event_params":{"pid":"DEBQB3-A900AQUSI","na":"Naturehike 四支撐強化型戶外登山護膝 單只入 左/L","cu":"TWD","up":790,"usp":399,"url":"https://24h.pchome.com.tw/prod/DEBQB3-A900AQUSI","piu":"https://d.ecimg.tw/items/DEBQB3A900AQUSI/000001_1609234436.jpg","qu":1,"sid":"19200oju-wf12-f6xo-rupm-nqouj93pg3dk_1633165280"}}],"lane":2}
    """.trimIndent()
    val execute: Connection.Response = Jsoup.connect("https://unification.useinsider.com/api/event/v1/insert")
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .followRedirects(true)
        .ignoreHttpErrors(true)
        .ignoreContentType(true)
        .userAgent(
            "Mozilla/5.0 AppleWebKit/537.36 (KHTML," +
                    " like Gecko) Chrome/45.0.2454.4 Safari/537.36"
        )
        .method(Connection.Method.POST)
        .requestBody(jsonContent)
        .maxBodySize(1000000 * 30) // 30 mb ~
        .timeout(0) // infinite timeout
        .execute()

    println(execute.body())
}

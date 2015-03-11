import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.fusesource.scalamd.Markdown
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FlatSpec}

/**
 *
 *
 * @author Brian Schlining
 * @since 2015-03-11T13:44:00
 */
@RunWith(classOf[JUnitRunner])
class MarkdownSpec extends FlatSpec with Matchers {

  def diffFormats(name: String): (Int, String) = {
    val textFile = new File(this.getClass.getResource("/" + name + ".text").toURI)
    val htmlFile = new File(this.getClass.getResource("/" + name + ".html").toURI)
    val text = Markdown(FileUtils.readFileToString(textFile, "UTF-8")).trim
    //      println("[%s]".format(text))
    val html = FileUtils.readFileToString(htmlFile, "UTF-8").trim
    val diffIndex = StringUtils.indexOfDifference(text, html)
    val diff = StringUtils.difference(text, html)
    (diffIndex, diff)
  }

  val names = Seq("Images",
    "TOC",
    "Amps and angle encoding",
    "Auto links",
    "Backslash escapes",
    "Blockquotes with code blocks",
    "Hard-wrapped paragraphs with list-like lines",
    "Horizontal rules",
    "Inline HTML (Advanced)",
    "Inline HTML (Simple)",
    "Inline HTML comments",
    "Links, inline style",
    "Links, reference style",
    "Literal quotes in titles",
    "Nested blockquotes",
    "Ordered and unordered lists",
    "Strong and em together",
    "Tabs",
    "Tidyness",
    "SmartyPants",
    "Markdown inside inline HTML",
    "Spans inside headers",
    "Macros"
  )

  for (name <- names) {
    "The MarkdownProcess" should s"correctly generate $name" in {
      diffFormats(name)._1 should be(-1)
    }
  }


}

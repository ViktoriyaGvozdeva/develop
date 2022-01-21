package constructor_classes

import javax.xml.xpath.XPath

data class LocatorsTypesConstructor constructor(
    val androidId: String = "id",
    val androidXPath: String = "xpath"
)

val locatorsTypes = LocatorsTypesConstructor()
package constructor_classes

data class LocatorsConstructor(
    val androidAccessibilityId: String = "",
    val androidId: String = "",
    val androidXpath: String = "",
)

val example = LocatorsConstructor()

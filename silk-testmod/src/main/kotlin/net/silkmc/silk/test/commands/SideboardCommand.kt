package net.silkmc.silk.test.commands

import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.game.sideboard.showSideboard
import net.silkmc.silk.game.sideboard.sideboard

val sideboardCommand = testCommand("sideboard") {
    argument<String>("example") { example ->
        suggestList { sideboardExamples.keys }
        runs {
            val sideboard = sideboardExamples[example()] ?: return@runs
            source.playerOrException.showSideboard(sideboard)
        }
    }
}

private val sideboardExamples = mapOf(
    "simple" to sideboard(
        literalText("Simple Sideboard") { color = 0x6DFF41 }
    ) {
        literalLine("Hey, how")
        literalLine("are you?")
        literalLine(" ")
        literalLine("colors work as well!") {
            color = 0xFF9658
        }
    },

    "simple_changing" to sideboard(
        literalText("Simple Sideboard") { color = 0x6DFF41 }
    ) {
        literalLine("Hey, how")
        literalLine("are you?")
        literalLine(" ")
        lineChangingPeriodically(1000) {
            literalText("changing color") {
                color = (0x000000..0xFFFFFF).random()
            }
        }
    }
)
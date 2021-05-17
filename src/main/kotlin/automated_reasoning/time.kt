package automated_reasoning

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun dateTime() = with(Clock.System.now().toLocalDateTime(TimeZone.of("Europe/Berlin"))) { date() + " " + time() }

internal fun time() = with(Clock.System.now().toLocalDateTime(TimeZone.of("Europe/Berlin"))) { time() }

private fun LocalDateTime.time() = "${"%02d".format(hour)}:${"%02d".format(minute)}:${second.toString().padStart(2, '0')}.${nanosecond.toString().padStart(9, '0')}"

private fun LocalDateTime.date() = "${"%04d".format(year)}-${"%02d".format(monthNumber)}-${"%02d".format(dayOfMonth)}"

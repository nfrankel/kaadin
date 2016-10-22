/*
 * Copyright 2016 Nicolas Fränkel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.frankel.kaadin

import com.vaadin.data.util.converter.*
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.text.NumberFormat
import java.util.*

class Person(val name: String, val job: String, val birthDate: Date) {
    companion object {
        internal val devs = arrayListOf(Person("John Doe", "Developer", Date()),
                Person("Alfred Pennyworth", "Butler", Date(10, 10, 1)),
                Person("Bruce Wayne", "Batman", Date(80, 0, 25)))
        internal val lead = Person("Jane Doe", "Team Leader", Date(96, 4, 15))
    }
}

internal class Line(val company: String,
                    val year0q1: Double, val year0q2: Double, val year1q1: Double, val year1q2: Double,
                    val year2q1: Double, val year2q2: Double, val year3q1: Double, val year3q2: Double,
                    val year4q1: Double, val year4q2: Double, val year5q1: Double, val year5q2: Double,
                    val year6q1: Double, val year6q2: Double, val year7q1: Double, val year7q2: Double,
                    val year8q1: Double, val year8q2: Double, val year9q1: Double, val year9q2: Double) {
    companion object {
        internal val data = IntRange(1, 30).map { randomLine() }
        private fun randomConsonant() = RandomStringUtils.random(1, "bcdfghjklmnpqrstvwxz")
        private fun randomVowel() = RandomStringUtils.random(1, "aeiouy")
        private fun randomSyllable() = when (RandomUtils.nextInt(0, 10)) {
            0, 1, 2 -> randomConsonant() + randomConsonant() + randomVowel()
            3, 4 -> randomVowel() + randomConsonant()
            5 -> randomVowel() + randomVowel()
            6 -> randomConsonant()
            7 -> randomVowel()
            else -> randomConsonant() + randomVowel()
        }

        private fun randomCompany() = IntRange(0, RandomUtils.nextInt(2, 4)).map { randomSyllable() }.joinToString("").capitalize()
        private fun randomFigure() = RandomUtils.nextDouble(1000.0, 9999.0)
        private fun randomLine() = Line(randomCompany(),
                randomFigure(), randomFigure(), randomFigure(), randomFigure(),
                randomFigure(), randomFigure(), randomFigure(), randomFigure(),
                randomFigure(), randomFigure(), randomFigure(), randomFigure(),
                randomFigure(), randomFigure(), randomFigure(), randomFigure(),
                randomFigure(), randomFigure(), randomFigure(), randomFigure())
    }
}

internal val converter = object : Converter<String, java.lang.Double> {
    override fun convertToPresentation(value: java.lang.Double, targetType: Class<out String>, locale: Locale) =
            "$${NumberFormat.getInstance(locale).format(value)}"

    override fun convertToModel(value: String, targetType: Class<out java.lang.Double>, locale: Locale) =
            throw UnsupportedOperationException("not implemented")

    override fun getPresentationType() = String::class.java
    override fun getModelType() = java.lang.Double::class.java
}
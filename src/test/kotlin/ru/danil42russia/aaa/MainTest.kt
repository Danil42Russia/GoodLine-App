package ru.danil42russia.aaa

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

object MainTest : Spek({
    group("exit-code ") {
        group("code 0") {
            test("T0-1") {
                val args = "-login user@xyz.com -pass qwerty12345"
                val code = application(args.toArray())

                assertEquals(0, code)
            }

            test("T0-2") {
                val args = "-pass 123456qwerty -login user@mk.ru"
                val code = application(args.toArray())

                assertEquals(0, code)
            }

            test("T0-3") {
                val args = "-login user@xyz.com -pass qwerty12345 -res A.B -role READ"
                val code = application(args.toArray())

                assertEquals(0, code)
            }

            test("T0-4") {
                val args = "-pass 123456qwerty -login user@mk.ru -res A.B.C -role READ"
                val code = application(args.toArray())

                assertEquals(0, code)
            }

            test("T0-5") {
                val args =
                    "-vol 100 -login user@xyz.com -res A.B.C.D.E -role READ -de 01.02.2019 -ds 01.01.2019 -pass qwerty12345"
                val code = application(args.toArray())

                assertEquals(0, code)
            }
        }

        group("code 1") {
            test("T1-1") {
                val args = ""
                val code = application(args.toArray())

                assertEquals(1, code)
            }

            test("T1-2") {
                val args = "-login user@xyz.com -pass qwerty12345 -params"
                val code = application(args.toArray())

                assertEquals(1, code)
            }

            test("T1-3") {
                val args = "-login user@xyz.com"
                val code = application(args.toArray())

                assertEquals(1, code)
            }

            test("T1-4") {
                val args = "-pass qwerty12345"
                val code = application(args.toArray())

                assertEquals(1, code)
            }

            test("T1-5") {
                val args = "-h"
                val code = application(args.toArray())

                assertEquals(1, code)
            }
        }

        group("code 2") {
            test("T2-1") {
                val args = "-login user -pass 1234"
                val code = application(args.toArray())

                assertEquals(2, code)
            }

            test("T2-2") {
                val args = "-login user@u -pass 1234"
                val code = application(args.toArray())

                assertEquals(2, code)
            }
        }

        group("code 3") {
            test("T3-1") {
                val args = "-login useD@xyz.zom -pass 1234"
                val code = application(args.toArray())

                assertEquals(3, code)
            }

            test("T3-2") {
                val args = "-login e@e.ru -pass 1234"
                val code = application(args.toArray())

                assertEquals(3, code)
            }
        }

        group("code 4") {
            test("T4-1") {
                val args = "-login user@xyz.com -pass 1234"
                val code = application(args.toArray())

                assertEquals(4, code)
            }

            test("T4-2") {
                val args = "-login user@mk.ru -pass 1234"
                val code = application(args.toArray())

                assertEquals(4, code)
            }
        }

        group("code 5") {
            test("T5-1") {
                val args = "-login user@xyz.com -pass qwerty12345 -res A.B.C -role READE"
                val code = application(args.toArray())

                assertEquals(5, code)
            }

            test("T5-2") {
                val args = "-login user@mk.ru -pass 123456qwerty -res A.B.C -role write"
                val code = application(args.toArray())

                assertEquals(5, code)
            }
        }

        group("code 7") {
            test("T7-1") {
                val args =
                    "-login user@xyz.com -pass qwerty12345 -res A.B.C -role READ -ds 01.02.2019 -de 01.01.2019 -vol 100"
                val code = application(args.toArray())

                assertEquals(7, code)
            }

            test("T7-2") {
                val args =
                    "-login user@mk.ru -pass 123456qwerty -res A.B.C -role WRITE -ds 01.01.2019 -de 01.02.2019 -vol -100"
                val code = application(args.toArray())

                assertEquals(7, code)
            }

            test("T7-3") {
                val args =
                    "-login user@mk.ru -pass 123456qwerty -res A.B.C -role WRITE -ds 01.02.2019 -de 01.01.2019 -vol -100"
                val code = application(args.toArray())

                assertEquals(7, code)
            }
        }
    }
})

private fun String.toArray(): Array<String> {
    return this.split(' ').toTypedArray()
}
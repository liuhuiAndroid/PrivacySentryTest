package com.yl.lib.privacysentry

import java.lang.reflect.Modifier

/**
 * @author yulun
 * @since 2023-08-29 18:04
 */
object ReflexObjectUtil {
    fun test(className: String) {
        try {
            val cl = Class.forName(className)
            val supercl = cl.superclass
            val modifiers = Modifier.toString(cl.modifiers)
            if (modifiers.length > 0) print("$modifiers ")
            print("class $className")
            if (supercl != null && supercl != Any::class.java) {
                print("extends " + supercl.name)
            }
            print("\n{\n")

            printFields(cl)
            println()
            printConstructors(cl)
            println()
            printMethods(cl)
            print("}\n")
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }

    fun printConstructors(cl: Class<*>) {
        val constructors = cl.constructors
        for (c in constructors) {
            print("  ")
            val name = c.name
            val modifiers = Modifier.toString(c.modifiers)
            if (modifiers.length > 0) print("$modifiers ")
            print("$name (")
            val paramType = c.parameterTypes
            for (j in paramType.indices) {
                if (j > 0) print(", ")
                print(paramType[j].name)
            }
            print(")")
        }
        println()
    }

    fun printMethods(cl: Class<*>) {
        val methods = cl.declaredMethods
        for (m in methods) {
            print("  ")
            val name = m.name
            val modifiers = Modifier.toString(m.modifiers)
            if (modifiers.length > 0) print("$modifiers ")
            val returnType = m.returnType
            print(returnType.name + " ")
            print("$name (")
            val paramType = m.parameterTypes
            for (j in paramType.indices) {
                if (j > 0) print(", ")
                print(paramType[j].name)
            }
            print(")")
            println()
        }
        println()
    }

    fun printFields(cl: Class<*>) {
        val fields = cl.declaredFields
        for (f in fields) {
            print("  ")
            val name = f.name
            val type = f.type
            val modifiers = Modifier.toString(f.modifiers)
            if (modifiers.length > 0) print("$modifiers ")
            println(type.name + " " + name + ";")
        }
    }
}

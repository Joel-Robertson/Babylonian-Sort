# Babylonian-Sort
Java program for sorting/converting large base-60 numbers.

This program is designed to run concurrently with a separate main file that contains a list of base-60 number inputs. The largest number that Java can work with is the long.MAX_VALUE (9,223,372,036,854,775,807), which exists in base-10 and is plenty large enough for most Java applications. But what if you still need something larger? The Bayblonians worked with a base-60 sexagesimal number system, in which the numbers 0 - 59 all had a unique value: 0 - X. The base-60 numbering system is useful for time related problems.

An example input/output follows:

INPUT:
2mlJuLHuLDk0PNM
4WAcBJXWuwtKGvi
Sl5ncqLdbr6vSLe
jLEsFUaJvgKmFuh
3L5PlEg9XR3OwJQ
erR1Py4KLSGNXf9
9DRdhXpKqvi8zay
OXyH9Sa0jV2Ew07
XkxuRhnpBdPJR0b
BI09MQkKt1AmUbo

SORTED OUTPUT:
2mlJuLHuLDk0PNM
3L5PlEg9XR3OwJQ
4WAcBJXWuwtKGvi
9DRdhXpKqvi8zay
erR1Py4KLSGNXf9
jLEsFUaJvgKmFuh
BI09MQkKt1AmUbo
OXyH9Sa0jV2Ew07
Sl5ncqLdbr6vSLe
XkxuRhnpBdPJR0b

These numbers represent astronomically large values that exceed the maximum Java value.

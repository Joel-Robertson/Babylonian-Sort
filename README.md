# Babylonian-Sort
Java program for sorting/converting large base-60 numbers.

This program is designed to run concurrently with a separate main file that contains a list of base-60 number inputs. The largest number that Java can work with is the long.MAX_VALUE (9,223,372,036,854,775,807), which exists in base-10 and is plenty large enough for most Java applications. But what if you still need something larger? The Bayblonians worked with a base-60 sexagesimal number system, in which the numbers 0 - 59 all had a unique value: 0 - X. The base-60 numbering systems is useful for time related problems.

An example input/output follows:

Input:                        Sorted Output:
2mlJuLHuLDk0PNM               2mlJuLHuLDk0PNM
4WAcBJXWuwtKGvi               3L5PlEg9XR3OwJQ
Sl5ncqLdbr6vSLe               4WAcBJXWuwtKGvi
jLEsFUaJvgKmFuh               9DRdhXpKqvi8zay
3L5PlEg9XR3OwJQ               erR1Py4KLSGNXf9
erR1Py4KLSGNXf9               jLEsFUaJvgKmFuh
9DRdhXpKqvi8zay               BI09MQkKt1AmUbo
OXyH9Sa0jV2Ew07               OXyH9Sa0jV2Ew07
XkxuRhnpBdPJR0b               Sl5ncqLdbr6vSLe
BI09MQkKt1AmUbo               XkxuRhnpBdPJR0b


These numbers represent astronomically large values that exceed the maximum Java value.

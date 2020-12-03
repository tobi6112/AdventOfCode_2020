# 🎄 Advent Of Code 2020

**This repository contains my [Advent Of Code 2020](https://adventofcode.com/) solutions written in Kotlin**

<p align="center"><img height="350" width="auto" src="https://i.imgur.com/l1iQzgN.png" />
<sub><sup>Source: <a href="https://github.com/apolukhin/christmas-tree">apolukhin/christmas-tree</a>, Boost Software License 1.0</sub></sup></p>

## 🚀 Running

Run regular with:
```
gradlew run
```

The application takes a `--days` or `-d` arguments to determine which daily Task should be executed. It takes numbers concated with `,` within (1..25). For example: `--days 1,2,4,5` and with gradle run:

```
gradlew run --args="-d 1,2,3,4,5"
```

## 🧰 Parameters

```
Options:
    --days, -d [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25] -> Days to execute as List e.g. 1,2,3,4 { String }
    --repetitions, -r [2] -> Repetitions of solving the exercise. Kind of a benchmark warmup for the JIT Compiler { Int }
    --help, -h -> Usage info
```

![https://i.imgur.com/HbEeD3A.png](https://i.imgur.com/HbEeD3A.png)

## 🕑 Benchmarking

According to [Avoiding Benchmarking Pitfalls on the JVM](https://www.oracle.com/technical-resources/articles/java/architect-benchmarking.html) the measured elapsed time for the exercises is not meaningful. So a JMH Benchmark is included.

Run detailed benchmarks with (5 Warmups - 10 Iterations):
```
gradlew benchmark
```

To select a specific day for benchmark use the `include` Property which takes a regular expression. For example you can benchmark day 3 by 
```
gradlew benchmark -Pinclude=Day3
gradlew benchmark -Pinclude=aoc.exercises.Day3
```

The benchmark will be printed to the console and exported as JSON to `build/reports/benchmarks/main`

![https://i.imgur.com/LA5Him8.png](https://i.imgur.com/LA5Him8.png)

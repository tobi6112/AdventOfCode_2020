# 🎄 Advent Of Code 2020

**This repository contains my [Advent Of Code 2020](https://adventofcode.com/) solutions written in Kotlin**

<p align="center"><img height="350" width="auto" src="https://i.imgur.com/l1iQzgN.png" />
<br />
<sub><sup>Source: <a href="https://github.com/apolukhin/christmas-tree">apolukhin/christmas-tree</a>, Boost Software License 1.0</sup></sub></p>

## 🚀 Running

Run regular with:
```
gradle run
```

The application takes a `--days` or `-d` arguments to determine which daily Task should be executed. It takes numbers concated with `,` within (1..25). For example: `--days 1,2,4,5` and with gradle run:

```
gradle run --args="-d 1,2,3,4,5"
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
gradle benchmark
```

To select a specific day for benchmark use the `include` Property which takes a regular expression. For example you can benchmark day 3 by 
```
gradle benchmark -Pinclude=Day3
gradle benchmark -Pinclude=aoc.exercises.day3
```

The benchmark will be printed to the console and exported as JSON to `build/reports/benchmarks/main`

![https://i.imgur.com/LA5Him8.png](https://i.imgur.com/LA5Him8.png)

# 🧙 Codegen

The project is able to create its file structure for each days exercise by running
```
gradle codegen -Pd=5
```
To get your personal input you need to set your SESSION Cookie as environment variable AOC_SESSION or put it inside a .env file at the root of the project.
Or you can skip obtaining the inputs by adding the `s` property
```
gradle codegen -Pd=5 -Ps
```

And you can download the exercise textual description as readme with
```
gradle readme -Pd=5
```
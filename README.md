# 🎄 Advent Of Code 2020

**This repository contains my [Advent Of Code 2020](https://adventofcode.com/) solutions written in Kotlin**

## 🚀 Running

Run regular with:
```
gradlew run
```

The application takes a `--days` or `-d` arguments to determine which daily Task should be executed. It takes numbers concated with `,` within (1..25). For example: `--days 1,2,4,5` and with gradle run:

```
gradlew run --args="-d 1,2,3,4,5"
```

![https://i.imgur.com/TuKAZrf.png](https://i.imgur.com/TuKAZrf.png)

## 🕑 Benchmarking

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

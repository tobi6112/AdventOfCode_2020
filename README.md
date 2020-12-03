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

Run detailed benchmarks with (5 Warmups - 10 Iterations):
```
gradlew benchmark
```

The benchmark will be printed to the console and exported as JSON to `build/reports/benchmarks/main`

![https://i.imgur.com/LA5Him8.png](https://i.imgur.com/LA5Him8.png)

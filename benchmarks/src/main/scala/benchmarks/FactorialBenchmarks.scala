package benchmarks

import futures.ScalaFuture
import org.openjdk.jmh.annotations.Benchmark
import tasks.ScalazTask

import scala.concurrent.Await
import scala.concurrent.duration._


class FactorialBenchmarks {

  @Benchmark
  def scalaFutures(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Await.result(new ScalaFuture().factorial(5000), Duration.Inf)
  }

  @Benchmark
  def scalaFuturesTrampoline(): Unit = {
    import concurrent.Execution.Implicits.trampoline
    Await.result(new ScalaFuture().factorial(5000), Duration.Inf)
  }

  @Benchmark
  def scalazTasks(): Unit = {
    new ScalazTask().factorial(5000).unsafePerformSync
  }

}

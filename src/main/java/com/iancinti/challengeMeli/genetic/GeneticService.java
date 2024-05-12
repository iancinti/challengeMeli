package com.iancinti.challengeMeli.genetic;

import com.iancinti.challengeMeli.coupon.adapter.out.webflux.model.ItemRestResponse;
import com.iancinti.challengeMeli.coupon.domain.CouponResponse;
import com.iancinti.challengeMeli.genetic.model.Chromosome;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneticService {

    public Mono<CouponResponse> generate(
            int populationSize,
            int generations,
            double mutationRate,
            List<ItemRestResponse> prices,
            int numItems,
            int maxCouponAmount
    ) {
        return Mono.from(Flux.range(0, populationSize)
                .map(i -> new Chromosome(numItems))
                .doOnNext(Chromosome::initialize)
                .collectList()
                .flatMapMany(population -> Flux.range(0, generations)
                        .flatMap(gen -> Flux.fromIterable(population)
                                .flatMap(chromosome -> GeneticOperations.selectByTournament(population.toArray(new Chromosome[0]))
                                        .collectList()
                                        .flatMap(parents -> GeneticOperations.crossover(parents.get(0), parents.size() > 1 ? parents.get(1) : parents.get(0)))
                                        .flatMap(offspring -> FitnessCalculator.calculateFitness(offspring, prices.stream().map(ItemRestResponse::getPrice).toList(), maxCouponAmount)
                                                .map(fitness -> {
                                                    offspring.setFitness(fitness);
                                                    return offspring;
                                                }))
                                )
                                .collectList()
                                .flatMap(newPopulation -> GeneticOperations.mutatePopulation(newPopulation, mutationRate)
                                        .then(Mono.just(newPopulation)))
                                .flatMap(newPopulation -> FitnessCalculator.calculateFitnessInParallel(newPopulation, prices.stream().map(ItemRestResponse::getPrice).toList(), maxCouponAmount)
                                        .then(Mono.just(newPopulation)))
                        )
                        .last()
                )
                .flatMap(c -> getMaxFitness(c, prices)));
    }

    private Mono<CouponResponse> getMaxFitness(List<Chromosome> population, List<ItemRestResponse> prices) {
        return Flux.fromIterable(population)
                .sort((c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()))
                .reduce(Chromosome::maxCromosome)
                .map(chromosome -> {

                    List<ItemRestResponse> pricesResponse = new ArrayList<>();
                    for (int i = 0; i < chromosome.getGenes().length; i++) {
                        if (chromosome.getGenes()[i]) {
                            pricesResponse.add(prices.get(i));
                        }
                    }

                    Double total = pricesResponse.stream().map(ItemRestResponse::getPrice).reduce(0.0, Double::sum);
                    List<String> ids = pricesResponse.stream().map(ItemRestResponse::getId).toList();
                    return new CouponResponse(ids, total);
                });
    }
}

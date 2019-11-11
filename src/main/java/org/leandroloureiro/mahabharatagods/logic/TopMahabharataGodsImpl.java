package org.leandroloureiro.mahabharatagods.logic;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.leandroloureiro.mahabharatagods.model.God;
import org.leandroloureiro.mahabharatagods.service.IndianGod;
import org.leandroloureiro.mahabharatagods.service.IndianGods;
import org.leandroloureiro.mahabharatagods.service.MahabharataBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class TopMahabharataGodsImpl implements TopMahabharataGods {

    private static final Logger LOG = LoggerFactory.getLogger(TopMahabharataGodsImpl.class);

    @Inject
    @RestClient
    MahabharataBook mahabharataBook;

    @Inject
    @RestClient
    IndianGods indianGods;

    @Inject
    @RestClient
    IndianGod indianGod;

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletionStage<List<God>> getTopMahabharataGods() {

        final CompletionStage<String> mahabharataContent = mahabharataBook.getBookContent();
        final CompletionStage<List<String>> godList = indianGods.getGodList();

        return mahabharataContent.thenCombineAsync(godList, (mahabharata, gods) -> checkGodsAndCount(gods, mahabharata)
                .stream()
                .map(g -> g.toCompletableFuture().join())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparingLong(God::getHitCount).reversed())
                .limit(3)
                .collect(Collectors.toList())

        );

    }

    private List<CompletionStage<Optional<God>>> checkGodsAndCount(final List<String> gods, final String mahabharata) {

        return gods.stream()
                .map(god -> indianGod.getIndianGod(god)
                        .thenApplyAsync(__ -> {
                            LOG.info("Indian God {} is valid.", god);
                            return Optional.of(countAppearances(god, mahabharata));
                        })
                        .exceptionally(__ -> {
                            LOG.warn("Indian God {} not valid!", god);
                            return Optional.empty();
                        }))
                .collect(toList());

    }

    private God countAppearances(final String god, final String mahabharata) {
        LOG.info("Calculating the appearances for god: {}", god);
        return new God(god, StringUtils.countMatches(mahabharata, god));
    }
}

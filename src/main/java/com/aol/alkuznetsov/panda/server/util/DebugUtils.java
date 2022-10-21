package com.aol.alkuznetsov.panda.server.util;

import com.aol.alkuznetsov.panda.server.model.Animal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DebugUtils {
  public static String getMessageWithAnimalsList(String message, List<Animal> animals) {
    List<ImmutableTriple<Long, String, String>> animalTriples =
        animals.stream()
            .map(
                animal ->
                    new ImmutableTriple<>(
                        animal.getId(), animal.getName(), animal.getType().getName()))
            .collect(Collectors.toList());
    String animalTriplesString = StringUtils.join(animalTriples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), animalTriplesString);
  }
}

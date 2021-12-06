import TerminalOperators.Collect;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) throws Exception {

    List<String> stringList = Stream.of("qbwerty", "dqw", "qcwqw", "qawsxae", "fg", "vcz", "qq")
      .collect(Collectors.toList());

    List<String> stringListForPrefix = Stream.of("qbwerty", "dqw", "qcwqw", "qawsxae", "fg", "vcz", "qq")
      .collect(Collectors.toList());

    List<Integer> integerList = Stream.of(1, 2, 3).collect(Collectors.toList());

    List<Integer> emptyList = new LinkedList<>();

    int[] mas = {1, 2, 3, 4};


    System.out.println("1 - Метод, возвращающий среднее значение списка целых чисел: ");
    System.out.println(meanValue(integerList)); //1+2+3 = 6 -> 6/2 = 2
    System.out.println();

    System.out.println("2 - Метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»");
    System.out.println(toUppercase(stringListForPrefix));
    System.out.println();

    System.out.println("3 - Метод, возвращающий список квадратов всех встречающихся только один раз элементов списка");
    System.out.println(uniqueItemsToSquare());
    System.out.println();

    System.out.println("4 - Метод, принимающий на вход коллекцию строк и возвращающий\n" +
      "все строки, начинающиеся с заданной буквы, отсортированные по алфавиту");
    System.out.println(sortByAlphabetical(stringList, 'q'));
    System.out.println();

    System.out.println("5 - Метод, принимающий на вход коллекцию и возвращающий её последний элемент или " +
      "кидающий исключение, если коллекция пуста");
    System.out.println(getLastElementOfCollection(integerList));
    //System.out.println(getLastElementOfCollection(emptyList));
    System.out.println();

    System.out.println("6 - Метод, принимающий на вход массив целых чисел, возвращающий\n" +
      "сумму чётных чисел или 0, если чётных чисел нет");
    System.out.println(returnSumOfEvenNumbers(mas));
    System.out.println();

    System.out.println("7 - Метод, преобразовывающий все строки в списке в Map, где первый\n" +
      "символ – ключ, оставшиеся – значение");
    System.out.println(convertStringsToSymbolKey(stringList));

  }

  //1 - Метод, возвращающий среднее значение списка целых чисел
  private static double meanValue(List<Integer> integerList) {
    return integerList.stream().reduce(Integer::sum).get() / (double) integerList.size();
  }

  //2 все строки в списке в верхний регистр и добавляющий к ним префикс
  private static List<String> toUppercase(List<String> stringListForPrefix) {
    return stringListForPrefix.stream()
      .map(String::toUpperCase)
      .map(s -> "_NEW_".concat(s).concat("_NEW_"))
      .collect(Collectors.toList());
  }

  //3 список квадратов всех встречающихся только один раз элементов списка
  private static List<Integer> uniqueItemsToSquare() {
    List<Integer> generatedIntList = generateIntegerList();

    System.out.println("Сгенерированный список: ");
    generatedIntList.forEach(System.out::println);

    System.out.println("Квадрат уникальных элементов списка: ");
    return generatedIntList.stream()
      .distinct()
      .map(val -> val * val)
      .collect(Collectors.toList());
  }

  private static List<Integer> generateIntegerList() {
    return Stream.generate(() -> (int) (Math.random() * 10))
        .limit(8)
        .collect(Collectors.toList());
  }

  //4 возвращающий все строки, начинающиеся с заданной буквы
  // отсортированные по алфавиту
  private static List<String> sortByAlphabetical(List<String> stringList, char letter) {
    return stringList.stream().sorted().filter(s -> s.charAt(0) == letter).collect(Collectors.toList());
  }

  //5 принимающий на вход коллекцию и возвращающий её последний элемент
  // или кидающий исключение, если коллекция пуста
  private static Object getLastElementOfCollection(Collection<?> collection) throws InitializationException {
    collection.stream()
      .findAny()
      .orElseThrow(InitializationException::new);

    return collection.stream()
      .skip(collection.size() - 1)
      .findAny()
      .orElseThrow(IllegalArgumentException::new);
  }

  //6 принимающий на вход массив целых чисел
  // возвращающий сумму чётных чисел или 0, если чётных чисел нет
  private static int returnSumOfEvenNumbers(int[] mas) {
    return Arrays.stream(mas)
      .filter(value -> value % 2 == 0)
      .sum();
  }

  //7 все строки в списке в Map, где первый символ – ключ, оставшиеся – значение
  private static Map<Character, String> convertStringsToSymbolKey(List<String> stringList) {
    return stringList.stream()
      .collect(Collectors.toMap(str -> str.charAt(0), str -> str.substring(1), (str, next) -> str));
  }
}
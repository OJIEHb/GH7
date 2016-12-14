import java.util.*;
import java.util.function.*;

/**
 * Created by andrey on 10.12.16.
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <E> List<E> filter(List<E> elements, Predicate<E> filter) {
        List<E> result = new ArrayList<E>();
        for(E e: elements){
            result.add(e);
        }
        return result;
    }

    public static <E> boolean anyMatch(List<E> elements, Predicate<E> predicate) {
        for(E e: elements){
            if(predicate.test(e)){
                return true;
            }
        }
        return false;
    }

    public static <E> boolean allMatch(List<E> elements, Predicate<E> predicate) {
        for (E element:elements)
            if(!predicate.test(element))
                return false;
        return true;
    }

    public static <E> boolean noneMatch(List<E> elements, Predicate<E> predicate) {
        for (E element:elements)
            if(predicate.test(element))
                return false;
        return true;
    }

    public static <T, R> List<R> map(List<T> elements, Function<T, R> mappingFunction) {
        List<R> result=new ArrayList<>();
        for(T element:elements)
            result.add(mappingFunction.apply(element));
        return result;
    }

    public static <E> Optional<E> max(List<E> elements, Comparator<E> comparator) {
        if(elements.size()==0)
            return Optional.empty();
        E result = elements.get(0);
        for(E e:elements){
            result = comparator.compare(result,e)>0 ? result:e;
        }
        return Optional.of(result);
    }

    public static <E> Optional<E> min(List<E> elements, Comparator<E> comparator) {
        if(elements.size()==0)
            return Optional.empty();
        E result = elements.get(0);
        for(E e:elements){
            result = comparator.compare(result,e)<0 ? result:e;
        }
        return Optional.of(result);
    }

    public static <E> List<E> distinct(List<E> elements) {
        List<E> result=new ArrayList<>();
        for(E element:elements)
            if(!result.contains(element))
                result.add(element);
        return result;
    }

    public static <E> void forEach(List<E> elements, Consumer<E> consumer) {
        for (E e:elements)
            consumer.accept(e);
    }

    public static <E> Optional<E> reduce(List<E> elements, BinaryOperator<E> accumulator) {
        if(elements.size()==0)
            return Optional.empty();
        E element = elements.get(0);
        for (int i =1;i<elements.size();i++){
            element = accumulator.apply(element,elements.get(i));
        }
        return Optional.of(element);
    }

    public static <E> E reduce(E seed, List<E> elements, BinaryOperator<E> accumulator) {
        E result = seed;
        for(E element:elements) {
            result = accumulator.apply(result, element);
        }
        return result;
    }

    public static <E> Map<Boolean, List<E>> partitionBy(List<E> elements, Predicate<E> predicate) {
        Map<Boolean,List<E>> result=new HashMap<>();
        List<E> trueGroup=new ArrayList<>();
        List<E> falseGroup=new ArrayList<>();
        for(E element:elements){
            if(predicate.test(element))
                trueGroup.add(element);
            else
                falseGroup.add(element);
        }
        result.put(true,trueGroup);
        result.put(false,falseGroup);
        return result;
    }

    public static <T, K> Map<K, List<T>> groupBy(List<T> elements, Function<T, K> classifier) {
        HashMap<K,List<T>> result = new HashMap<K, List<T>>();
        for(T e:elements) {
            K key = classifier.apply(e);
            if(result.containsKey(key)){
                result.get(key).add(e);
            }
            else{
                ArrayList<T> list = new ArrayList<T>();
                list.add(e);
                result.put(key,list);
            }
        }
        return result;
    }
    public static <T, K, U> Map<K, U> toMap(List<T> elements,
                                            Function<T, K> keyFunction,
                                            Function<T, U> valueFunction,
                                            BinaryOperator<U> mergeFunction) {
        HashMap<K,U> map = new HashMap<K, U>();
        for(T e: elements){
            U value = valueFunction.apply(e);
            K key = keyFunction.apply(e);
            if(map.containsKey(key)){
                map.put(key,mergeFunction.apply(map.get(key),value));
            }else
                map.put(key,value);
        }
        return map;
    }
}



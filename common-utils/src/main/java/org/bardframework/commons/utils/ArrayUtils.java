package org.bardframework.commons.utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Vahid Zafari on 8/12/2016.
 */
public final class ArrayUtils {

    /**
     * An empty immutable {@code Object} array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * An empty immutable {@code Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * An empty immutable {@code Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty immutable {@code Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * An empty immutable {@code Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty immutable {@code Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * An empty immutable {@code Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * An empty immutable {@code Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * An empty immutable {@code Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    /**
     * An empty immutable {@code Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    /**
     * The index value when an element is not found in a list or array: {@code -1}.
     * This value is returned by methods in this class and can also be used in comparisons with values returned by
     * various method from {@link java.util.List}.
     */
    public static final int INDEX_NOT_FOUND = -1;

    private ArrayUtils() {
    }

    /**
     * @param start, int end, int increment
     * @return Array of numbers from start to end with specific increment step
     * if increment < 0 then returns String object with zero length
     */
    public static String[] getNumberArray(int start, int end, int increment) {
        if (increment > 0) {
            int count = (end - start) / increment;
            String[] numbers = new String[count];
            for (int i = 0; i < count; i++) {
                numbers[i] = String.valueOf(start + (i * increment));
            }
            return numbers;
        } else {
            return new String[0];
        }
    }

    /**
     * @param original(Array), int splitSize
     * @return split given array to small array with split size length
     */
    public static <T> List<T[]> arraySplit(T[] original, int splitSize) {
        List<T[]> result = new ArrayList<>();
        if (original == null) {
            return result;
        }
        int currentPosition = 0;
        while (currentPosition < original.length) {
            int remaining = original.length - currentPosition + 1;
            if (remaining <= splitSize) {
                result.add(Arrays.copyOfRange(original, currentPosition, original.length));
            } else {
                result.add(Arrays.copyOfRange(original, currentPosition, currentPosition + splitSize));
            }
            currentPosition += splitSize;
        }
        return result;
    }

    /**
     * @return List of numbers in Long format
     */
    public static List<Long> getLongList(Collection<String> nums) {
        List<Long> ids = new ArrayList<>();
        if (nums != null) {
            for (String num : nums) {
                ids.add(Long.valueOf(num));
            }
        }
        return ids;
    }

    /**
     * @return List of numbers in Long format
     */
    public static List<Long> getLongList(String... nums) {
        return getLongList(Arrays.asList(nums));
    }


    /**
     * <p>Converts the given array into a {@link java.util.Map}. Each element of the array
     * must be either a {@link java.util.Map.Entry} or an Array, containing at least two
     * elements, where the first element is used as key and the second as
     * value.
     *
     * <p>This method can be used to initialize:
     * <pre>
     * // Create a Map mapping colors.
     * Map colorMap = ArrayUtils.toMap(new String[][] {
     *     {"RED", "#FF0000"},
     *     {"GREEN", "#00FF00"},
     *     {"BLUE", "#0000FF"}});
     * </pre>
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array an array whose elements are either a {@link java.util.Map.Entry} or an Array containing at least two elements, may be {@code null}
     * @return a {@code Map} that was created from the array
     * @throws IllegalArgumentException if one element of this Array is itself an Array containing less then two elements
     * @throws IllegalArgumentException if the array contains elements other than {@link java.util.Map.Entry} and an Array
     */
    public static Map<Object, Object> toMap(final Object[] array) {
        if (array == null) {
            return null;
        }
        final Map<Object, Object> map = new HashMap<>((int) (array.length * 1.5));
        for (int i = 0; i < array.length; i++) {
            final Object object = array[i];
            if (object instanceof Map.Entry<?, ?>) {
                final Map.Entry<?, ?> entry = (Map.Entry<?, ?>) object;
                map.put(entry.getKey(), entry.getValue());
            } else if (object instanceof Object[]) {
                final Object[] entry = (Object[]) object;
                if (entry.length < 2) {
                    throw new IllegalArgumentException("Array element " + i + ", '"
                            + object
                            + "', has a length less than 2");
                }
                map.put(entry[0], entry[1]);
            } else {
                throw new IllegalArgumentException("Array element " + i + ", '"
                        + object
                        + "', is neither of type Map.Entry nor an Array");
            }
        }
        return map;
    }

    /**
     * <p>Create a type-safe generic array.
     *
     * <p>The Java language does not allow an array to be created from a generic type:
     *
     * <pre>
     * public static &lt;T&gt; T[] createAnArray(int size) {
     * return new T[size]; // compiler error here
     * }
     * public static &lt;T&gt; T[] createAnArray(int size) {
     * return (T[]) new Object[size]; // ClassCastException at runtime
     * }
     * </pre>
     *
     * <p>Therefore new arrays of generic types can be created with this method.
     * For example, an array of Strings can be created:
     *
     * <pre>
     * String[] array = ArrayUtils.toArray("1", "2");
     * String[] emptyArray = ArrayUtils.&lt;String&gt;toArray();
     * </pre>
     *
     * <p>The method is typically used in scenarios, where the caller itself uses generic types
     * that have to be combined into an array.
     *
     * <p>Note, this method makes only sense to provide arguments of the same type so that the
     * compiler can deduce the type of the array itself. While it is possible to select the
     * type explicitly like in
     * <code>Number[] array = ArrayUtils.&lt;Number&gt;toArray(Integer.valueOf(42), Double.valueOf(Math.PI))</code>,
     * there is no real advantage when compared to
     * <code>new Number[] {Integer.valueOf(42), Double.valueOf(Math.PI)}</code>.
     *
     * @param <T>   the array's element type
     * @param items the varargs array items, null allowed
     * @return the array, not null unless a null array is passed in
     */
    public static <T> T[] toArray(final T... items) {
        return items;
    }

    /**
     * <p>Shallow clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>The objects in the array are not cloned, thus there is no special
     * handling for multi-dimensional arrays.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param <T>   the component type of the array
     * @param array the array to shallow clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static long[] clone(final long[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static int[] clone(final int[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static short[] clone(final short[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static char[] clone(final char[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static byte[] clone(final byte[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static double[] clone(final double[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static float[] clone(final float[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static boolean[] clone(final boolean[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * @param array the array to check for {@code null} or empty
     * @param type  the class representation of the desired array
     * @param <T>   the class type
     * @return the same array, {@code public static} empty array if {@code null}
     * @throws IllegalArgumentException if the type argument is null
     */
    public static <T> T[] nullToEmpty(final T[] array, final Class<T[]> type) {
        if (type == null) {
            throw new IllegalArgumentException("The type must not be null");
        }

        if (array == null) {
            return type.cast(Array.newInstance(type.getComponentType(), 0));
        }
        return array;
    }


    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Object[] nullToEmpty(final Object[] array) {
        if (isEmpty(array)) {
            return EMPTY_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Class<?>[] nullToEmpty(final Class<?>[] array) {
        if (isEmpty(array)) {
            return EMPTY_CLASS_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static String[] nullToEmpty(final String[] array) {
        if (isEmpty(array)) {
            return EMPTY_STRING_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static long[] nullToEmpty(final long[] array) {
        if (isEmpty(array)) {
            return EMPTY_LONG_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static int[] nullToEmpty(final int[] array) {
        if (isEmpty(array)) {
            return EMPTY_INT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static short[] nullToEmpty(final short[] array) {
        if (isEmpty(array)) {
            return EMPTY_SHORT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static char[] nullToEmpty(final char[] array) {
        if (isEmpty(array)) {
            return EMPTY_CHAR_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static byte[] nullToEmpty(final byte[] array) {
        if (isEmpty(array)) {
            return EMPTY_BYTE_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static double[] nullToEmpty(final double[] array) {
        if (isEmpty(array)) {
            return EMPTY_DOUBLE_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static float[] nullToEmpty(final float[] array) {
        if (isEmpty(array)) {
            return EMPTY_FLOAT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static boolean[] nullToEmpty(final boolean[] array) {
        if (isEmpty(array)) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Long[] nullToEmpty(final Long[] array) {
        if (isEmpty(array)) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Integer[] nullToEmpty(final Integer[] array) {
        if (isEmpty(array)) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Short[] nullToEmpty(final Short[] array) {
        if (isEmpty(array)) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Character[] nullToEmpty(final Character[] array) {
        if (isEmpty(array)) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Byte[] nullToEmpty(final Byte[] array) {
        if (isEmpty(array)) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Double[] nullToEmpty(final Double[] array) {
        if (isEmpty(array)) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Float[] nullToEmpty(final Float[] array) {
        if (isEmpty(array)) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Defensive programming technique to change a {@code null}
     * reference to an empty one.
     *
     * <p>This method returns an empty array for a {@code null} input array.
     *
     * <p>As a memory optimizing technique an empty array passed in will be overridden with
     * the empty {@code public static} references in this class.
     *
     * @param array the array to check for {@code null} or empty
     * @return the same array, {@code public static} empty array if {@code null} or empty input
     */
    public static Boolean[] nullToEmpty(final Boolean[] array) {
        if (isEmpty(array)) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        return array;
    }

    /**
     * <p>Produces a new array containing the elements between
     * the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * <p>The component type of the subarray is always the same as
     * that of the input array. Thus, if the input is an array of type
     * {@code Date}, the following usage is envisaged:
     *
     * <pre>
     * Date[] someDates = (Date[]) ArrayUtils.subarray(allDates, 2, 5);
     * </pre>
     *
     * @param <T>                 the component type of the array
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static <T> T[] subarray(final T[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        final Class<?> type = array.getClass().getComponentType();
        if (newSize <= 0) {
            @SuppressWarnings("unchecked") // OK, because array is of type T
            final T[] emptyArray = (T[]) Array.newInstance(type, 0);
            return emptyArray;
        }
        @SuppressWarnings("unchecked") // OK, because array is of type T
        final T[] subarray = (T[]) Array.newInstance(type, newSize);
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code long} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static long[] subarray(final long[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_LONG_ARRAY;
        }

        final long[] subarray = new long[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code int} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static int[] subarray(final int[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_INT_ARRAY;
        }

        final int[] subarray = new int[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code short} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static short[] subarray(final short[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_SHORT_ARRAY;
        }

        final short[] subarray = new short[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code char} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static char[] subarray(final char[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_CHAR_ARRAY;
        }

        final char[] subarray = new char[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code byte} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static byte[] subarray(final byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_BYTE_ARRAY;
        }

        final byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code double} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static double[] subarray(final double[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }

        final double[] subarray = new double[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code float} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static float[] subarray(final float[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }

        final float[] subarray = new float[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Produces a new {@code boolean} array containing the elements
     * between the start and end indices.
     *
     * <p>The start index is inclusive, the end index exclusive.
     * Null array input produces null output.
     *
     * @param array               the array
     * @param startIndexInclusive the starting index. Undervalue (&lt;0)
     *                            is promoted to 0, overvalue (&gt;array.length) results
     *                            in an empty array.
     * @param endIndexExclusive   elements up to endIndex-1 are present in the
     *                            returned subarray. Undervalue (&lt; startIndex) produces
     *                            empty array, overvalue (&gt;array.length) is demoted to
     *                            array length.
     * @return a new array containing the elements between
     * the start and end indices.
     */
    public static boolean[] subarray(final boolean[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        final int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }

        final boolean[] subarray = new boolean[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * <p>Any multi-dimensional aspects of the arrays are ignored.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final Object[] array1, final Object[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final long[] array1, final long[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final int[] array1, final int[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final short[] array1, final short[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final char[] array1, final char[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final byte[] array1, final byte[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final double[] array1, final double[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final float[] array1, final float[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Checks whether two arrays are the same length, treating
     * {@code null} arrays as length {@code 0}.
     *
     * @param array1 the first array, may be {@code null}
     * @param array2 the second array, may be {@code null}
     * @return {@code true} if length of arrays matches, treating
     * {@code null} as an empty array
     */
    public static boolean isSameLength(final boolean[] array1, final boolean[] array2) {
        return getLength(array1) == getLength(array2);
    }

    /**
     * <p>Returns the length of the specified array.
     * This method can deal with {@code Object} arrays and with primitive arrays.
     *
     * <p>If the input array is {@code null}, {@code 0} is returned.
     *
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array the array to retrieve the length from, may be null
     * @return The length of the array, or {@code 0} if the array is {@code null}
     * @throws IllegalArgumentException if the object argument is not an array.
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * <p>Checks whether two arrays are the same type taking into account
     * multi-dimensional arrays.
     *
     * @param array1 the first array, must not be {@code null}
     * @param array2 the second array, must not be {@code null}
     * @return {@code true} if type of arrays matches
     * @throws IllegalArgumentException if either array is {@code null}
     */
    public static boolean isSameType(final Object array1, final Object array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        return array1.getClass().getName().equals(array2.getClass().getName());
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final Object[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final long[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final int[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final short[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final char[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final byte[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final double[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final float[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final boolean[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final boolean[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        boolean tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final byte[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final char[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        char tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final double[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        double tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final float[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        float tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final int[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final long[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        long tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Under value (&lt;0) is promoted to 0, over value (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Under value (&lt; start index) results in no
     *                            change. Over value (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final Object[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        Object tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     */
    public static void reverse(final short[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = Math.max(startIndexInclusive, 0);
        int j = Math.min(array.length, endIndexExclusive) - 1;
        short tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * Swaps two elements in the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap(["1", "2", "3"], 0, 2) -&gt; ["3", "2", "1"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3"], 0, 0) -&gt; ["1", "2", "3"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3"], 1, 0) -&gt; ["2", "1", "3"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3"], 0, 5) -&gt; ["1", "2", "3"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3"], -1, 1) -&gt; ["2", "1", "3"]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final Object[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([true, false, true], 0, 2) -&gt; [true, false, true]</li>
     *     <li>ArrayUtils.swap([true, false, true], 0, 0) -&gt; [true, false, true]</li>
     *     <li>ArrayUtils.swap([true, false, true], 1, 0) -&gt; [false, true, true]</li>
     *     <li>ArrayUtils.swap([true, false, true], 0, 5) -&gt; [true, false, true]</li>
     *     <li>ArrayUtils.swap([true, false, true], -1, 1) -&gt; [false, true, true]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final long[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final int[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final short[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final char[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final byte[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final double[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final float[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps two elements in the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for a {@code null} or empty input array or for overflow indices.
     * Negative indices are promoted to 0(zero).</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 2) -&gt; [3, 2, 1]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 0) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 1, 0) -&gt; [2, 1, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], 0, 5) -&gt; [1, 2, 3]</li>
     *     <li>ArrayUtils.swap([1, 2, 3], -1, 1) -&gt; [2, 1, 3]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element to swap
     * @param offset2 the index of the second element to swap
     */
    public static void swap(final boolean[] array, final int offset1, final int offset2) {
        if (array == null || array.length == 0) {
            return;
        }
        swap(array, offset1, offset2, 1);
    }

    /**
     * Swaps a series of elements in the given boolean array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([true, false, true, false], 0, 2, 1) -&gt; [true, false, true, false]</li>
     *     <li>ArrayUtils.swap([true, false, true, false], 0, 0, 1) -&gt; [true, false, true, false]</li>
     *     <li>ArrayUtils.swap([true, false, true, false], 0, 2, 2) -&gt; [true, false, true, false]</li>
     *     <li>ArrayUtils.swap([true, false, true, false], -3, 2, 2) -&gt; [true, false, true, false]</li>
     *     <li>ArrayUtils.swap([true, false, true, false], 0, 3, 3) -&gt; [false, false, true, true]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final boolean[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final boolean aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given byte array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final byte[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final byte aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given char array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final char[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final char aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given double array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final double[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final double aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given float array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final float[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final float aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }

    }

    /**
     * Swaps a series of elements in the given int array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final int[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final int aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given long array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final long[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final long aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 2, 1) -&gt; ["3", "2", "1", "4"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 0, 1) -&gt; ["1", "2", "3", "4"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3", "4"], 2, 0, 2) -&gt; ["3", "4", "1", "2"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3", "4"], -3, 2, 2) -&gt; ["3", "4", "1", "2"]</li>
     *     <li>ArrayUtils.swap(["1", "2", "3", "4"], 0, 3, 3) -&gt; ["4", "2", "3", "1"]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final Object[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final Object aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Swaps a series of elements in the given short array.
     *
     * <p>This method does nothing for a {@code null} or empty input array or
     * for overflow indices. Negative indices are promoted to 0(zero). If any
     * of the sub-arrays to swap falls outside of the given array, then the
     * swap is stopped at the end of the array and as many as possible elements
     * are swapped.</p>
     * <p>
     * Examples:
     * <ul>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 2, 1) -&gt; [3, 2, 1, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 0, 1) -&gt; [1, 2, 3, 4]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 2, 0, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], -3, 2, 2) -&gt; [3, 4, 1, 2]</li>
     *     <li>ArrayUtils.swap([1, 2, 3, 4], 0, 3, 3) -&gt; [4, 2, 3, 1]</li>
     * </ul>
     *
     * @param array   the array to swap, may be {@code null}
     * @param offset1 the index of the first element in the series to swap
     * @param offset2 the index of the second element in the series to swap
     * @param len     the number of elements to swap starting with the given indices
     */
    public static void swap(final short[] array, int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        if (offset1 == offset2) {
            return;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final short aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }

    /**
     * Shifts the order of the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final Object[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final long[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final int[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final short[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final char[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final byte[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final double[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final float[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array  the array to shift, may be {@code null}
     * @param offset The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *               rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final boolean[] array, final int offset) {
        if (array == null) {
            return;
        }
        shift(array, 0, array.length, offset);
    }

    /**
     * Shifts the order of a series of elements in the given boolean array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final boolean[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given byte array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given char array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given double array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given float array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given int array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given long array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final Object[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * Shifts the order of a series of elements in the given short array.
     *
     * <p>There is no special handling for multi-dimensional arrays. This method
     * does nothing for {@code null} or empty input arrays.</p>
     *
     * @param array               the array to shift, may be {@code null}
     * @param startIndexInclusive the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are shifted in the array. Undervalue (&lt; start index) results in no
     *                            change. Overvalue (&gt;array.length) is demoted to array length.
     * @param offset              The number of positions to rotate the elements.  If the offset is larger than the number of elements to
     *                            rotate, than the effective offset is modulo the number of elements to rotate.
     */
    public static void shift(final short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        if (array == null) {
            return;
        }
        if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
            return;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive >= array.length) {
            endIndexExclusive = array.length;
        }
        int n = endIndexExclusive - startIndexInclusive;
        if (n <= 1) {
            return;
        }
        offset %= n;
        if (offset < 0) {
            offset += n;
        }
        // For algorithm explanations and proof of O(n) time complexity and O(1) space complexity
        // see https://beradrian.wordpress.com/2015/04/07/shift-an-array-in-on-in-place/
        while (n > 1 && offset > 0) {
            final int n_offset = n - offset;

            if (offset > n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
                n = offset;
                offset -= n_offset;
            } else if (offset < n_offset) {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                startIndexInclusive += offset;
                n = n_offset;
            } else {
                swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
                break;
            }
        }
    }

    /**
     * <p>Finds the index of the given object in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array        the array to search through for the object, may be {@code null}
     * @param objectToFind the object to find, may be {@code null}
     * @return the index of the object within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    /**
     * <p>Finds the index of the given object in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array        the array to search through for the object, may be {@code null}
     * @param objectToFind the object to find, may be {@code null}
     * @param startIndex   the index to start searching at
     * @return the index of the object within the array starting at the index,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given object within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array        the array to traverse backwards looking for the object, may be {@code null}
     * @param objectToFind the object to find, may be {@code null}
     * @return the last index of the object within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final Object[] array, final Object objectToFind) {
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given object in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than
     * the array length will search from the end of the array.
     *
     * @param array        the array to traverse for looking for the object, may be {@code null}
     * @param objectToFind the object to find, may be {@code null}
     * @param startIndex   the start index to traverse backwards from
     * @return the last index of the object within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final Object[] array, final Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i = startIndex; i >= 0; i--) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the object is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array        the array to search through
     * @param objectToFind the object to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final long[] array, final long valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final long[] array, final long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final long[] array, final long valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final long[] array, final long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final long[] array, final long valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final int[] array, final int valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final int[] array, final int valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final int[] array, final int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final int[] array, final int valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final short[] array, final short valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final short[] array, final short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final short[] array, final short valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final short[] array, final short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final short[] array, final short valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final char[] array, final char valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final char[] array, final char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final char[] array, final char valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final char[] array, final char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final char[] array, final char valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final byte[] array, final byte valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final byte[] array, final byte valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final byte[] array, final byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final byte[] array, final byte valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final double[] array, final double valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value within a given tolerance in the array.
     * This method will return the index of the first value which falls between the region
     * defined by valueToFind - tolerance and valueToFind + tolerance.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param tolerance   tolerance of the search
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final double[] array, final double valueToFind, final double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final double[] array, final double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     * This method will return the index of the first value which falls between the region
     * defined by valueToFind - tolerance and valueToFind + tolerance.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @param tolerance   tolerance of the search
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        final double min = valueToFind - tolerance;
        final double max = valueToFind + tolerance;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final double[] array, final double valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value within a given tolerance in the array.
     * This method will return the index of the last value which falls between the region
     * defined by valueToFind - tolerance and valueToFind + tolerance.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param tolerance   tolerance of the search
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final double[] array, final double valueToFind, final double tolerance) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     * This method will return the index of the last value which falls between the region
     * defined by valueToFind - tolerance and valueToFind + tolerance.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @param tolerance   search for value within plus/minus this amount
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        final double min = valueToFind - tolerance;
        final double max = valueToFind + tolerance;
        for (int i = startIndex; i >= 0; i--) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final double[] array, final double valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if a value falling within the given tolerance is in the
     * given array.  If the array contains a value within the inclusive range
     * defined by (value - tolerance) to (value + tolerance).
     *
     * <p>The method returns {@code false} if a {@code null} array
     * is passed in.
     *
     * @param array       the array to search
     * @param valueToFind the value to find
     * @param tolerance   the array contains the tolerance of the search
     * @return true if value falling within tolerance is in array
     */
    public static boolean contains(final double[] array, final double valueToFind, final double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final float[] array, final float valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final float[] array, final float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final float[] array, final float valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
     * array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final float[] array, final float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final float[] array, final float valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the index of the given value in the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int indexOf(final boolean[] array, final boolean valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * <p>Finds the index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex is treated as zero. A startIndex larger than the array
     * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
     *
     * @param array       the array to search through for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the index to start searching at
     * @return the index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null}
     * array input
     */
    public static int indexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Finds the last index of the given value within the array.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) if
     * {@code null} array input.
     *
     * @param array       the array to traverse backwards looking for the object, may be {@code null}
     * @param valueToFind the object to find
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final boolean[] array, final boolean valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    /**
     * <p>Finds the last index of the given value in the array starting at the given index.
     *
     * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     *
     * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than
     * the array length will search from the end of the array.
     *
     * @param array       the array to traverse for looking for the object, may be {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
     */
    public static int lastIndexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>Checks if the value is in the given array.
     *
     * <p>The method returns {@code false} if a {@code null} array is passed in.
     *
     * @param array       the array to search through
     * @param valueToFind the value to find
     * @return {@code true} if the array contains the object
     */
    public static boolean contains(final boolean[] array, final boolean valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    // Primitive/Object array converters
    // ----------------------------------------------------------------------

    // Character array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Characters to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Character} array, may be {@code null}
     * @return a {@code char} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static char[] toPrimitive(final Character[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Character to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Character} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code char} array, {@code null} if null array input
     */
    public static char[] toPrimitive(final Character[] array, final char valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            final Character b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive chars to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code char} array
     * @return a {@code Character} array, {@code null} if null array input
     */
    public static Character[] toObject(final char[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        final Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Long array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Longs to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Long} array, may be {@code null}
     * @return a {@code long} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static long[] toPrimitive(final Long[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Long to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Long} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code long} array, {@code null} if null array input
     */
    public static long[] toPrimitive(final Long[] array, final long valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            final Long b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive longs to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code long} array
     * @return a {@code Long} array, {@code null} if null array input
     */
    public static Long[] toObject(final long[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        final Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Int array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Integers to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Integer} array, may be {@code null}
     * @return an {@code int} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Integer to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Integer} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return an {@code int} array, {@code null} if null array input
     */
    public static int[] toPrimitive(final Integer[] array, final int valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            final Integer b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive ints to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array an {@code int} array
     * @return an {@code Integer} array, {@code null} if null array input
     */
    public static Integer[] toObject(final int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Short array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Shorts to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Short} array, may be {@code null}
     * @return a {@code byte} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static short[] toPrimitive(final Short[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Short to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Short} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code byte} array, {@code null} if null array input
     */
    public static short[] toPrimitive(final Short[] array, final short valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            final Short b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive shorts to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code short} array
     * @return a {@code Short} array, {@code null} if null array input
     */
    public static Short[] toObject(final short[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        final Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Byte array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Bytes to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Byte} array, may be {@code null}
     * @return a {@code byte} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static byte[] toPrimitive(final Byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Bytes to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Byte} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code byte} array, {@code null} if null array input
     */
    public static byte[] toPrimitive(final Byte[] array, final byte valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            final Byte b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive bytes to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code byte} array
     * @return a {@code Byte} array, {@code null} if null array input
     */
    public static Byte[] toObject(final byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Double array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Doubles to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Double} array, may be {@code null}
     * @return a {@code double} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static double[] toPrimitive(final Double[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Doubles to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Double} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code double} array, {@code null} if null array input
     */
    public static double[] toPrimitive(final Double[] array, final double valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            final Double b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive doubles to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code double} array
     * @return a {@code Double} array, {@code null} if null array input
     */
    public static Double[] toObject(final double[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        final Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    //   Float array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Floats to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Float} array, may be {@code null}
     * @return a {@code float} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static float[] toPrimitive(final Float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Floats to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Float} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code float} array, {@code null} if null array input
     */
    public static float[] toPrimitive(final Float[] array, final float valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            final Float b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive floats to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code float} array
     * @return a {@code Float} array, {@code null} if null array input
     */
    public static Float[] toObject(final float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        final Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Boolean array converters
    // ----------------------------------------------------------------------

    /**
     * <p>Converts an array of object Booleans to primitives.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code Boolean} array, may be {@code null}
     * @return a {@code boolean} array, {@code null} if null array input
     * @throws NullPointerException if array content is {@code null}
     */
    public static boolean[] toPrimitive(final Boolean[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * <p>Converts an array of object Booleans to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array        a {@code Boolean} array, may be {@code null}
     * @param valueForNull the value to insert if {@code null} found
     * @return a {@code boolean} array, {@code null} if null array input
     */
    public static boolean[] toPrimitive(final Boolean[] array, final boolean valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            final Boolean b = array[i];
            result[i] = (b == null ? valueForNull : b);
        }
        return result;
    }

    /**
     * <p>Converts an array of primitive booleans to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array a {@code boolean} array
     * @return a {@code Boolean} array, {@code null} if null array input
     */
    public static Boolean[] toObject(final boolean[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        final Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (array[i] ? Boolean.TRUE : Boolean.FALSE);
        }
        return result;
    }

    // ----------------------------------------------------------------------

    /**
     * <p>Checks if an array of Objects is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive longs is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final long[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive ints is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final int[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive shorts is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final short[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive chars is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final char[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive bytes is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final byte[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive doubles is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final double[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive floats is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final float[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive booleans is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static boolean isEmpty(final boolean[] array) {
        return getLength(array) == 0;
    }

    // ----------------------------------------------------------------------

    /**
     * <p>Checks if an array of Objects is not empty and not {@code null}.
     *
     * @param <T>   the component type of the array
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive longs is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive ints is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive shorts is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive chars is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive bytes is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive doubles is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive floats is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive booleans is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static boolean isNotEmpty(final boolean[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(null, null)     = null
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * ArrayUtils.addAll([null], [null]) = [null, null]
     * ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
     * </pre>
     *
     * @param <T>    the component type of the array
     * @param array1 the first array whose elements are added to the new array, may be {@code null}
     * @param array2 the second array whose elements are added to the new array, may be {@code null}
     * @return The new array, {@code null} if both arrays are {@code null}.
     * The type of the new array is the type of the first array,
     * unless the first array is null, in which case the type is the same as the second array.
     * @throws IllegalArgumentException if the array types are incompatible
     */
    public static <T> T[] addAll(final T[] array1, final T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final Class<?> type1 = array1.getClass().getComponentType();
        @SuppressWarnings("unchecked") // OK, because array is of type T
        final T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        try {
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        } catch (final ArrayStoreException ase) {
            // Check if problem was due to incompatible types
            /*
             * We do this here, rather than before the copy because:
             * - it would be a wasted check most of the time
             * - safer, in case check turns out to be too strict
             */
            final Class<?> type2 = array2.getClass().getComponentType();
            if (!type1.isAssignableFrom(type2)) {
                throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of "
                        + type1.getName(), ase);
            }
            throw ase; // No, so rethrow original
        }
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new boolean[] array.
     */
    public static boolean[] addAll(final boolean[] array1, final boolean... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final boolean[] joinedArray = new boolean[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new char[] array.
     */
    public static char[] addAll(final char[] array1, final char... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final char[] joinedArray = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new byte[] array.
     */
    public static byte[] addAll(final byte[] array1, final byte... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new short[] array.
     */
    public static short[] addAll(final short[] array1, final short... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final short[] joinedArray = new short[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new int[] array.
     */
    public static int[] addAll(final int[] array1, final int... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final int[] joinedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new long[] array.
     */
    public static long[] addAll(final long[] array1, final long... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final long[] joinedArray = new long[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new float[] array.
     */
    public static float[] addAll(final float[] array1, final float... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final float[] joinedArray = new float[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array.
     * @param array2 the second array whose elements are added to the new array.
     * @return The new double[] array.
     */
    public static double[] addAll(final double[] array1, final double... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final double[] joinedArray = new double[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element, unless the element itself is null,
     * in which case the return type is Object[]
     *
     * <pre>
     * ArrayUtils.add(null, null)      = IllegalArgumentException
     * ArrayUtils.add(null, "a")       = ["a"]
     * ArrayUtils.add(["a"], null)     = ["a", null]
     * ArrayUtils.add(["a"], "b")      = ["a", "b"]
     * ArrayUtils.add(["a", "b"], "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param <T>     the component type of the array
     * @param array   the array to "add" the element to, may be {@code null}
     * @param element the object to add, may be {@code null}
     * @return A new array containing the existing elements plus the new element
     * The returned array type will be that of the input array (unless null),
     * in which case it will have the same type as the element.
     * If both are null, an IllegalArgumentException is thrown
     * @throws IllegalArgumentException if both arguments are null
     */
    public static <T> T[] add(final T[] array, final T element) {
        Class<?> type;
        if (array != null) {
            type = array.getClass().getComponentType();
        } else if (element != null) {
            type = element.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        @SuppressWarnings("unchecked") // type must be T
        final T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, true)          = [true]
     * ArrayUtils.add([true], false)       = [true, false]
     * ArrayUtils.add([true, false], true) = [true, false, true]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static boolean[] add(final boolean[] array, final boolean element) {
        final boolean[] newArray = (boolean[]) copyArrayGrow1(array, Boolean.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static byte[] add(final byte[] array, final byte element) {
        final byte[] newArray = (byte[]) copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, '0')       = ['0']
     * ArrayUtils.add(['1'], '0')      = ['1', '0']
     * ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static char[] add(final char[] array, final char element) {
        final char[] newArray = (char[]) copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static double[] add(final double[] array, final double element) {
        final double[] newArray = (double[]) copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static float[] add(final float[] array, final float element) {
        final float[] newArray = (float[]) copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static int[] add(final int[] array, final int element) {
        final int[] newArray = (int[]) copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static long[] add(final long[] array, final long element) {
        final long[] newArray = (long[]) copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static short[] add(final short[] array, final short element) {
        final short[] newArray = (short[]) copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * Returns a copy of the given array of size 1 greater than the argument.
     * The last value of the array is left to the default value.
     *
     * @param array                 The array to copy, must not be {@code null}.
     * @param newArrayComponentType If {@code array} is {@code null}, create a
     *                              size 1 array of this type.
     * @return A new copy of the array of size 1 greater than the input.
     */
    private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) {
        if (array != null) {
            final int arrayLength = Array.getLength(array);
            final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }

    /**
     * Underlying implementation of add(array, index, element) methods.
     * The last parameter is the class, which may not equal element.getClass
     * for primitives.
     *
     * @param array   the array to add the element to, may be {@code null}
     * @param index   the position of the new object
     * @param element the object to add
     * @param clss    the type of the element being added
     * @return A new array containing the existing elements and the new element
     */
    private static Object add(final Object array, final int index, final Object element, final Class<?> clss) {
        if (array == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
            }
            final Object joinedArray = Array.newInstance(clss, 1);
            Array.set(joinedArray, 0, element);
            return joinedArray;
        }
        final int length = Array.getLength(array);
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        final Object result = Array.newInstance(clss, length + 1);
        System.arraycopy(array, 0, result, 0, index);
        Array.set(result, index, element);
        if (index < length) {
            System.arraycopy(array, index, result, index + 1, length - index);
        }
        return result;
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove(["a"], 0)           = []
     * ArrayUtils.remove(["a", "b"], 0)      = ["b"]
     * ArrayUtils.remove(["a", "b"], 1)      = ["a"]
     * ArrayUtils.remove(["a", "b", "c"], 1) = ["a", "c"]
     * </pre>
     *
     * @param <T>   the component type of the array
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    @SuppressWarnings("unchecked") // remove() always creates an array of the same type as its input
    public static <T> T[] remove(final T[] array, final int index) {
        return (T[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, "a")            = null
     * ArrayUtils.removeElement([], "a")              = []
     * ArrayUtils.removeElement(["a"], "b")           = ["a"]
     * ArrayUtils.removeElement(["a", "b"], "a")      = ["b"]
     * ArrayUtils.removeElement(["a", "b", "a"], "a") = ["b", "a"]
     * </pre>
     *
     * @param <T>     the component type of the array
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static <T> T[] removeElement(final T[] array, final Object element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([true], 0)              = []
     * ArrayUtils.remove([true, false], 0)       = [false]
     * ArrayUtils.remove([true, false], 1)       = [true]
     * ArrayUtils.remove([true, true, false], 1) = [true, false]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static boolean[] remove(final boolean[] array, final int index) {
        return (boolean[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, true)                = null
     * ArrayUtils.removeElement([], true)                  = []
     * ArrayUtils.removeElement([true], false)             = [true]
     * ArrayUtils.removeElement([true, false], false)      = [true]
     * ArrayUtils.removeElement([true, false, true], true) = [false, true]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static boolean[] removeElement(final boolean[] array, final boolean element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1], 0)          = []
     * ArrayUtils.remove([1, 0], 0)       = [0]
     * ArrayUtils.remove([1, 0], 1)       = [1]
     * ArrayUtils.remove([1, 0, 1], 1)    = [1, 1]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static byte[] remove(final byte[] array, final int index) {
        return (byte[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1)        = null
     * ArrayUtils.removeElement([], 1)          = []
     * ArrayUtils.removeElement([1], 0)         = [1]
     * ArrayUtils.removeElement([1, 0], 0)      = [1]
     * ArrayUtils.removeElement([1, 0, 1], 1)   = [0, 1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static byte[] removeElement(final byte[] array, final byte element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove(['a'], 0)           = []
     * ArrayUtils.remove(['a', 'b'], 0)      = ['b']
     * ArrayUtils.remove(['a', 'b'], 1)      = ['a']
     * ArrayUtils.remove(['a', 'b', 'c'], 1) = ['a', 'c']
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static char[] remove(final char[] array, final int index) {
        return (char[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 'a')            = null
     * ArrayUtils.removeElement([], 'a')              = []
     * ArrayUtils.removeElement(['a'], 'b')           = ['a']
     * ArrayUtils.removeElement(['a', 'b'], 'a')      = ['b']
     * ArrayUtils.removeElement(['a', 'b', 'a'], 'a') = ['b', 'a']
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static char[] removeElement(final char[] array, final char element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1.1], 0)           = []
     * ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
     * ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
     * ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static double[] remove(final double[] array, final int index) {
        return (double[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1.1)            = null
     * ArrayUtils.removeElement([], 1.1)              = []
     * ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
     * ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
     * ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static double[] removeElement(final double[] array, final double element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1.1], 0)           = []
     * ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
     * ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
     * ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static float[] remove(final float[] array, final int index) {
        return (float[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1.1)            = null
     * ArrayUtils.removeElement([], 1.1)              = []
     * ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
     * ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
     * ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static float[] removeElement(final float[] array, final float element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1], 0)         = []
     * ArrayUtils.remove([2, 6], 0)      = [6]
     * ArrayUtils.remove([2, 6], 1)      = [2]
     * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static int[] remove(final int[] array, final int index) {
        return (int[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1)      = null
     * ArrayUtils.removeElement([], 1)        = []
     * ArrayUtils.removeElement([1], 2)       = [1]
     * ArrayUtils.removeElement([1, 3], 1)    = [3]
     * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static int[] removeElement(final int[] array, final int element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1], 0)         = []
     * ArrayUtils.remove([2, 6], 0)      = [6]
     * ArrayUtils.remove([2, 6], 1)      = [2]
     * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static long[] remove(final long[] array, final int index) {
        return (long[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1)      = null
     * ArrayUtils.removeElement([], 1)        = []
     * ArrayUtils.removeElement([1], 2)       = [1]
     * ArrayUtils.removeElement([1, 3], 1)    = [3]
     * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static long[] removeElement(final long[] array, final long element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.remove([1], 0)         = []
     * ArrayUtils.remove([2, 6], 0)      = [6]
     * ArrayUtils.remove([2, 6], 1)      = [2]
     * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static short[] remove(final short[] array, final int index) {
        return (short[]) remove((Object) array, index);
    }

    /**
     * <p>Removes the first occurrence of the specified element from the
     * specified array. All subsequent elements are shifted to the left
     * (subtracts one from their indices). If the array doesn't contains
     * such an element, no elements are removed from the array.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the first occurrence of the specified element. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <pre>
     * ArrayUtils.removeElement(null, 1)      = null
     * ArrayUtils.removeElement([], 1)        = []
     * ArrayUtils.removeElement([1], 2)       = [1]
     * ArrayUtils.removeElement([1, 3], 1)    = [3]
     * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     *
     * @param array   the array to remove the element from, may be {@code null}
     * @param element the element to be removed
     * @return A new array containing the existing elements except the first
     * occurrence of the specified element.
     */
    public static short[] removeElement(final short[] array, final short element) {
        final int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <p>Removes the element at the specified position from the specified array.
     * All subsequent elements are shifted to the left (subtracts one from
     * their indices).
     *
     * <p>This method returns a new array with the same elements of the input
     * array except the element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * @param array the array to remove the element from, may not be {@code null}
     * @param index the position of the element to be removed
     * @return A new array containing the existing elements except the element
     * at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    private static Object remove(final Object array, final int index) {
        final int length = getLength(array);
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        final Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        return result;
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll(["a", "b", "c"], 0, 2) = ["b"]
     * ArrayUtils.removeAll(["a", "b", "c"], 1, 2) = ["a"]
     * </pre>
     *
     * @param <T>     the component type of the array
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    @SuppressWarnings("unchecked") // removeAll() always creates an array of the same type as its input
    public static <T> T[] removeAll(final T[] array, final int... indices) {
        return (T[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, "a", "b")            = null
     * ArrayUtils.removeElements([], "a", "b")              = []
     * ArrayUtils.removeElements(["a"], "b", "c")           = ["a"]
     * ArrayUtils.removeElements(["a", "b"], "a", "c")      = ["b"]
     * ArrayUtils.removeElements(["a", "b", "a"], "a")      = ["b", "a"]
     * ArrayUtils.removeElements(["a", "b", "a"], "a", "a") = ["b"]
     * </pre>
     *
     * @param <T>    the component type of the array
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    @SafeVarargs
    public static <T> T[] removeElements(final T[] array, final T... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<T, MutableInt> occurrences = new HashMap<>(values.length);
        for (final T v : values) {
            final MutableInt count = occurrences.get(v);
            if (count == null) {
                occurrences.put(v, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final T key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        @SuppressWarnings("unchecked") // removeAll() always creates an array of the same type as its input
        final T[] result = (T[]) removeAll(array, toRemove);
        return result;
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static byte[] removeAll(final byte[] array, final int... indices) {
        return (byte[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static byte[] removeElements(final byte[] array, final byte... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final Map<Byte, MutableInt> occurrences = new HashMap<>(values.length);
        for (final byte v : values) {
            final Byte boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final byte key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (byte[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static short[] removeAll(final short[] array, final int... indices) {
        return (short[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static short[] removeElements(final short[] array, final short... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Short, MutableInt> occurrences = new HashMap<>(values.length);
        for (final short v : values) {
            final Short boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final short key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (short[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static int[] removeAll(final int[] array, final int... indices) {
        return (int[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static int[] removeElements(final int[] array, final int... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Integer, MutableInt> occurrences = new HashMap<>(values.length);
        for (final int v : values) {
            final Integer boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final int key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (int[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static char[] removeAll(final char[] array, final int... indices) {
        return (char[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static char[] removeElements(final char[] array, final char... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Character, MutableInt> occurrences = new HashMap<>(values.length);
        for (final char v : values) {
            final Character boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final char key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (char[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static long[] removeAll(final long[] array, final int... indices) {
        return (long[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static long[] removeElements(final long[] array, final long... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Long, MutableInt> occurrences = new HashMap<>(values.length);
        for (final long v : values) {
            final Long boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final long key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (long[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static float[] removeAll(final float[] array, final int... indices) {
        return (float[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static float[] removeElements(final float[] array, final float... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Float, MutableInt> occurrences = new HashMap<>(values.length);
        for (final float v : values) {
            final Float boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final float key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (float[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static double[] removeAll(final double[] array, final int... indices) {
        return (double[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, 1, 2)      = null
     * ArrayUtils.removeElements([], 1, 2)        = []
     * ArrayUtils.removeElements([1], 2, 3)       = [1]
     * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
     * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
     * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static double[] removeElements(final double[] array, final double... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Double, MutableInt> occurrences = new HashMap<>(values.length);
        for (final double v : values) {
            final Double boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final double key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (double[]) removeAll(array, toRemove);
    }

    /**
     * <p>Removes the elements at the specified positions from the specified array.
     * All remaining elements are shifted to the left.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except those at the specified positions. The component
     * type of the returned array is always the same as that of the input
     * array.
     *
     * <p>If the input array is {@code null}, an IndexOutOfBoundsException
     * will be thrown, because in that case no valid index can be specified.
     *
     * <pre>
     * ArrayUtils.removeAll([true, false, true], 0, 2) = [false]
     * ArrayUtils.removeAll([true, false, true], 1, 2) = [true]
     * </pre>
     *
     * @param array   the array to remove the element from, may not be {@code null}
     * @param indices the positions of the elements to be removed
     * @return A new array containing the existing elements except those
     * at the specified positions.
     * @throws IndexOutOfBoundsException if any index is out of range
     *                                   (index &lt; 0 || index &gt;= array.length), or if the array is {@code null}.
     */
    public static boolean[] removeAll(final boolean[] array, final int... indices) {
        return (boolean[]) removeAll((Object) array, indices);
    }

    /**
     * <p>Removes occurrences of specified elements, in specified quantities,
     * from the specified array. All subsequent elements are shifted left.
     * For any element-to-be-removed specified in greater quantities than
     * contained in the original array, no change occurs beyond the
     * removal of the existing matching items.
     *
     * <p>This method returns a new array with the same elements of the input
     * array except for the earliest-encountered occurrences of the specified
     * elements. The component type of the returned array is always the same
     * as that of the input array.
     *
     * <pre>
     * ArrayUtils.removeElements(null, true, false)               = null
     * ArrayUtils.removeElements([], true, false)                 = []
     * ArrayUtils.removeElements([true], false, false)            = [true]
     * ArrayUtils.removeElements([true, false], true, true)       = [false]
     * ArrayUtils.removeElements([true, false, true], true)       = [false, true]
     * ArrayUtils.removeElements([true, false, true], true, true) = [false]
     * </pre>
     *
     * @param array  the array to remove the element from, may be {@code null}
     * @param values the elements to be removed
     * @return A new array containing the existing elements except the
     * earliest-encountered occurrences of the specified elements.
     */
    public static boolean[] removeElements(final boolean[] array, final boolean... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Boolean, MutableInt> occurrences = new HashMap<>(2); // only two possible values here
        for (final boolean v : values) {
            final Boolean boxed = v;
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            final boolean key = array[i];
            final MutableInt count = occurrences.get(key);
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(key);
                }
                toRemove.set(i);
            }
        }
        return (boolean[]) removeAll(array, toRemove);
    }

    /**
     * Removes multiple array elements specified by index.
     *
     * @param array   source
     * @param indices to remove
     * @return new array of same type minus elements specified by unique values of {@code indices}
     */
    // package protected for access by unit tests
    static Object removeAll(final Object array, final int... indices) {
        final int length = getLength(array);
        int diff = 0; // number of distinct indexes, i.e. number of entries that will be removed
        final int[] clonedIndices = clone(indices);
        Arrays.sort(clonedIndices);

        // identify length of result array
        if (isNotEmpty(clonedIndices)) {
            int i = clonedIndices.length;
            int prevIndex = length;
            while (--i >= 0) {
                final int index = clonedIndices[i];
                if (index < 0 || index >= length) {
                    throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
                }
                if (index >= prevIndex) {
                    continue;
                }
                diff++;
                prevIndex = index;
            }
        }

        // create result array
        final Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
        if (diff < length) {
            int end = length; // index just after last copy
            int dest = length - diff; // number of entries so far not copied
            for (int i = clonedIndices.length - 1; i >= 0; i--) {
                final int index = clonedIndices[i];
                if (end - index > 1) { // same as (cp > 0)
                    final int cp = end - index - 1;
                    dest -= cp;
                    System.arraycopy(array, index + 1, result, dest, cp);
                    // Afer this copy, we still have room for dest items.
                }
                end = index;
            }
            if (end > 0) {
                System.arraycopy(array, 0, result, 0, end);
            }
        }
        return result;
    }

    /**
     * Removes multiple array elements specified by indices.
     *
     * @param array   source
     * @param indices to remove
     * @return new array of same type minus elements specified by the set bits in {@code indices}
     */
    // package protected for access by unit tests
    static Object removeAll(final Object array, final BitSet indices) {
        final int srcLength = getLength(array);

        final int removals = indices.cardinality(); // true bits are items to remove
        final Object result = Array.newInstance(array.getClass().getComponentType(), srcLength - removals);
        int srcIndex = 0;
        int destIndex = 0;
        int count;
        int set;
        while ((set = indices.nextSetBit(srcIndex)) != -1) {
            count = set - srcIndex;
            if (count > 0) {
                System.arraycopy(array, srcIndex, result, destIndex, count);
                destIndex += count;
            }
            srcIndex = indices.nextClearBit(set);
        }
        count = srcLength - srcIndex;
        if (count > 0) {
            System.arraycopy(array, srcIndex, result, destIndex, count);
        }
        return result;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to the class's
     * {@code compareTo} method.
     *
     * @param array the array to check
     * @param <T>   the datatype of the array to check, it must implement {@code Comparable}
     * @return whether the array is sorted
     */
    public static <T extends Comparable<? super T>> boolean isSorted(final T[] array) {
        return isSorted(array, (o1, o2) -> o1.compareTo(o2));
    }


    /**
     * <p>This method checks whether the provided array is sorted according to the provided {@code Comparator}.
     *
     * @param array      the array to check
     * @param comparator the {@code Comparator} to compare over
     * @param <T>        the datatype of the array
     * @return whether the array is sorted
     */
    public static <T> boolean isSorted(final T[] array, final Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator should not be null.");
        }

        if (array == null || array.length < 2) {
            return true;
        }

        T previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final T current = array[i];
            if (comparator.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final int[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        int previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final int current = array[i];
            if (Integer.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final long[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        long previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final long current = array[i];
            if (Long.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final short[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        short previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final short current = array[i];
            if (Integer.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final double[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        double previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final double current = array[i];
            if (Double.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final float[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        float previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final float current = array[i];
            if (Float.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final byte[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        byte previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final byte current = array[i];
            if (Integer.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * <p>This method checks whether the provided array is sorted according to natural ordering
     * ({@code false} before {@code true}).
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     */
    public static boolean isSorted(final boolean[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        boolean previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final boolean current = array[i];
            if (Boolean.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    /**
     * Removes the occurrences of the specified element from the specified boolean array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static boolean[] removeAllOccurrences(final boolean[] array, final boolean element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified char array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static char[] removeAllOccurrences(final char[] array, final char element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified byte array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static byte[] removeAllOccurrences(final byte[] array, final byte element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified short array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static short[] removeAllOccurrences(final short[] array, final short element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified int array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static int[] removeAllOccurrences(final int[] array, final int element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified long array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static long[] removeAllOccurrences(final long[] array, final long element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified float array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static float[] removeAllOccurrences(final float[] array, final float element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified double array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static double[] removeAllOccurrences(final double[] array, final double element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * Removes the occurrences of the specified element from the specified array.
     *
     * <p>
     * All subsequent elements are shifted to the left (subtracts one from their indices).
     * If the array doesn't contains such an element, no elements are removed from the array.
     * <code>null</code> will be returned if the input array is <code>null</code>.
     * </p>
     *
     * @param <T>     the type of object in the array
     * @param element the element to remove
     * @param array   the input array
     * @return A new array containing the existing elements except the occurrences of the specified element.
     */
    public static <T> T[] removeAllOccurrences(final T[] array, final T element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }

        final int[] indices = new int[array.length - index];
        indices[0] = index;
        int count = 1;

        while ((index = indexOf(array, element, indices[count - 1] + 1)) != INDEX_NOT_FOUND) {
            indices[count++] = index;
        }

        return removeAll(array, Arrays.copyOf(indices, count));
    }

    /**
     * <p>Returns an array containing the string representation of each element in the argument array.</p>
     *
     * <p>This method returns {@code null} for a {@code null} input array.</p>
     *
     * @param array the {@code Object[]} to be processed, may be null
     * @return {@code String[]} of the same size as the source with its element's string representation,
     * {@code null} if null array input
     * @throws NullPointerException if array contains {@code null}
     */
    public static String[] toStringArray(final Object[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_STRING_ARRAY;
        }

        final String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].toString();
        }

        return result;
    }

    /**
     * <p>Returns an array containing the string representation of each element in the argument
     * array handling {@code null} elements.</p>
     *
     * <p>This method returns {@code null} for a {@code null} input array.</p>
     *
     * @param array                the Object[] to be processed, may be null
     * @param valueForNullElements the value to insert if {@code null} is found
     * @return a {@code String} array, {@code null} if null array input
     */
    public static String[] toStringArray(final Object[] array, final String valueForNullElements) {
        if (null == array) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_STRING_ARRAY;
        }

        final String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            final Object object = array[i];
            result[i] = (object == null ? valueForNullElements : object.toString());
        }

        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static boolean[] insert(final int index, final boolean[] array, final boolean... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final boolean[] result = new boolean[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static byte[] insert(final int index, final byte[] array, final byte... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final byte[] result = new byte[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static char[] insert(final int index, final char[] array, final char... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final char[] result = new char[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static double[] insert(final int index, final double[] array, final double... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final double[] result = new double[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static float[] insert(final int index, final float[] array, final float... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final float[] result = new float[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static int[] insert(final int index, final int[] array, final int... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final int[] result = new int[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static long[] insert(final int index, final long[] array, final long... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final long[] result = new long[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    public static short[] insert(final int index, final short[] array, final short... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final short[] result = new short[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param <T>    The type of elements in {@code array} and {@code values}
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     */
    @SafeVarargs
    public static <T> T[] insert(final int index, final T[] array, final T... values) {
        /*
         * Note on use of @SafeVarargs:
         *
         * By returning null when 'array' is null, we avoid returning the vararg
         * array to the caller. We also avoid relying on the type of the vararg
         * array, by inspecting the component type of 'array'.
         */

        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final Class<?> type = array.getClass().getComponentType();
        @SuppressWarnings("unchecked") // OK, because array and values are of type T
        final T[] result = (T[]) Array.newInstance(type, array.length + values.length);

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final Object[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final Object[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final boolean[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final boolean[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final byte[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final byte[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final char[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final char[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final short[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final short[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final int[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final int[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final long[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final long[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final float[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final float[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array the array to shuffle
     */
    public static void shuffle(final double[] array) {
        shuffle(array, new Random());
    }

    /**
     * Randomly permutes the elements of the specified array using the Fisher-Yates algorithm.
     *
     * @param array  the array to shuffle
     * @param random the source of randomness used to permute the elements
     */
    public static void shuffle(final double[] array, final Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    /**
     * Returns whether a given array can safely be accessed at the given index.
     *
     * @param <T>   the component type of the array
     * @param array the array to inspect, may be null
     * @param index the index of the array to be inspected
     * @return Whether the given index is safely-accessible in the given array
     */
    public static <T> boolean isArrayIndexValid(T[] array, int index) {
        if (getLength(array) == 0 || array.length <= index) {
            return false;
        }

        return index >= 0;
    }

    /**
     * A mutable <code>int</code> wrapper.
     * <p>
     * Note that as MutableInt does not extend Integer, it is not treated by String.format as an Integer parameter.
     */
    private static class MutableInt extends Number implements Comparable<MutableInt> {

        /**
         * Required for serialization support.
         */
        private static final long serialVersionUID = 512176391864L;

        /**
         * The mutable value.
         */
        private int value;

        /**
         * Constructs a new MutableInt with the default value of zero.
         */
        public MutableInt() {
            super();
        }

        /**
         * Constructs a new MutableInt with the specified value.
         *
         * @param value the initial value to store
         */
        public MutableInt(final int value) {
            super();
            this.value = value;
        }

        /**
         * Constructs a new MutableInt with the specified value.
         *
         * @param value the initial value to store, not null
         * @throws NullPointerException if the object is null
         */
        public MutableInt(final Number value) {
            super();
            this.value = value.intValue();
        }

        /**
         * Constructs a new MutableInt parsing the given string.
         *
         * @param value the string to parse, not null
         * @throws NumberFormatException if the string cannot be parsed into an int
         */
        public MutableInt(final String value) {
            super();
            this.value = Integer.parseInt(value);
        }

        /**
         * Gets the value as a Integer instance.
         *
         * @return the value as a Integer, never null
         */
        public Integer getValue() {
            return this.value;
        }

        /**
         * Sets the value.
         *
         * @param value the value to set
         */
        public void setValue(final int value) {
            this.value = value;
        }

        /**
         * Sets the value from any Number instance.
         *
         * @param value the value to set, not null
         * @throws NullPointerException if the object is null
         */
        public void setValue(final Number value) {
            this.value = value.intValue();
        }

        /**
         * Increments the value.
         */
        public void increment() {
            value++;
        }

        /**
         * Increments this instance's value by 1; this method returns the value associated with the instance
         * immediately prior to the increment operation. This method is not thread safe.
         *
         * @return the value associated with the instance before it was incremented
         */
        public int getAndIncrement() {
            final int last = value;
            value++;
            return last;
        }

        /**
         * Increments this instance's value by 1; this method returns the value associated with the instance
         * immediately after the increment operation. This method is not thread safe.
         *
         * @return the value associated with the instance after it is incremented
         */
        public int incrementAndGet() {
            value++;
            return value;
        }

        /**
         * Decrements the value.
         */
        public void decrement() {
            value--;
        }

        /**
         * Decrements this instance's value by 1; this method returns the value associated with the instance
         * immediately prior to the decrement operation. This method is not thread safe.
         *
         * @return the value associated with the instance before it was decremented
         */
        public int getAndDecrement() {
            final int last = value;
            value--;
            return last;
        }

        /**
         * Decrements this instance's value by 1; this method returns the value associated with the instance
         * immediately after the decrement operation. This method is not thread safe.
         *
         * @return the value associated with the instance after it is decremented
         */
        public int decrementAndGet() {
            value--;
            return value;
        }

        /**
         * Adds a value to the value of this instance.
         *
         * @param operand the value to add, not null
         */
        public void add(final int operand) {
            this.value += operand;
        }

        /**
         * Adds a value to the value of this instance.
         *
         * @param operand the value to add, not null
         * @throws NullPointerException if the object is null
         */
        public void add(final Number operand) {
            this.value += operand.intValue();
        }

        /**
         * Subtracts a value from the value of this instance.
         *
         * @param operand the value to subtract, not null
         */
        public void subtract(final int operand) {
            this.value -= operand;
        }

        /**
         * Subtracts a value from the value of this instance.
         *
         * @param operand the value to subtract, not null
         * @throws NullPointerException if the object is null
         */
        public void subtract(final Number operand) {
            this.value -= operand.intValue();
        }

        /**
         * Increments this instance's value by {@code operand}; this method returns the value associated with the instance
         * immediately after the addition operation. This method is not thread safe.
         *
         * @param operand the quantity to add, not null
         * @return the value associated with this instance after adding the operand
         */
        public int addAndGet(final int operand) {
            this.value += operand;
            return value;
        }

        /**
         * Increments this instance's value by {@code operand}; this method returns the value associated with the instance
         * immediately after the addition operation. This method is not thread safe.
         *
         * @param operand the quantity to add, not null
         * @return the value associated with this instance after adding the operand
         * @throws NullPointerException if {@code operand} is null
         */
        public int addAndGet(final Number operand) {
            this.value += operand.intValue();
            return value;
        }

        /**
         * Increments this instance's value by {@code operand}; this method returns the value associated with the instance
         * immediately prior to the addition operation. This method is not thread safe.
         *
         * @param operand the quantity to add, not null
         * @return the value associated with this instance immediately before the operand was added
         */
        public int getAndAdd(final int operand) {
            final int last = value;
            this.value += operand;
            return last;
        }

        /**
         * Increments this instance's value by {@code operand}; this method returns the value associated with the instance
         * immediately prior to the addition operation. This method is not thread safe.
         *
         * @param operand the quantity to add, not null
         * @return the value associated with this instance immediately before the operand was added
         * @throws NullPointerException if {@code operand} is null
         */
        public int getAndAdd(final Number operand) {
            final int last = value;
            this.value += operand.intValue();
            return last;
        }

        /**
         * Returns the value of this MutableInt as an int.
         *
         * @return the numeric value represented by this object after conversion to type int.
         */
        @Override
        public int intValue() {
            return value;
        }

        /**
         * Returns the value of this MutableInt as a long.
         *
         * @return the numeric value represented by this object after conversion to type long.
         */
        @Override
        public long longValue() {
            return value;
        }

        /**
         * Returns the value of this MutableInt as a float.
         *
         * @return the numeric value represented by this object after conversion to type float.
         */
        @Override
        public float floatValue() {
            return value;
        }

        /**
         * Returns the value of this MutableInt as a double.
         *
         * @return the numeric value represented by this object after conversion to type double.
         */
        @Override
        public double doubleValue() {
            return value;
        }

        /**
         * Gets this mutable as an instance of Integer.
         *
         * @return a Integer instance containing the value from this mutable, never null
         */
        public Integer toInteger() {
            return intValue();
        }

        /**
         * Compares this object to the specified object. The result is <code>true</code> if and only if the argument is
         * not <code>null</code> and is a <code>MutableInt</code> object that contains the same <code>int</code> value
         * as this object.
         *
         * @param obj the object to compare with, null returns false
         * @return <code>true</code> if the objects are the same; <code>false</code> otherwise.
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof MutableInt) {
                return value == ((MutableInt) obj).intValue();
            }
            return false;
        }

        /**
         * Returns a suitable hash code for this mutable.
         *
         * @return a suitable hash code
         */
        @Override
        public int hashCode() {
            return value;
        }

        /**
         * Compares this mutable to another in ascending order.
         *
         * @param other the other mutable to compare to, not null
         * @return negative if this is less, zero if equal, positive if greater
         */
        @Override
        public int compareTo(final MutableInt other) {
            return Integer.compare(this.value, other.value);
        }

        /**
         * Returns the String value of this mutable.
         *
         * @return the mutable value as a string
         */
        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

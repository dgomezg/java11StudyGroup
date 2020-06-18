package dev.dgomezg.playground.java.strings;

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */


import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * <p>
 * See https://issues.liferay.com/browse/LPS-6072.
 * </p>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Preston Crary
 */
public class LiferayStringBundler implements Serializable {

    public static String concat(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                strings[i] = "null";
            }
        }

        return _toString(strings, strings.length);
    }

    public LiferayStringBundler() {
        _array = new String[_DEFAULT_ARRAY_CAPACITY];
    }

    public LiferayStringBundler(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = _DEFAULT_ARRAY_CAPACITY;
        }

        _array = new String[initialCapacity];
    }

    public LiferayStringBundler(String s) {
        _array = new String[_DEFAULT_ARRAY_CAPACITY];

        _array[0] = s;

        _arrayIndex = 1;
    }

    public LiferayStringBundler(String[] stringArray) {
        this(stringArray, 0);
    }

    public LiferayStringBundler(String[] stringArray, int extraSpace) {
        _array = new String[stringArray.length + extraSpace];

        for (String s : stringArray) {
            if ((s != null) && (s.length() > 0)) {
                _array[_arrayIndex++] = s;
            }
        }
    }

    public LiferayStringBundler append(boolean b) {
        if (b) {
            return append("true");
        }

        return append("false");
    }

    public LiferayStringBundler append(char c) {
        return append(String.valueOf(c));
    }

    public LiferayStringBundler append(char[] chars) {
        if (chars == null) {
            return append("null");
        }

        return append(new String(chars));
    }

    public LiferayStringBundler append(double d) {
        return append(String.valueOf(d));
    }

    public LiferayStringBundler append(float f) {
        return append(String.valueOf(f));
    }

    public LiferayStringBundler append(int i) {
        return append(String.valueOf(i));
    }

    public LiferayStringBundler append(long l) {
        return append(String.valueOf(l));
    }

    public LiferayStringBundler append(Object object) {
        return append(String.valueOf(object));
    }

    public LiferayStringBundler append(String s) {
        if (s == null) {
            s = "null";
        }

        if (s.length() == 0) {
            return this;
        }

        if (_arrayIndex >= _array.length) {
            expandCapacity(_array.length * 2);
        }

        _array[_arrayIndex++] = s;

        return this;
    }

    public LiferayStringBundler append(String[] stringArray) {

        if ((_array.length - _arrayIndex) < stringArray.length) {
            expandCapacity((_array.length + stringArray.length) * 2);
        }

        for (String s : stringArray) {
            if ((s != null) && (s.length() > 0)) {
                _array[_arrayIndex++] = s;
            }
        }

        return this;
    }

    public LiferayStringBundler append(LiferayStringBundler sb) {
        if ((sb == null) || (sb._arrayIndex == 0)) {
            return this;
        }

        if ((_array.length - _arrayIndex) < sb._arrayIndex) {
            expandCapacity((_array.length + sb._arrayIndex) * 2);
        }

        System.arraycopy(sb._array, 0, _array, _arrayIndex, sb._arrayIndex);

        _arrayIndex += sb._arrayIndex;

        return this;
    }

    public int capacity() {
        return _array.length;
    }

    public String[] getStrings() {
        return _array;
    }

    public int index() {
        return _arrayIndex;
    }

    public int length() {
        int length = 0;

        for (int i = 0; i < _arrayIndex; i++) {
            length += _array[i].length();
        }

        return length;
    }

    public void setIndex(int newIndex) {
        if (newIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(newIndex);
        }

        if (newIndex > _array.length) {
            String[] newArray = new String[newIndex];

            System.arraycopy(_array, 0, newArray, 0, _arrayIndex);

            _array = newArray;
        }

        if (_arrayIndex < newIndex) {
            for (int i = _arrayIndex; i < newIndex; i++) {
                _array[i] = "";
            }
        }

        if (_arrayIndex > newIndex) {
            for (int i = newIndex; i < _arrayIndex; i++) {
                _array[i] = null;
            }
        }

        _arrayIndex = newIndex;
    }

    public void setStringAt(String s, int index) {
        if ((index < 0) || (index >= _arrayIndex)) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        _array[index] = s;
    }

    public String stringAt(int index) {
        if ((index < 0) || (index >= _arrayIndex)) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return _array[index];
    }

    @Override
    public String toString() {
        return _toString(_array, _arrayIndex);
    }

    public void writeTo(Writer writer) throws IOException {
        for (int i = 0; i < _arrayIndex; i++) {
            writer.write(_array[i]);
        }
    }

    protected void expandCapacity(int newCapacity) {
        String[] newArray = new String[newCapacity];

        System.arraycopy(_array, 0, newArray, 0, _arrayIndex);

        _array = newArray;
    }

    private static String _toString(String[] array, int arrayIndex) {
        if (arrayIndex == 0) {
            return "";
        }

        if (arrayIndex == 1) {
            return array[0];
        }

        if (arrayIndex == 2) {
            return array[0].concat(array[1]);
        }

        if (arrayIndex == 3) {
            if (array[0].length() < array[2].length()) {
                return array[0].concat(
                        array[1]
                ).concat(
                        array[2]
                );
            }

            return array[0].concat(array[1].concat(array[2]));
        }

        int length = 0;

        for (int i = 0; i < arrayIndex; i++) {
            length += array[i].length();
        }

        StringBuilder sb = null;

        if (length > _THREAD_LOCAL_BUFFER_LIMIT) {
            Reference<StringBuilder> reference =
                    _stringBuilderThreadLocal.get();

            if (reference != null) {
                sb = reference.get();
            }

            if (sb == null) {
                sb = new StringBuilder(length);

                _stringBuilderThreadLocal.set(new SoftReference<>(sb));
            }
            else {
                sb.setLength(length);
            }

            sb.setLength(0);
        }
        else {
            sb = new StringBuilder(length);
        }

        for (int i = 0; i < arrayIndex; i++) {
            sb.append(array[i]);
        }

        return sb.toString();
    }

    private static final int _DEFAULT_ARRAY_CAPACITY = 16;

    private static final int _THREAD_LOCAL_BUFFER_LIMIT;

    private static final ThreadLocal<Reference<StringBuilder>>
            _stringBuilderThreadLocal;
    private static final long serialVersionUID = 1L;

    static {
            _THREAD_LOCAL_BUFFER_LIMIT = Integer.MAX_VALUE;

            _stringBuilderThreadLocal = null;
    }

    private String[] _array;
    private int _arrayIndex;

}
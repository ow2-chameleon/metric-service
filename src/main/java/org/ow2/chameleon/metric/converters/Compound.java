/*
 * Copyright 2013 OW2 Chameleon
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ow2.chameleon.metric.converters;

/**
 * Composition of operations.
 */
public class Compound implements ConversionFunction {

    private final ConversionFunction left;
    private final ConversionFunction right;

    public Compound(ConversionFunction left, ConversionFunction right) {
        this.left = left;
        this.right = right;
    }

    public Number apply(Number number) {
        return left.apply(right.apply(number));
    }

    public ConversionFunction inverse() {
        return new Compound(right.inverse(), left.inverse());
    }
}
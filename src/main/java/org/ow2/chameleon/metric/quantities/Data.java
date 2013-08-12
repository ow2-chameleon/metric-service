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
package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.TransformedUnit;
import org.ow2.chameleon.metric.TransformedUnitBuilder;
import org.ow2.chameleon.metric.Unit;

/**
 * This class represents an amount of data quantity in bit.
 * This class use the IEC and decimal prefixes.
 *
 * @author clement.escoffier@gmail.com
 */
public class Data extends Quantity<Data> {

    public static final Unit<Data> BIT = new Unit<Data>("bit", "bit");

    public static final Unit<Data> BYTE = new TransformedUnitBuilder<Data>(BIT).times(8).symbol("B").name("byte")
            .withConverter().build();

    public static final Unit<Data> OCTET = BYTE;

    public static final Unit<Data> NIBBLE = new TransformedUnitBuilder<Data>(BIT).times(4).symbol("ni").name("nibble")
            .withConverter().build();

    public static final Unit<Data> RUNE = new TransformedUnitBuilder<Data>(BIT).times(16).symbol("ru").name("rune")
            .withConverter().build();

    public static final Unit<Data> QUAD = new TransformedUnitBuilder<Data>(BIT).times(32).symbol("q").name("quad")
            .withConverter().build();

    // Multiples using the decimal prefix (SI)

    /**
     * 1 Kilobit = 1000 bits
     */
    public static final Unit<Data> KILOBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1000, 1))
            .symbol("Kbit")
            .withConverter()
            .build();

    /**
     * 1 KiloByte = 1000 Bytes
     */
    public static final Unit<Data> KILOBYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1000, 1))
            .symbol("KB")
            .withConverter()
            .build();

    /**
     * 1 Kibibit = 1024 bits
     */
    public static final Unit<Data> KIBIBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(1024)
            .symbol("Kibit")
            .name("kibibit")
            .withConverter()
            .build();

    /**
     * 1 kibibyte = 1024 bytes
     */
    public static final Unit<Data> KIBIBYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(1024)
            .symbol("KiB")
            .name("kibibyte")
            .withConverter()
            .build();

    /**
     * 1 Megabit = 1 000 000 bits
     */
    public static final Unit<Data> MEGABIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1000, 2))
            .symbol("Mbit")
            .withConverter()
            .build();

    /**
     * 1 MegaByte = 1000^2 Bytes
     */
    public static final Unit<Data> MEGABYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1000, 2))
            .symbol("MB")
            .withConverter()
            .build();

    /**
     * 1 mebibit = 1024^2 bits
     */
    public static final Unit<Data> MEBIBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1024,2))
            .symbol("Mibit")
            .name("mebibit")
            .withConverter()
            .build();

    /**
     * 1 mebibyte = 1024^2 bytes
     */
    public static final Unit<Data> MEBIBYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1024,2))
            .symbol("MiB")
            .name("mebibyte")
            .withConverter()
            .build();

    /**
     * 1 Gigabit = 1000^3 bits
     */
    public static final Unit<Data> GIGABIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1000, 3))
            .symbol("Gbit")
            .withConverter()
            .build();

    /**
     * 1 GigaByte = 1000^3 bytes
     */
    public static final Unit<Data> GIGABYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1000, 3))
            .symbol("GB")
            .withConverter()
            .build();

    /**
     * 1 gibibit = 1024^3 bits
     */
    public static final Unit<Data> GIBIBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1024,3))
            .symbol("Gibit")
            .name("gibibit")
            .withConverter()
            .build();

    /**
     * 1 gibibyte = 1024^3 bytes
     */
    public static final Unit<Data> GIBIBYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1024,3))
            .symbol("GiB")
            .name("gibibyte")
            .withConverter()
            .build();

    /**
     * 1 terabit = 1000^4 bits
     */
    public static final Unit<Data> TERABIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1000, 4))
            .symbol("Tbit")
            .withConverter()
            .build();


    /**
     * 1 TeraByte = 1000^4 bytes
     */
    public static final Unit<Data> TERABYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1000, 4))
            .symbol("TB")
            .withConverter()
            .build();

    /**
     * 1 tebibit = 1024^4 bits
     */
    public static final Unit<Data> TEBEBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(Math.pow(1024,4))
            .symbol("Tibit")
            .name("tebibit")
            .withConverter()
            .build();

    /**
     * 1 tebibyte = 1024^4 bytes
     */
    public static final Unit<Data> TEBIBYTE = new TransformedUnitBuilder<Data>(BYTE)
            .times(Math.pow(1024,4))
            .symbol("TiB")
            .name("tibibyte")
            .withConverter()
            .build();


    /**
     * @param number
     * @param unit
     */
    public Data(Number number, Unit<Data> unit) {
        super(Data.class, number, unit);
    }

    public Data(Number number) {
        this(number, BIT);
    }
}

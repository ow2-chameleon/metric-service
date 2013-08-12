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
package org.ow2.chameleon.metric.systems;

import org.ow2.chameleon.metric.*;
import org.ow2.chameleon.metric.quantities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents definition of SI Units
 */
public class SI extends DefaultSystemOfUnits {

    public static Unit<Length> METRE = new Unit<Length>("m", "meter", Dimension.LENGTH);
    public static Unit<Mass> GRAM = new Unit<Mass>("g", "gram", Dimension.MASS);
    public static Unit<Time> SECOND = new Unit<Time>("s", "second", Dimension.TIME);
    public static Unit AMPERE = new Unit("A", "ampere", Dimension.ELECTRIC_CURRENT);
    public static Unit<Temperature> KELVIN = new Unit<Temperature>("K", "kelvin", Dimension.TEMPERATURE);
    public static Unit MOL = new Unit("mol", "mol", Dimension.AMOUNT_OF_SUBSTANCE);
    public static Unit CANDELA = new Unit("cd", "candela", Dimension.LUMINOUS_INTENSITY);

    public SI() {
        super("SI", Arrays.asList(
                Dimension.AMOUNT_OF_SUBSTANCE,
                Dimension.ELECTRIC_CURRENT,
                Dimension.LENGTH,
                Dimension.LUMINOUS_INTENSITY,
                Dimension.MASS,
                Dimension.TEMPERATURE,
                Dimension.TIME,
                Dimension.NONE),
                getSIPrefixMapping());

        // The SI defines the kilogram as base unit and not gram.
        Unit<Mass> kg = new TransformedUnitBuilder<Mass>(GRAM).times(1000).symbol("kg").name("kilogram")
                .withConverter().build();

        // Base units.
        this.addUnitToSystem(METRE)
                .addUnitToSystem(kg, false) // No prefix for Kg.
                .addUnitToSystem(GRAM)
                .addUnitToSystem(SECOND)
                .addUnitToSystem(AMPERE)
                .addUnitToSystem(KELVIN)
                .addUnitToSystem(MOL)
                .addUnitToSystem(CANDELA);

        // Angle
        final Unit<Angle> steradian = new Unit<Angle>("sr", "steradian", Dimension.NONE);
        this.addUnitToSystem(steradian);
        this.addUnitToSystem(new Unit<Angle>("rad", "radian", Dimension.NONE));

        // Celsius
        this.addUnitToSystem(new TransformedUnitBuilder<Temperature>(KELVIN)
                .symbol("\u2103")
                .name("celsius")
                .add(273.15)
                .withConverter()
                .build());

        // Frequency
        this.addUnitToSystem(new DerivedUnitBuilder<Frequency>().pow(SI.SECOND, -1).name("hertz").symbol("Hz").build());

        // Force
        final Unit<Force> newton = new DerivedUnitBuilder<Force>().from(kg).times(SI.METRE).pow(SI.SECOND,-2).name("newton").symbol("N").build();
        this.addUnitToSystem(newton);

        // Pressure
        this.addUnitToSystem(new DerivedUnitBuilder<Pressure>()
                .from(newton).pow(SI.METRE, -2)
                .name("pascal")
                .symbol("Pa")
                .build());

        // Energy
        final Unit<Energy> joule = new DerivedUnitBuilder<Energy>()
                .from(newton).times(METRE)
                .name("joule")
                .symbol("J")
                .build();
        this.addUnitToSystem(joule);

        // Power
        final Unit<Power> watt = new DerivedUnitBuilder<Power>().name("watt").symbol("W").from(joule).dividedBy(SECOND)
                .build();
        this.addUnitToSystem(watt);

        // Electric Charge
        final Unit<ElectricCharge> coulomb = new DerivedUnitBuilder<ElectricCharge>().name("coulomb").symbol("C").from(SECOND).times(AMPERE)
                .build();
        this.addUnitToSystem(coulomb);

        // Electric Potential
        final Unit<ElectricPotential> volt = new DerivedUnitBuilder<ElectricPotential>().name("volt").symbol("V").from(watt).dividedBy(AMPERE)
                .build();
        this.addUnitToSystem(volt);

        // Electric Capacitance
        this.addUnitToSystem(new DerivedUnitBuilder<ElectricCapacitance>().name("farad").symbol("F").from(coulomb).dividedBy(volt)
                .build());

        // Electric Resistance
        this.addUnitToSystem(new DerivedUnitBuilder<ElectricResistance>().name("ohm").symbol("\u2126").from
                (volt).dividedBy(AMPERE).build());

        // Luminous Flux
        final Unit<LuminousFlux> lumen = new DerivedUnitBuilder<LuminousFlux>().name("lumen").symbol("lm").from(CANDELA).times
                (steradian).build();
        this.addUnitToSystem(lumen);

        // Illuminance
        this.addUnitToSystem(new DerivedUnitBuilder<Illuminance>().name("lux").symbol("lx").from(lumen).pow
                (METRE, -2).build());

        // Area
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("square metre").symbol("\u33A1").from(METRE).times(METRE)
                .build());

        // Volume
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("cubic metre").symbol("\u33A5").from(METRE).times(METRE)
                .times(METRE).build());

        // Velocity
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("metre per second").symbol("m/s").from(METRE).dividedBy
                (SECOND).build());

        // Volumetric flow
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("cubic metre per second").symbol("\u33A5/s").from(METRE)
                .times
                        (METRE).times(METRE).dividedBy(SECOND).build());

        // Acceleration
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("metre per second squared").symbol("m/s\u00B2").from(METRE)
                .times(METRE).pow(SECOND, -2).build());

        // Density
        this.addUnitToSystem(new DerivedUnitBuilder<Area>().name("gram per cubic metre").symbol("g/m\u00B3").from(GRAM).pow
                (METRE, -3).build());

    }

    public static SystemOfUnits getSI() {
        return MetricService.getInstance().getSystemOfUnits("SI");
    }

    public static List<Prefix> getSIPrefixMapping() {
        List<Prefix> prefixes = new ArrayList<Prefix>();
        prefixes.add(new Prefix("tera", "T", 1000000000000l));
        prefixes.add(new Prefix("giga", "G", 1000000000l));
        prefixes.add(new Prefix("mega", "M", 1000000));
        prefixes.add(new Prefix("giga", "k", 1000));
        prefixes.add(new Prefix("hecto", "h", 100));
        prefixes.add(new Prefix("deci", "d", 0.1));
        prefixes.add(new Prefix("centi", "c", 0.01));
        prefixes.add(new Prefix("milli", "m", 0.001));
        prefixes.add(new Prefix("micro", "\u00B5", 0.000001));
        prefixes.add(new Prefix("nano", "n", 0.000000001));
        prefixes.add(new Prefix("pico", "p", 0.000000000001));
        return prefixes;
    }
}

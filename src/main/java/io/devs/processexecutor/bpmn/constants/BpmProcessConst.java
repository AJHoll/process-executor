package io.devs.processexecutor.bpmn.constants;

import java.math.BigDecimal;

public class BpmProcessConst {

    public static Long CONSIGNMENT_QUANTITY = 100L; // 100 ед. в партии производства
    public static BigDecimal SHTRIPS_AMOUNT_IN_ONE_PRODDUCT = BigDecimal.valueOf(0.8); // в кг. - там 200 грамм 4 пластины на 1

    public static BigDecimal getMetallInProduct() {
        return SHTRIPS_AMOUNT_IN_ONE_PRODDUCT.multiply(BigDecimal.valueOf(CONSIGNMENT_QUANTITY));
    }

    public static BigDecimal getShtripsInProduct() {
        return SHTRIPS_AMOUNT_IN_ONE_PRODDUCT.multiply(BigDecimal.valueOf(CONSIGNMENT_QUANTITY));
    }
}

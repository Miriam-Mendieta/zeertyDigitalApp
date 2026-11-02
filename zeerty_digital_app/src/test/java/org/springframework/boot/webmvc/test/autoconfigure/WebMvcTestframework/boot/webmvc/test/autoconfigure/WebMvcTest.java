package org.springframework.boot.webmvc.test.autoconfigure.WebMvcTestframework.boot.webmvc.test.autoconfigure;

import com.miriammendieta.zeerty_digital_app.controller.ActivoDigitalController;

/**
 * WebMvcTest
 */
public @interface WebMvcTest {

    Class<ActivoDigitalController> value();

}

package com.cbus.aem.core.services.impl;

import com.cbus.aem.core.config.PetsApiConfig;
import com.cbus.aem.core.services.PetsApiConfigService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = PetsApiConfigService.class, immediate = true)
@Designate(ocd = PetsApiConfig.class)
public class PetsApiConfigServiceImpl implements PetsApiConfigService {
    private PetsApiConfig config;

    @Activate
    protected void activate(PetsApiConfig config) {
        this.config = config;
    }

    @Override
    public PetsApiConfig getConfig() {
        return config;
    }
}
